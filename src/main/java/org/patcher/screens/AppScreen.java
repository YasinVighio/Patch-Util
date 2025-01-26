/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package org.patcher.screens;

import org.patcher.core.PatchUtil;
import org.patcher.dto.ActionDTO;
import org.patcher.utility.UIUtils;
import org.patcher.utility.Util;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Yasin
 */
public class AppScreen extends javax.swing.JFrame {

    private DefaultListModel<String> fileListModel = new DefaultListModel<>();
    private DefaultComboBoxModel<String> appNameModel = new DefaultComboBoxModel<>();

    private String appDir =  "";
    private String patchPath = "";

    /**
     * Creates new form AppScreen
     */
    public AppScreen() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addFileBtn = new javax.swing.JButton();
        rmFileBtn = new javax.swing.JButton();
        createPatchBtn = new javax.swing.JButton();
        applyPatchBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        selectedFilesList = new javax.swing.JList<>();
        appNameLabel = new javax.swing.JLabel();
        customAppName = new javax.swing.JTextField();
        appNameSelector = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        matchAppRootBox = new javax.swing.JCheckBox();
        appNameLabel1 = new javax.swing.JLabel();
        appNameLabel2 = new javax.swing.JLabel();
        selectAppBtn = new javax.swing.JButton();
        clearOutputBtn = new javax.swing.JButton();
        readPatchBtn = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        outputArea = new javax.swing.JTextPane();
        verifyPatchBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        addFileBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        addFileBtn.setText("Add File");
        addFileBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addFileBtnActionPerformed(evt);
            }
        });

        rmFileBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        rmFileBtn.setText("Remove File");
        rmFileBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rmFileBtnActionPerformed(evt);
            }
        });

        createPatchBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        createPatchBtn.setText("Create Patch");
        createPatchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createPatchBtnActionPerformed(evt);
            }
        });

        applyPatchBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        applyPatchBtn.setText("Apply Patch");
        applyPatchBtn.setAutoscrolls(true);
        applyPatchBtn.setEnabled(false);
        applyPatchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                applyPatchBtnActionPerformed(evt);
            }
        });

        selectedFilesList.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        selectedFilesList.setModel(fileListModel);
        jScrollPane1.setViewportView(selectedFilesList);

        appNameLabel.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        appNameLabel.setForeground(new java.awt.Color(153, 153, 0));
        appNameLabel.setText("Output");
        appNameLabel.setToolTipText("Utility application to create patch from and for exploded (extracted) WAR/JAR Files");

        customAppName.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        appNameSelector.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        appNameSelector.setModel(appNameModel);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Root Directory");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Deployment App Name");

        matchAppRootBox.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        matchAppRootBox.setSelected(true);
        matchAppRootBox.setText("Match Deployment App Name with Root Directory of Patch");


        appNameLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        appNameLabel1.setForeground(new java.awt.Color(153, 153, 0));
        appNameLabel1.setText("Patch Utils v1.0");
        appNameLabel1.setToolTipText("Utility application to create patch from and for exploded (extracted) WAR/JAR Files");

        appNameLabel2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        appNameLabel2.setForeground(new java.awt.Color(153, 153, 0));
        appNameLabel2.setText("Files Selected");
        appNameLabel2.setToolTipText("Utility application to create patch from and for exploded (extracted) WAR/JAR Files");

        selectAppBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        selectAppBtn.setText("Select App");
        selectAppBtn.setToolTipText("Select exploded war or jar binary on which patch is to be applied");
        selectAppBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectAppBtnActionPerformed(evt);
            }
        });

        clearOutputBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        clearOutputBtn.setText("Clear");
        clearOutputBtn.setAutoscrolls(true);
        clearOutputBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearOutputBtnActionPerformed(evt);
            }
        });

        readPatchBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        readPatchBtn.setText("Read Patch");
        readPatchBtn.setToolTipText("Select exploded war or jar binary on which patch is to be applied");
        readPatchBtn.setEnabled(false);
        readPatchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                readPatchBtnActionPerformed(evt);
            }
        });

        outputArea.setEditable(false);
        outputArea.setContentType("text/html"); // NOI18N
        outputArea.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jScrollPane3.setViewportView(outputArea);

        verifyPatchBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        verifyPatchBtn.setText("Verify Patch");
        verifyPatchBtn.setToolTipText("Select exploded war or jar binary on which patch is to be applied");
        verifyPatchBtn.setEnabled(false);
        verifyPatchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verifyPatchBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 939, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(appNameLabel)
                            .addComponent(clearOutputBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane3))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(createPatchBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(appNameLabel2))
                                .addGap(18, 18, 18)
                                .addComponent(matchAppRootBox))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(rmFileBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(addFileBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(166, 166, 166)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel1))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(appNameSelector, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(customAppName, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(appNameLabel1))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(applyPatchBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(selectAppBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(readPatchBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(verifyPatchBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(113, 113, 113)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(265, 265, 265))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(appNameLabel1)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(appNameSelector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(customAppName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(73, 73, 73))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(addFileBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rmFileBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(createPatchBtn)
                                    .addComponent(matchAppRootBox))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(selectAppBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(readPatchBtn)
                        .addGap(7, 7, 7)
                        .addComponent(verifyPatchBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(applyPatchBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(appNameLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(clearOutputBtn))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addComponent(appNameLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private String openFileDialog(){
        String filePath = "";
        try{
            FileDialog fd = new FileDialog(this);
            fd.setVisible(true);
            if(Util.areStringsValid(fd.getFile(), fd.getDirectory())){
                filePath = fd.getDirectory()+fd.getFile();
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return filePath;
    }

    private void addFileBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addFileBtnActionPerformed
        String newFile = openFileDialog();
        if(newFile.endsWith(".zip")){
            appNameModel.removeAllElements();
            customAppName.setText("");
            fileListModel.removeAllElements();
            fileListModel.addElement(newFile);
            if(Util.areStringsValid(this.appDir)) {
                verifyPatchBtn.setEnabled(true);
            }
            readPatchBtn.setEnabled(true);
            this.patchPath = newFile;
        } else if(newFile.endsWith(".class")) {
            String filePathWithoutDrive = UIUtils.removeDriveLetterFromPath(newFile);
            String[] filePathParts = filePathWithoutDrive.split("\\\\");
            for (String path : filePathParts) {
                if (appNameModel.getIndexOf(path) < 0) {
                    appNameModel.addElement(path);
                }
            }
            fileListModel.addElement(newFile);
            UIUtils.removeZipFilesFromListModel(fileListModel);
            this.readPatchBtn.setEnabled(false);
            this.applyPatchBtn.setEnabled(false);
            this.verifyPatchBtn.setEnabled(false);
        }
    }//GEN-LAST:event_addFileBtnActionPerformed

    private void rmFileBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rmFileBtnActionPerformed
        int selectedFile = selectedFilesList.getSelectedIndex();
        if(selectedFile>-1){
            if(selectedFilesList.getSelectedValue().endsWith(".zip")){
                this.verifyPatchBtn.setEnabled(false);
                this.applyPatchBtn.setEnabled(false);
                this.readPatchBtn.setEnabled(false);
                this.patchPath="";
            }
            fileListModel.remove(selectedFile);
        }
    }//GEN-LAST:event_rmFileBtnActionPerformed

    private void createPatchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createPatchBtnActionPerformed
        if(Util.isListModelNotEmpty(fileListModel)) {
            String newFileName = JOptionPane.showInputDialog(this, "Enter patch name");
            if (!Util.areStringsValid(newFileName)) {
                newFileName = "patch_" + (new Date()).toString();
            }
            newFileName = newFileName + ".zip";
            System.out.println(newFileName);
            List<String> filePaths = UIUtils.convertListModelToListStr(fileListModel);
            String appName = appNameSelector.getSelectedItem().toString();
            String customName = null;
            if (Util.areStringsValid(customAppName.getText())) {
                customName = customAppName.getText();
            }
            if (Util.isListNotEmpty(filePaths)) {
                String outputMsg = PatchUtil.createPatch(filePaths, newFileName, appName, customName);
                this.outputArea.setText("\n"+outputMsg);
            }
        }
    }

    private void applyPatchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_applyPatchBtnActionPerformed
        ActionDTO ad = PatchUtil.applyPatch(this.appDir, fileListModel.get(0),matchAppRootBox.isSelected());
        outputArea.setText(ad.getMessage());
    }

    private void selectAppBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectAppBtnActionPerformed
        JFileChooser folderChooser = new JFileChooser();
        folderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        folderChooser.setDialogTitle("Select extracted WAR/JAR root dir");

        int result = folderChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFolder = folderChooser.getSelectedFile();
            this.appDir=selectedFolder.getAbsolutePath();
            String[] appDirParts = appDir.split("\\\\");
            String appName = appDirParts[appDirParts.length-1];
            this.customAppName.setText(appName);
            outputArea.setText("App folder selected: "+this.appDir);
            if(Util.isListModelNotEmpty(fileListModel) && fileListModel.get(0).endsWith(".zip") && Util.areStringsValid(this.appDir)){
                this.verifyPatchBtn.setEnabled(true);
            }
        } else {
            this.applyPatchBtn.setEnabled(false);
            this.verifyPatchBtn.setEnabled(false);
            this.readPatchBtn.setEnabled(false);
            this.appDir="";
            this.customAppName.setText("");
            outputArea.setText("App folder selected: none");
        }
    }//GEN-LAST:event_selectAppBtnActionPerformed

    private void clearOutputBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearOutputBtnActionPerformed
      this.outputArea.setText("");
    }//GEN-LAST:event_clearOutputBtnActionPerformed

    private void readPatchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_readPatchBtnActionPerformed
        List<String> patchFiles = PatchUtil.readPatch(this.patchPath);
        String files = UIUtils.stringListToOutputAreaMsg(patchFiles);
        String outputText = UIUtils.encloseTextInParagraph(files, "Arial", "16px", "green", "Files in patch:", true);
        this.outputArea.setText(outputText);
    }//GEN-LAST:event_readPatchBtnActionPerformed

    private void verifyPatchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verifyPatchBtnActionPerformed
        ActionDTO actionDTO = PatchUtil.verifyPatch(fileListModel.get(0), this.appDir,matchAppRootBox.isSelected());
        String msgColor = "";
        String outputMsg = "";
        if(actionDTO.getSuccessful()){
            this.applyPatchBtn.setEnabled(true);
            msgColor="green";
            outputMsg = actionDTO.getMessage();

        } else {
            this.applyPatchBtn.setEnabled(false);
            msgColor="red";
            if(Util.areStringsValid(actionDTO.getMessage())){
                outputMsg = actionDTO.getMessage()+"<br>";
            }
            String concatenatedMsg = UIUtils.stringListToOutputAreaMsg(actionDTO.messageList);
            if(Util.areStringsValid(concatenatedMsg)){
                outputMsg=outputMsg+"<br>"+concatenatedMsg;
            }
        }
        String outputText = UIUtils.encloseTextInParagraph(outputMsg, "Arial", "12px", msgColor, "Verification Result:", true);
        this.outputArea.setText(outputText);

    }//GEN-LAST:event_verifyPatchBtnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AppScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AppScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AppScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AppScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AppScreen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addFileBtn;
    private javax.swing.JLabel appNameLabel;
    private javax.swing.JLabel appNameLabel1;
    private javax.swing.JLabel appNameLabel2;
    private javax.swing.JComboBox<String> appNameSelector;
    private javax.swing.JButton applyPatchBtn;
    private javax.swing.JButton clearOutputBtn;
    private javax.swing.JButton createPatchBtn;
    private javax.swing.JTextField customAppName;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JCheckBox matchAppRootBox;
    private javax.swing.JTextPane outputArea;
    private javax.swing.JButton readPatchBtn;
    private javax.swing.JButton rmFileBtn;
    private javax.swing.JButton selectAppBtn;
    private javax.swing.JList<String> selectedFilesList;
    private javax.swing.JButton verifyPatchBtn;
    // End of variables declaration//GEN-END:variables
}
