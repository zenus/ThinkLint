package com.thinklint;

/**
 * Created by zenus on 2015/7/31.
 */

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.util.containers.hash.HashSet;
import com.intellij.openapi.project.Project;
import com.jetbrains.php.tools.quality.QualityToolMessage.Severity;
import com.intellij.codeHighlighting.HighlightDisplayLevel;
import com.jetbrains.php.tools.quality.QualityToolAnnotatorInfo;
import com.jetbrains.php.tools.quality.QualityToolMessage;
import com.jetbrains.php.tools.quality.QualityToolMessageProcessor;
import com.jetbrains.php.tools.quality.QualityToolMessage.Severity;
import java.io.IOException;
import java.io.StringReader;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
//import javax.xml.parsers.ParserConfigurationException;
//import javax.xml.parsers.SAXParser;
//import javax.xml.parsers.SAXParserFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
//import org.xml.sax.Attributes;
//import org.xml.sax.InputSource;
//import org.xml.sax.SAXException;
//import org.xml.sax.helpers.DefaultHandler;
import com.thinklint.PhpLintConfigurable;

public  class PhpLintMessageProcessor extends QualityToolMessageProcessor {
    private StringBuilder messageBuf;
    private int myPrevLine = -1;
    private static String message = "";
    private Set<String> lineMessages = new HashSet();
  //  private SAXParser mySAXParser;
    private static Logger LOG = Logger.getInstance(PhpLintMessageProcessor.class.getName());

    protected PhpLintMessageProcessor(QualityToolAnnotatorInfo info, int maxMessages) {
        super(info, maxMessages);

//        try {
//            this.mySAXParser = SAXParserFactory.newInstance().newSAXParser();
//        } catch (ParserConfigurationException var4) {
//            LOG.error(var4.getMessage());
//        } catch (SAXException var5) {
//            LOG.error(var5.getMessage());
//        }

    }

    @Nullable
    protected HighlightDisplayLevel severityToDisplayLevel(@NotNull Severity severity) {
        return null;
    }

    public void parseLine(String line) {
//        if(canBeMessageStart(line) && this.messageBuf == null) {
//            this.messageBuf = new StringBuilder();
//        }

    if(canBeErrorMessage(line) > -1 && canBeMessageStart(line)) {
//            String message = this.messageBuf.append(line).toString().trim();
 //           boolean isErrorMessageStart = this.getMessageStart(message) > -1;
  //              boolean isErrorMessageEnd = this.getMessageEnd(message) > -1;
   //             if(isErrorMessageEnd) {
    //                this.messageBuf = null;

                            QualityToolMessage qualityToolMessage = new QualityToolMessage(this, getLineNum(line), getSeverity(), getMessageText(line));
                            int currLine = getLineNum(line);
                            if(currLine != this.myPrevLine) {
                                this.lineMessages.clear();
                                this.myPrevLine = currLine;
                            }

                            String messageText = qualityToolMessage.getMessageText();
                            if(!this.lineMessages.contains(messageText)) {
                                this.lineMessages.add(messageText);
                                this.addMessage(qualityToolMessage);
                            }
                //}
        }
    }

    private static int getLineNum(String message) {
        String pattern = "====\\s+(\\d+):\\s+ERROR:\\s+(.*)";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(message);
        if (m.find( )) {
            String lineNumStr = m.group(1);
            message = m.group(2);
            return Integer.parseInt(lineNumStr);
        }else{
            message = "";
            return 1;
        }
    }
    private static String getMessageText(String message) {
        return  message;
    }

    @NotNull
    protected String getQuickFixFamilyName() {
        return "PhpLint";
    }

    private Severity getSeverity() {
        return Severity.ERROR;
    }


    private static boolean canBeMessageStart(String message) {
        return StringUtil.startsWith(message.trim(), "====");
    }
    private static int canBeErrorMessage(String message) {
        return message.indexOf("ERROR");
    }

    private static boolean canBeMessageEnd(String message) {
        return StringUtil.endsWith(message, "\r\n");
    }

    public void done() {
    }

    protected PhpLintConfigurable getToolConfigurable(@NotNull Project project) {
        return new PhpLintConfigurable(project);
    }


    //public abstract int getMessageStart(@NotNull String var1);
    public int getMessageStart(@NotNull String line) {
        return line.indexOf("====");
    }

    public int getMessageEnd(@NotNull String line) {
        int TagStart = line.indexOf("====");
        return TagStart > -1 ? line.length()-4:-1;
    }

    //public abstract int getMessageEnd(@NotNull String var1);

}