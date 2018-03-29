package com.thinklint;

/**
 * Created by zenus on 2015/7/31.
 */

import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.text.StringUtil;
import com.jetbrains.php.tools.quality.QualityToolConfigurationComboBox;
import com.jetbrains.php.tools.quality.QualityToolProjectConfigurableForm;
import com.jetbrains.php.tools.quality.QualityToolValidationException;
import com.thinklint.PhpLintBlackList;
import com.thinklint.PhpLintConfigurationComboBox;
import com.thinklint.PhpLintProjectConfiguration;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PhpLintConfigurable extends QualityToolProjectConfigurableForm {
    public PhpLintConfigurable(@NotNull Project project) {
        super(project, PhpLintBlackList.getInstance(project));
    }

    @Nls
    public String getDisplayName() {
        return "PhpLint";
    }

    public String getHelpTopic() {
        return "reference.settings.php.phplint";
    }

    @NotNull
    public String getId() {
        return PhpLintConfigurable.class.getName();
    }

    protected void updateSelectedConfiguration(@Nullable String newConfigurationId) {
        PhpLintProjectConfiguration projectConfiguration = PhpLintProjectConfiguration.getInstance(this.myProject);
        if(newConfigurationId != null && !StringUtil.equals(newConfigurationId, projectConfiguration.getSelectedConfigurationId())) {
            projectConfiguration.setSelectedConfigurationId(newConfigurationId);
        }

    }

    @Nullable
    protected String getSavedSelectedConfigurationId() {
        return PhpLintProjectConfiguration.getInstance(this.myProject).getSelectedConfigurationId();
    }

    @Nullable
    protected String validate(@Nullable String configuration) {
        try {
            PhpLintProjectConfiguration.getInstance(this.myProject).findConfiguration(this.myProject, configuration);
            return null;
        } catch (QualityToolValidationException var3) {
            return var3.getMessage();
        }
    }

    @NotNull
    protected QualityToolConfigurationComboBox createConfigurationComboBox() {
        return new PhpLintConfigurationComboBox(myProject);
    }
}

