package com.thinklint;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.project.Project;
import com.intellij.util.xmlb.XmlSerializerUtil;
import com.jetbrains.php.tools.quality.QualityToolProjectConfiguration;
//import com.jetbrains.php.tools.quality.messDetector.MessDetectorConfiguration;
//import com.jetbrains.php.tools.quality.messDetector.MessDetectorConfigurationManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.thinklint.PhpLintConfigurationManager;
import com.thinklint.PhpLintConfiguration;

/**
 * Created by zenus on 2015/7/31.
 */

@State(
        name = "PhpLintProjectConfiguration",
        storages = {        @Storage(
                file = "$WORKSPACE_FILE$"
        )}
)
public class PhpLintProjectConfiguration extends QualityToolProjectConfiguration<PhpLintConfiguration> implements PersistentStateComponent<PhpLintProjectConfiguration> {
    public PhpLintProjectConfiguration() {
    }

    public static PhpLintProjectConfiguration getInstance(Project project) {
        return (PhpLintProjectConfiguration) ServiceManager.getService(project, PhpLintProjectConfiguration.class);
    }

    @Nullable
    public PhpLintProjectConfiguration getState() {
        return this;
    }

    public void loadState(PhpLintProjectConfiguration state) {
        XmlSerializerUtil.copyBean(state, this);
    }

    @NotNull
    protected String getQualityToolName() {
        return "PhpLint";
    }

    @NotNull
    protected PhpLintConfigurationManager getConfigurationManager() {
        return PhpLintConfigurationManager.getInstance();
    }
}
