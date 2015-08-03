package com.thinklint;
import com.intellij.icons.AllIcons.General;
import com.intellij.ide.DataManager;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.DefaultActionGroup;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.util.Pair;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.ui.table.JBTable;
import com.intellij.util.Consumer;
import com.jetbrains.php.config.interpreters.PhpInterpreter;
import com.jetbrains.php.config.interpreters.PhpInterpretersManager;
import com.jetbrains.php.run.remote.PhpRemoteInterpreterManager;
import com.jetbrains.php.tools.quality.QualityToolValidationException;
import com.thinklint.PhpLintConfiguration;
import com.thinklint.PhpLintProjectConfiguration;
//import com.thinklint.MessDetectorRulesetAnalyzer;
import com.thinklint.PhpLintInspection;
//import com.jetbrains.php.tools.quality.messDetector.RulesetDescriptor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.table.AbstractTableModel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PhpLintOptionsPanel {
    private static final Logger LOG = Logger.getInstance(PhpLintOptionsPanel.class);
    private JPanel myContentPane;
    private JPanel myRulesOptions;
    private JBTable myCustomRulesetTable;
    private JPanel myToolBarPanel;
    private final PhpLintInspection myInspection;
    //private final PhpLintOptionsPanel.CustomRulesetTableModel myRulesetTableModelModel;

    public PhpLintOptionsPanel(PhpLintInspection inspection) {
        this.myInspection = inspection;
        this.createUIComponents();
//        DefaultActionGroup group = new DefaultActionGroup();
//        JComponent toolBar = ActionManager.getInstance().createActionToolbar("unknown", group, true).getComponent();
//        this.myRulesetTableModelModel = new PhpLintOptionsPanel.CustomRulesetTableModel(null);
//        this.myCustomRulesetTable.setModel(this.myRulesetTableModelModel);
//        AnAction addAction = new AnAction("Add Rule", (String)null, General.Add) {
//            public void actionPerformed(AnActionEvent e) {
//                PhpLintOptionsPanel.this.addCustomRule();
//            }
//        };
//        group.add(addAction);
//        AnAction removeAction = new AnAction("Remove Rule", (String)null, General.Remove) {
//            public void actionPerformed(AnActionEvent e) {
//                PhpLintOptionsPanel.this.removeCustomRule();
//            }
//
//            public void update(AnActionEvent e) {
//                e.getPresentation().setEnabled(PhpLintOptionsPanel.this.myCustomRulesetTable.getSelectedRows().length > 0);
//            }
//        };
//        group.add(removeAction);
//        this.myToolBarPanel.add(toolBar, "Center");
    }

    public JPanel getContentPane() {
        return this.myContentPane;
    }

    private void createUIComponents() {
//        this.myRulesOptions = new JPanel();
//        this.myRulesOptions.setLayout(new BoxLayout(this.myRulesOptions, 1));
//        new PhpLintOptionsPanel.MyRuleSetOption(this.myRulesOptions, "codesize", "Code Size Rules", null);
//        new PhpLintOptionsPanel.MyRuleSetOption(this.myRulesOptions, "controversial", "Controversial Rules", null);
//        new PhpLintOptionsPanel.MyRuleSetOption(this.myRulesOptions, "design", "Design Rules", null);
//        new PhpLintOptionsPanel.MyRuleSetOption(this.myRulesOptions, "naming", "Naming Rules", null);
//        new PhpLintOptionsPanel.MyRuleSetOption(this.myRulesOptions, "unusedcode", "Unused Code Rules", null);
    }

    @Nullable
    private PhpInterpreter getSelectedInterpreter() {
        Project project = this.getCurrentProject();

        try {
            PhpLintConfiguration e = (PhpLintConfiguration)PhpLintProjectConfiguration.getInstance(project).findSelectedConfiguration(project);
            return e != null && !StringUtil.isEmpty(e.getInterpreterId())?PhpInterpretersManager.getInstance().findInterpreterById(e.getInterpreterId()):null;
        } catch (QualityToolValidationException var3) {
            return null;
        }
    }

    @NotNull
    private Project getCurrentProject() {
        Project project = (Project)CommonDataKeys.PROJECT.getData(DataManager.getInstance().getDataContext(this.myContentPane));
        if(project == null) {
            project = ProjectManager.getInstance().getDefaultProject();
        }

        return project;
    }

//    private void addCustomRule() {
//        PhpInterpreter interpreter = this.getSelectedInterpreter();
//        if(interpreter != null && interpreter.isRemote()) {
//            PhpRemoteInterpreterManager fileChooserDescriptor = PhpRemoteInterpreterManager.getInstance();
//            if(fileChooserDescriptor != null) {
//                fileChooserDescriptor.openRemoteFileChooser(this.getCurrentProject(), interpreter.getPhpSdkAdditionalData(), (String)null, "Set Path", this.myContentPane, new Consumer() {
//                    public void consume(Pair<String, VirtualFile> file) {
//                        PhpLintOptionsPanel.this.addRuleDescriptor((String)file.getFirst(), (VirtualFile)file.getSecond());
//                    }
//                });
//                return;
//            }
//
//            LOG.warn("Remote interpreter can\'t be executed. Please enable the PHP Remote Interpreter plugin.");
//        }
//
//        FileChooserDescriptor fileChooserDescriptor1 = new FileChooserDescriptor(true, false, false, false, false, false) {
//            public boolean isFileSelectable(VirtualFile file) {
//                return !PhpLintOptionsPanel.containsRulesetXml(file)?false:super.isFileSelectable(file);
//            }
//
//            public boolean isFileVisible(VirtualFile file, boolean showHiddenFiles) {
//                return file.isDirectory() || "xml".equals(file.getExtension());
//            }
//        };
//        VirtualFile file = FileChooser.chooseFile(fileChooserDescriptor1, this.myContentPane, (Project)null, this.getPreselectedFile());
//        if(file != null) {
//            this.addRuleDescriptor(file.getPath(), file);
//        }
//
//    }

//    private void addRuleDescriptor(@NotNull String originalPath, @NotNull VirtualFile file) {
//        RulesetDescriptor descriptor = PhpLintRulesetAnalyzer.readRulesetFile(originalPath, file);
//        if(descriptor != null) {
//            int row = this.myRulesetTableModelModel.addDescriptor(descriptor);
//            this.myCustomRulesetTable.getSelectionModel().setSelectionInterval(row, row);
//        }
//
//    }

//    @Nullable
//    private VirtualFile getPreselectedFile() {
//                    int[] selectedRows = this.myCustomRulesetTable.getSelectedRows();
//                    if(selectedRows.length > 0) {
//                        RulesetDescriptor descriptor = this.myRulesetTableModelModel.getDescriptorAt(selectedRows[0]);
//                        if(descriptor.isValid(false)) {
//                            File file = new File(descriptor.getPath());
//                            if(file.exists()) {
//                                return VfsUtil.findFileByIoFile(file, true);
//                            }
//            }
//        }
//
//        return null;
//    }

//    private static boolean containsRulesetXml(VirtualFile file) {
//        return PhpLintRulesetAnalyzer.readRulesetFile(file) != null;
//    }

//    private void removeCustomRule() {
//        int[] selectedRows = this.myCustomRulesetTable.getSelectedRows();
//        if(selectedRows.length > 0) {
//            this.myRulesetTableModelModel.removeDescriptors(selectedRows);
//        }
//
//    }

//    private class CustomRulesetTableModel extends AbstractTableModel {
//        private CustomRulesetTableModel() {
//        }

//        public int getRowCount() {
//            return PhpLintOptionsPanel.this.myInspection.customRulesets == null?0:PhpLintOptionsPanel.this.myInspection.customRulesets.size();
//        }
//
//        public int getColumnCount() {
//            return 2;
//        }
//
//        public String getColumnName(int columnIndex) {
//            switch(columnIndex) {
//                case 0:
//                    return "Name";
//                case 1:
//                    return "File";
//                default:
//                    return null;
//            }
//        }
//
//        public Class<?> getColumnClass(int columnIndex) {
//            return String.class;
//        }
//
//        public boolean isCellEditable(int rowIndex, int columnIndex) {
//            return false;
//        }

//        public Object getValueAt(int rowIndex, int columnIndex) {
//            switch(columnIndex) {
//                case 0:
//                    return ((RulesetDescriptor)PhpLintOptionsPanel.this.myInspection.customRulesets.get(rowIndex)).getName();
//                case 1:
//                    RulesetDescriptor descriptor = (RulesetDescriptor)PhpLintOptionsPanel.this.myInspection.customRulesets.get(rowIndex);
//                    PhpInterpreter interpreter = PhpLintOptionsPanel.this.getSelectedInterpreter();
//                    boolean remote = interpreter != null && interpreter.isRemote();
//                    return descriptor.isValid(remote)?descriptor.getPath():"<does not exist>";
//                default:
//                    return null;
//            }
//        }

//        public int addDescriptor(RulesetDescriptor rulesetDescriptor) {
//            PhpLintOptionsPanel.this.myInspection.customRulesets.add(rulesetDescriptor);
//            this.fireTableDataChanged();
//            return PhpLintOptionsPanel.this.myInspection.customRulesets.size() - 1;
//        }

//        public void removeDescriptors(int[] rowIndices) {
//            ArrayList toRemove = new ArrayList();
//            int[] arr$ = rowIndices;
//            int len$ = rowIndices.length;
//
//            for(int i$ = 0; i$ < len$; ++i$) {
//                int rowIndex = arr$[i$];
//                toRemove.add(PhpLintOptionsPanel.this.myInspection.customRulesets.get(rowIndex));
//            }
//
//            PhpLintOptionsPanel.this.myInspection.customRulesets.removeAll(toRemove);
//            this.fireTableDataChanged();
//        }

//        public RulesetDescriptor getDescriptorAt(int rowIndex) {
//            return rowIndex >= 0 && rowIndex < PhpLintOptionsPanel.this.myInspection.customRulesets.size()?(RulesetDescriptor)PhpLintOptionsPanel.this.myInspection.customRulesets.get(rowIndex):null;
//        }
    //}

//    private class MyRuleSetOption {
//        private JCheckBox myCheckBox;
//        private final String myOptionName;
//
//        private MyRuleSetOption(JPanel contentPane, String optionName, String optionTitle) {
//            this.myCheckBox = new JCheckBox(optionTitle);
//            this.myOptionName = optionName;
//            this.myCheckBox.setSelected(PhpLintOptionsPanel.this.myInspection.getRulesetOptionValue(optionName));
//            this.myCheckBox.addActionListener(new ActionListener() {
//                public void actionPerformed(ActionEvent e) {
//                    PhpLintOptionsPanel.this.myInspection.setRulesetOptionValue(MyRuleSetOption.this.myOptionName, MyRuleSetOption.this.myCheckBox.isSelected());
//                }
//            });
//            contentPane.add(this.myCheckBox);
//        }
//    }
}
