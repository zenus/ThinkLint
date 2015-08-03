package com.thinklint;

/**
 * Created by zenus on 2015/8/1.
 */
import com.jetbrains.php.ui.PhpNamedCloneableItemsListEditor.ConfigurableFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.php.tools.quality.QualityToolConfigurableForm;
import com.intellij.openapi.project.Project;
import com.thinklint.PhpLintConfigurableForm;
import com.thinklint.PhpLintConfiguration;
import com.thinklint.PhpLintConfigurationProvider;
import com.intellij.openapi.options.UnnamedConfigurable ;

import com.thinklint.PhpLintConfigurableForm;

public class PhpLintConfigurableFactory<T extends PhpLintConfiguration> implements ConfigurableFactory<T>{

    private Project project ;

    public PhpLintConfigurableFactory(Project project){
        this.project = project;
    }
    @NotNull
//    public UnnamedConfigurable createConfigurable(@NotNull PhpLintConfiguration item, @NotNull final Project project) {
    public UnnamedConfigurable createConfigurable(@NotNull PhpLintConfiguration item) {
//        PhpLintConfigurationProvider provider = PhpLintConfigurationProvider.getInstances();
//        if(provider != null) {
//            QualityToolConfigurableForm form = provider.createConfigurationForm(this.project, item);
//            if(form != null) {
//                return form;
//            }
//        }
        return new PhpLintConfigurableForm(this.project, item);
    }
}
