package com.thinklint;
import com.jetbrains.php.ui.PhpNamedCloneableItemsListEditor.Cloner;
import org.jetbrains.annotations.NotNull;
import com.thinklint.PhpLintConfiguration;

/**
 * Created by Administrator on 2015/8/1.
 */
 public class PhpLintCloner<T extends PhpLintConfiguration> implements Cloner<T> {
//    @NotNull
//    PhpLintConfiguration cloneOf(T var1){
//
//    }
//
//    @NotNull
//    T copy( T var1){
//
//    }
    @NotNull
    public PhpLintConfiguration cloneOf(@NotNull PhpLintConfiguration settings) {
        return this.copy(settings);
       // return settings;
    }
    @NotNull
    public PhpLintConfiguration copy(@NotNull PhpLintConfiguration settings) {
        return settings.clone();
       // return settings;
    }

}

