package com.thinklint;


/**
 * Created by zenus on 2015/8/1.
 */
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.extensions.ExtensionPointName;
import com.intellij.util.NullableFunction;
import com.jetbrains.php.tools.quality.QualityToolConfigurationProvider;
import com.thinklint.PhpLintConfiguration;
import com.thinklint.PhpLintConfigurationManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class PhpLintConfigurationProvider extends QualityToolConfigurationProvider<PhpLintConfiguration> {
    private static final Logger LOG = Logger.getInstance(PhpLintConfigurationProvider.class);
    private static final ExtensionPointName<PhpLintConfigurationProvider> EP_NAME = ExtensionPointName.create("com.thinklint.PhpLintConfigurationProvider");

    public PhpLintConfigurationProvider() {
    }

    @Nullable
    public static PhpLintConfigurationProvider getInstances() {
        PhpLintConfigurationProvider[] extensions = (PhpLintConfigurationProvider[])EP_NAME.getExtensions();
        if(extensions.length > 1) {
            LOG.error("Several providers for phplint configuration was found");
        }

        return extensions.length == 0?null:extensions[0];
    }

    protected void fillSettingsByDefaultValue(@NotNull PhpLintConfiguration settings, @NotNull NullableFunction<String, String> preparePath) {
        PhpLintConfiguration localConfiguration = (PhpLintConfiguration) PhpLintConfigurationManager.getInstance().getLocalSettings();
        super.fillSettingsByDefaultValue(settings, localConfiguration, preparePath);
    }
}