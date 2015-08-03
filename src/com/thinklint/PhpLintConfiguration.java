package com.thinklint;

import com.intellij.openapi.util.text.StringUtil;
import com.intellij.util.ArrayUtil;
import com.intellij.util.xmlb.annotations.Attribute;
import com.jetbrains.php.tools.quality.QualityToolConfiguration;
import com.thinklint.PhpLintConfigurationManager;
import com.intellij.util.xmlb.annotations.Attribute;
import com.intellij.util.xmlb.annotations.Transient;
import org.jetbrains.annotations.NotNull;

/**
 * Created by zenus on 2015/8/1.
 */

public class  PhpLintConfiguration implements QualityToolConfiguration {
    public static final String LOCAL = "Local";
    private String myPhpLintPath = "";
    private String myStandards = "";
    private int myMaxMessagesPerFile;
    private int myTimeoutMs;

    public PhpLintConfiguration() {
        this.myMaxMessagesPerFile = PhpLintConfigurationManager.DEFAULT_MAX_MESSAGES_PER_FILE;
        this.myTimeoutMs = 5000;
    }

    @Attribute("tool_path")
    public String getToolPath() {
        return this.myPhpLintPath;
    }

    public void setToolPath(String toolPath) {
        this.myPhpLintPath = toolPath;
    }

    @Attribute("max_messages_per_file")
    public int getMaxMessagesPerFile() {
        return this.myMaxMessagesPerFile;
    }

    public void setMaxMessagesPerFile(int maxMessagesPerFile) {
        this.myMaxMessagesPerFile = maxMessagesPerFile;
    }

    @Attribute("timeout")
    public int getTimeout() {
        return this.myTimeoutMs;
    }

    public void setTimeout(int timeout) {
        this.myTimeoutMs = timeout;
    }

    public String getPresentableName() {
        return this.getId();
    }

    public String getId() {
        return "Local";
    }

    public String getInterpreterId() {
        return null;
    }

    public PhpLintConfiguration clone() {
        PhpLintConfiguration settings = new PhpLintConfiguration();
        this.clone(settings);
        return settings;
    }

    public PhpLintConfiguration clone(@NotNull PhpLintConfiguration settings) {
        settings.myPhpLintPath = this.myPhpLintPath;
        settings.myMaxMessagesPerFile = this.myMaxMessagesPerFile;
        settings.myTimeoutMs = this.myTimeoutMs;
        return settings;
    }

    @Transient
    public String[] getStandards() {
        String[] savedStandards = this.myStandards.split(PhpLintConfigurationManager.STANDARDS_SEPARATOR);
        return (String[])ArrayUtil.append(savedStandards, PhpLintConfigurationManager.CUSTOM_CODING_STANDARD);
    }

    public int compareTo(@NotNull QualityToolConfiguration o) {
        return !(o instanceof PhpLintConfiguration)?1:(StringUtil.equals(this.getPresentableName(), "Local")?-1:(StringUtil.equals(o.getPresentableName(), "Local")?1:StringUtil.compare(this.getPresentableName(), o.getPresentableName(), false)));
    }
}
