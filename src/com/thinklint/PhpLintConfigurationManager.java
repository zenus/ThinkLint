package com.thinklint;

/**
 * Created by  zenus on 2015/7/31.
 */
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializer;
import com.jetbrains.php.tools.quality.QualityToolConfigurationManager;
import com.jetbrains.php.tools.quality.QualityToolConfigurationProvider;
import com.thinklint.PhpLintConfiguration;
import com.thinklint.PhpLintConfigurationProvider;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@State(
        name = "PhpLint",
        storages = {        @Storage(
                file = "$APP_CONFIG$/php.xml"
        )}
)
public class PhpLintConfigurationManager extends QualityToolConfigurationManager<PhpLintConfiguration> {
    private static final String LINT_DETECTOR_PATH = "PhpLintPath";
    private static final String LINT_DETECTOR = "PhpLint";
    private static final String ROOT_NAME = "PhpLint_settings";
    public static int DEFAULT_MAX_MESSAGES_PER_FILE = 50;
    public static String CUSTOM_CODING_STANDARD = "Custom";
    public static String STANDARDS_SEPARATOR = ";";

    public PhpLintConfigurationManager() {
    }

    public static PhpLintConfigurationManager getInstance() {
        return (PhpLintConfigurationManager)ServiceManager.getService(PhpLintConfigurationManager.class);
    }

    @NotNull
    protected PhpLintConfiguration createLocalSettings() {
        return new PhpLintConfiguration();
    }

    @NotNull
    protected String getQualityToolName() {
        return "PhpLint";
    }

    @NotNull
    protected String getOldStyleToolPathName() {
        return "PhpLintPath";
    }

    @NotNull
    protected String getConfigurationRootName() {
        return "PhpLint_settings";
    }

    @Nullable
    protected QualityToolConfigurationProvider<PhpLintConfiguration> getConfigurationProvider() {
        return PhpLintConfigurationProvider.getInstances();
    }

    @Nullable
    protected PhpLintConfiguration loadLocal(Element element) {
        return (PhpLintConfiguration)XmlSerializer.deserialize(element, PhpLintConfiguration.class);
    }
}
