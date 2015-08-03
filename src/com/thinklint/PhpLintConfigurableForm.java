package com.thinklint;

/**
 * Created by Administrator on 2015/8/1.
 */
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Pair;
import com.intellij.openapi.vfs.VirtualFile;
import com.jetbrains.php.tools.quality.QualityToolConfigurableForm;
import com.thinklint.PhpLintConfiguration;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PhpLintConfigurableForm<C extends PhpLintConfiguration> extends QualityToolConfigurableForm<C> {
    public PhpLintConfigurableForm(@NotNull Project project, @NotNull C configuration) {
        super(project, configuration, "PhpLint", "phplint");
    }

    @NotNull
    public C getConfiguration() {
        return super.getConfiguration();
    }

    @Nls
    public String getDisplayName() {
        return "PhpLint";
    }

    @Nullable
    public String getHelpTopic() {
        return "settings.php.phplint";
    }

    @NotNull
    public String getId() {
        return PhpLintConfigurableForm.class.getName();
    }

    @NotNull
    public Pair<Boolean, String> validateMessage(String message) {
        return message.startsWith("phplint")?Pair.create(Boolean.valueOf(true), "OK, " + message):Pair.create(Boolean.valueOf(false), message);
    }

    public boolean isValidToolFile(VirtualFile file) {
        return file.getName().startsWith("phpl");
    }
}