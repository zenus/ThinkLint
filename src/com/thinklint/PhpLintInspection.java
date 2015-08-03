package com.thinklint;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import com.intellij.codeHighlighting.HighlightDisplayLevel;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.profile.codeInspection.InspectionProfileManager;
import com.jetbrains.php.lang.inspections.PhpInspection;
import com.jetbrains.php.tools.quality.QualityToolAnnotator;
import com.jetbrains.php.tools.quality.QualityToolValidationInspection;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

import com.thinklint.PhpLintAnnotator;

public class PhpLintInspection extends QualityToolValidationInspection {
    public static final String DISPLAY_NAME = "PhpLint Validation";
    public boolean IGNORE_WARNINGS = false;
    public String CODING_STANDARD = "";
    public String CUSTOM_RULESET_PATH = "";
    public String WARNING_HIGHLIGHT_LEVEL_NAME;
    public boolean SHOW_LINT_NAMES;

    public PhpLintInspection() {
        this.WARNING_HIGHLIGHT_LEVEL_NAME = HighlightSeverity.WARNING.myName;
        this.SHOW_LINT_NAMES = false;
    }

    @NotNull
    public String[] getGroupPath() {
        String[] group= PhpInspection.GROUP_PATH_GENERAL;
        if(PhpInspection.GROUP_PATH_GENERAL == null) {
            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", new Object[]{"com/thinklint/PhpLintInspection", "getGroupPath"}));
        } else {
            return group;
        }
    }

    @NotNull
    public String getShortName() {
        return this.getClass().getSimpleName();
    }

    @Nls
    @NotNull
    public String getDisplayName() {
        return "PhpLint Validation";
    }

    @Nullable
    public JComponent createOptionsPanel() {
        return (new PhpLintOptionsPanel(this)).getContentPane();
    }

    @NotNull
    protected QualityToolAnnotator getAnnotator() {
        PhpLintAnnotator annotator = PhpLintAnnotator.INSTANCE;
        if(PhpLintAnnotator.INSTANCE == null) {
            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", new Object[]{"com/thinklint/PhpLintInspection", "getAnnotator"}));
        } else {
            return annotator;
        }
    }

    public String getToolName() {
        return "PhpLint ";
    }

    HighlightDisplayLevel getWarningLevel() {
        HighlightSeverity severity = InspectionProfileManager.getInstance().getOwnSeverityRegistrar().getSeverity(this.WARNING_HIGHLIGHT_LEVEL_NAME);
        if(severity != null) {
            HighlightDisplayLevel level = HighlightDisplayLevel.find(severity);
            if(level != null) {
                return level;
            }
        }

        return HighlightDisplayLevel.WARNING;
    }

    void setWarningLevel(HighlightDisplayLevel level) {
        this.WARNING_HIGHLIGHT_LEVEL_NAME = level.getSeverity().myName;
    }
}

