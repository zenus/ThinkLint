package com.thinklint;

/**
 * Created by zenus on 2015/8/1.
 */

import com.intellij.openapi.options.UnnamedConfigurable;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.NotNullFactory;
import com.jetbrains.php.tools.quality.QualityToolConfigurableForm;
import com.jetbrains.php.tools.quality.QualityToolConfigurableList;
import com.jetbrains.php.tools.quality.QualityToolConfiguration;
import com.jetbrains.php.tools.quality.QualityToolConfigurationProvider;
import com.thinklint.PhpLintConfigurable;
import com.thinklint.PhpLintConfigurableForm;
import com.thinklint.PhpLintConfiguration;
import com.thinklint.PhpLintConfigurationManager;
import com.thinklint.PhpLintConfigurationProvider;
import com.jetbrains.php.ui.PhpUiUtil;
import com.jetbrains.php.ui.PhpNamedCloneableItemsListEditor.Cloner;
import com.jetbrains.php.ui.PhpNamedCloneableItemsListEditor.ConfigurableFactory;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.thinklint.PhpLintCloner;
import com.thinklint.PhpLintConfiguration;

public class PhpLintConfigurableList extends QualityToolConfigurableList<PhpLintConfiguration> {
    public static final String DISPLAY_NAME = "PhpLint";
    public static final String HELP_TOPIC = "reference.settings.php.phplint";
    @NonNls
    private static final String SUBJ_DISPLAY_NAME = "phplint";

    public PhpLintConfigurableList(@NotNull final Project project, @Nullable String initialElement) {
        super(project, PhpLintConfigurationManager.getInstance(), new NotNullFactory() {
            @NotNull
            public PhpLintConfiguration create() {
                return new PhpLintConfiguration();
            }
        },
            new PhpLintCloner() ,
            new PhpLintConfigurableFactory(project) ,
            initialElement);
        this.setSubjectDisplayName("phplint");
    }

    @Nullable
    protected QualityToolConfigurationProvider<PhpLintConfiguration> getConfigurationProvider() {
        return PhpLintConfigurationProvider.getInstances();
    }

    @Nullable
    protected PhpLintConfiguration getConfiguration(@Nullable QualityToolConfiguration configuration) {
        return configuration instanceof PhpLintConfiguration?(PhpLintConfiguration)configuration:null;
    }

    @NotNull
    public static Runnable createFix(@NotNull final Project project) {
        return new Runnable() {
            public void run() {
                PhpUiUtil.editConfigurable(project, new PhpLintConfigurable(project));
            }
        };
    }

    @Nls
    public String getDisplayName() {
        return "PhpLint";
    }

    public String getHelpTopic() {
        return "reference.settings.php.phplint";
    }
}