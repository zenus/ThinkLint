package com.thinklint;

import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.project.Project;
import com.jetbrains.php.tools.quality.QualityToolBlackList;

/**
 * Created by zenus on 2015/8/1.
 */
@State(
        name = "PhpLintBlackList",
        storages = {        @Storage(
                file = "$WORKSPACE_FILE$"
        )}
)
public class PhpLintBlackList extends QualityToolBlackList {
    public PhpLintBlackList() {
    }

    public static PhpLintBlackList getInstance(Project project) {
        return (PhpLintBlackList) ServiceManager.getService(project, PhpLintBlackList.class);
    }
}