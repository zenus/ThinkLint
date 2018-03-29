package com.thinklint;

import com.intellij.codeInspection.LocalInspectionTool;
import com.intellij.execution.ExecutionException;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.util.ArrayUtil;
import com.jetbrains.php.config.interpreters.PhpInterpreter;
import com.jetbrains.php.config.interpreters.PhpInterpretersManagerImpl;
import com.jetbrains.php.config.interpreters.PhpSdkFileTransfer;
import com.jetbrains.php.tools.quality.QualityToolAnnotator;
import com.jetbrains.php.tools.quality.QualityToolAnnotatorInfo;
import com.jetbrains.php.tools.quality.QualityToolConfiguration;
import com.jetbrains.php.tools.quality.QualityToolMessageProcessor;
import com.jetbrains.php.tools.quality.QualityToolProcessCreator;
import com.jetbrains.php.tools.quality.QualityToolValidationException;
import com.thinklint.PhpLintMessageProcessor;
import com.thinklint.PhpLintProjectConfiguration;
import com.thinklint.PhpLintInspection;
import java.util.ArrayList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.thinklint.PhpLintProjectConfiguration;

public class PhpLintAnnotator extends QualityToolAnnotator {
    private static final Logger LOG = Logger.getInstance(PhpLintAnnotator.class);
    private static final String RUNNING_LINT_ANNOTATOR = "Running PhpLint annotator";
    private static final String TEMP_DIRECTORY = "phplint_temp.tmp";
    static final PhpLintAnnotator INSTANCE = new PhpLintAnnotator();

    public PhpLintAnnotator() {
    }

    @Nullable
    protected QualityToolConfiguration getConfiguration(@NotNull Project project, @NotNull LocalInspectionTool inspection) {
        try {
            return PhpLintProjectConfiguration.getInstance(project).findSelectedConfiguration(project);
        } catch (QualityToolValidationException var3) {
            LOG.warn(var3.getMessage());
            return null;
        }
    }

    protected String getTemporaryFilesFolder() {
        return "phplint_temp.tmp";
    }

    protected String getProcessTitle() {
        return "Running PhpLint annotator";
    }

    protected String getInspectionId() {
        return (new PhpLintInspection()).getID();
    }

    protected void runTool(@NotNull QualityToolMessageProcessor messageProcessor, @NotNull QualityToolAnnotatorInfo annotatorInfo) throws ExecutionException {

    }

    @Override
    protected void runTool(@NotNull QualityToolMessageProcessor messageProcessor, @NotNull QualityToolAnnotatorInfo annotatorInfo, @NotNull PhpSdkFileTransfer transfer) throws ExecutionException {
        ArrayList params = new ArrayList();
        params.add("--project-root");
        params.add((annotatorInfo.getProject()).getBasePath());
        params.add(annotatorInfo.getFilePath());
        //PhpLintInspection inspection = (PhpLintInspection)annotatorInfo.getInspection();
        //String enabledOptions = inspection.getRuleSetsOption(isRemote(annotatorInfo.getInterpreterId()));
        //if(!enabledOptions.isEmpty()) {
        //   params.add(enabledOptions);
        QualityToolProcessCreator.runToolProcess(
                annotatorInfo.getProject(),
                annotatorInfo.getInterpreterId(),
                annotatorInfo.getToolPath(),
                annotatorInfo.getTimeout(),
                PhpLintBlackList.getInstance(annotatorInfo.getProject()),
                messageProcessor, null,
                annotatorInfo.getOriginalFile(),
                transfer, ArrayUtil.toStringArray(params));
        if(messageProcessor.getInternalErrorMessage() != null) {
            if(annotatorInfo.isOnTheFly()) {
                PhpLintBlackList blackList = PhpLintBlackList.getInstance(annotatorInfo.getProject());
                String message = messageProcessor.getInternalErrorMessage().getMessageText();
                showProcessErrorMessage(annotatorInfo, blackList, message);
            }

            messageProcessor.setFatalError();
        }

        //}
    }

    private static boolean isRemote(@Nullable String interpreterId, Project project) {
        if(!StringUtil.isNotEmpty(interpreterId)) {
            return false;
        } else {
            PhpInterpreter interpreter = PhpInterpretersManagerImpl.getInstance(project).findInterpreterById(interpreterId);
            return interpreter != null && interpreter.isRemote();
        }
    }

    protected QualityToolMessageProcessor createMessageProcessor(@NotNull QualityToolAnnotatorInfo collectedInfo) {
        return new PhpLintMessageProcessor(collectedInfo,collectedInfo.getMaxMessagesPerFile());
        //return QualityToolMessageProcessor.createBufferingProcessor() ;
    }
}
