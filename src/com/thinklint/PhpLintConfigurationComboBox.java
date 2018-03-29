package com.thinklint;

import com.intellij.openapi.project.Project;
import com.jetbrains.php.tools.quality.QualityToolConfigurationComboBox;
import com.jetbrains.php.tools.quality.QualityToolProjectConfiguration;
import com.thinklint.PhpLintConfigurableList;
import com.thinklint.PhpLintConfiguration;
import com.thinklint.PhpLintConfigurationManager;
import com.jetbrains.php.ui.PhpUiUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by zenus on 2015/8/1.
 */
public class PhpLintConfigurationComboBox extends QualityToolConfigurationComboBox<PhpLintConfiguration> {
    public PhpLintConfigurationComboBox(@Nullable Project project) {
        super(project);
    }

    @NotNull
    protected ActionListener createBrowserAction(@NotNull final Project project) {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                QualityToolConfigurationItem item = PhpLintConfigurationComboBox.this.getSelectedItem();
                PhpLintConfigurableList configurableList = new PhpLintConfigurableList(project, item == null?null:item.getName());
                if(PhpUiUtil.editConfigurable(project, configurableList)) {
                    String oldSelectedId = item == null?null:item.getId();
                    boolean byDefaultInterpreter = QualityToolProjectConfiguration.isByDefaultInterpreter(oldSelectedId);
                    String newSelectedId = byDefaultInterpreter?oldSelectedId:configurableList.getLastSelectedItemId();
                    PhpLintConfigurationComboBox.this.reset(project, newSelectedId);
                } else {
                    PhpLintConfigurationComboBox.this.reset(project);
                }

            }
        };
    }

    @NotNull
    protected List<PhpLintConfiguration> getItems() {
        return PhpLintConfigurationManager.getInstance().getAllSettings();
    }

    @NotNull
    protected PhpLintConfiguration getDefaultItem() {
        return (PhpLintConfiguration)PhpLintConfigurationManager.getInstance().getLocalSettings();
    }
}