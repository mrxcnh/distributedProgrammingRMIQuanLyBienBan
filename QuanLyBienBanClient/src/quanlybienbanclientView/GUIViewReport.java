/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlybienbanclientView;

import entity.Meeting;
import entity.PeopleEditReport;
import entity.Report;
import entity.User;
import quanlybienbanclientController.PermissionController;
import quanlybienbanclientController.ReportController;
import quanlybienbanclientController.UserController;
import remoteInterface.RemoteInterface;
import remoteInterface.RemoteReportInterface;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import registry.Register;


/**
 *
 * @author thanhdovan
 */
public class GUIViewReport extends javax.swing.JFrame {
    private final PermissionController permissionController;
    private final ReportController reportController;
    private final UserController userController;
    private RemoteReportImpl remoteReportImpl;
    private String content;
    private String selectedContent;
    Report reportSelected;
    public static User user;
    public static Meeting meeting;
    public static void updateReportTable(List<Report> list){
        Object[] column = {"ReportId", "ReportName", "TimeCreate", "Authors"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(column);
        try {
            for (Report u : list ){
                Object[] row = {u.getId(), u.getReportName(), u.getTimeCreate(), u.getAuthors()};
                model.addRow(row);
            }
            GUIViewReport.reportTable.setModel(model);
        } catch (Exception ex) {
            Logger.getLogger(GUIAdminClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void updateUserEdittingTable(List<User> list){
        Object[] column = {"UserId", "Username", "Position"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(column);
        try {
            for (User u : list ){
                Object[] row = {u.getId(), u.getUsername(), u.getPosition()};
                model.addRow(row);
            }
            GUIViewReport.userEdittingTable.setModel(model);
        } catch (Exception ex) {
            Logger.getLogger("Error! Can not update table user editting!");
        }
    }
    public static void updateReportContent(String content){
        GUIViewReport.reportContentTextArea.setText(content);
    }
    /**
     * Creates new form GUIViewReport
     */
    public GUIViewReport() {
        try {
            remoteReportImpl = new RemoteReportImpl();
        } catch (RemoteException | NotBoundException | MalformedURLException ex) {
            Logger.getLogger("Can not init callback Object!");
        }
        reportController = new ReportController();
        userController = new UserController();
        permissionController = new PermissionController();
        initComponents();
        this.jLabel7.setText(user.getUsername());
        if("staff".equals(GUIViewReport.user.getPosition())){
            this.deleteReportButton.setVisible(false);
        }
        this.jLabel2.setText("MID"+meeting.getId());
        List<Report> reports = reportController.getReports(meeting.getId());
        GUIViewReport.updateReportTable(reports);
        this.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e)
            {
                int row = GUIViewReport.reportTable.getSelectedRow();
                if (row != -1){
                    if(JOptionPane.showConfirmDialog(rootPane, "Did you save your changes?", "", JOptionPane.YES_NO_OPTION) == 0){
                        try {
                            remoteReportImpl.h.removeRemoteReportInterface(remoteReportImpl);
                        } catch (RemoteException ex) {
                            System.out.println("Can not remove remote report interface!");
                        }
                        PeopleEditReport per = new PeopleEditReport();
                        per.setUserId(user.getId());
                        per.setReportId(reportSelected.getId());
                        int peopleEditId = reportController.getPeopleEdit(per);
                        if (peopleEditId == 0){
                            e.getWindow().dispose();
                        }
                        else{
                            reportController.removePeopleEdit(peopleEditId);
                            e.getWindow().dispose();
                        }
                    }
                }
                else{
                    try {
                        remoteReportImpl.h.removeRemoteReportInterface(remoteReportImpl);
                    } catch (RemoteException ex) {
                        System.out.println("Can not remove remote report interface!");
                    }
                    e.getWindow().dispose();
                }
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        editReportButton = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        reportTable = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        reportContentTextArea = new javax.swing.JTextArea();
        quitEdittingButton = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        userEdittingTable = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        deleteReportButton = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jLabel1.setText("Report Meeting");

        jLabel2.setText("jLabel2");

        editReportButton.setText("Edit Report");
        editReportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editReportButtonActionPerformed(evt);
            }
        });

        jButton3.setText("Export");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        reportTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ReportId", "ReportName", "TimeCreate", "Authors"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        reportTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                reportTableMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(reportTable);

        reportContentTextArea.setEditable(false);
        reportContentTextArea.setColumns(20);
        reportContentTextArea.setRows(5);
        reportContentTextArea.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                reportContentTextAreaCaretUpdate(evt);
            }
        });
        reportContentTextArea.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                reportContentTextAreaKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                reportContentTextAreaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                reportContentTextAreaKeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(reportContentTextArea);

        quitEdittingButton.setText("Quit Editting");
        quitEdittingButton.setEnabled(false);
        quitEdittingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitEdittingButtonActionPerformed(evt);
            }
        });

        userEdittingTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "UserId", "Username"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        userEdittingTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                userEdittingTableMousePressed(evt);
            }
        });
        jScrollPane4.setViewportView(userEdittingTable);

        jLabel3.setText("User are editting this report");

        jLabel4.setText("Report");

        deleteReportButton.setText("Delete Report");
        deleteReportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteReportButtonActionPerformed(evt);
            }
        });

        jLabel6.setText("Login as:");

        jLabel7.setText("jLabel7");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(deleteReportButton))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(editReportButton, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(quitEdittingButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel4)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addGap(13, 13, 13)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(editReportButton)
                        .addComponent(quitEdittingButton)
                        .addComponent(deleteReportButton))
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(96, 96, 96))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 633, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void reportTableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reportTableMousePressed
        if (GUIViewReport.reportTable.getRowSelectionAllowed() == true){
            GUIViewReport.reportContentTextArea.setText("");
            int row = GUIViewReport.reportTable.getSelectedRow();
            int reportId = Integer.parseInt(GUIViewReport.reportTable.getValueAt(row, 0).toString());
            this.jLabel5.setText(GUIViewReport.reportTable.getValueAt(row, 0).toString());
            reportSelected = reportController.getReport(reportId);
            String newcontent = reportController.getReportContent(reportId);
            selectedContent = newcontent;
            content = newcontent;
            GUIViewReport.reportContentTextArea.append(newcontent);
            List<Integer> userIds = reportController.getIdOfUserEdit(reportId);
            List<User> userEdittings = new ArrayList<>();
            for (int i : userIds){
                User u = userController.getUser(i);
                userEdittings.add(u);
            }
            GUIViewReport.updateUserEdittingTable(userEdittings);
        }
    }//GEN-LAST:event_reportTableMousePressed

    private void editReportButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editReportButtonActionPerformed
        int row = GUIViewReport.reportTable.getSelectedRow();
        content = GUIViewReport.reportContentTextArea.getText();
        if (row != -1){
            int reportId = Integer.parseInt(GUIViewReport.reportTable.getValueAt(row, 0).toString());
            List<Integer> checkUserIds = reportController.getIdOfUserEdit(reportId);
            if(!checkUserIds.isEmpty()){
                JOptionPane.showMessageDialog(rootPane, "Someone is editting this report! Can not access!");
                return;
            }
            if (GUIViewReport.user.getId()==GUIViewReport.meeting.getUserCreateId()||"w".equals(permissionController.getPermission(user, meeting))||"u".equals(permissionController.getPermission(user, meeting))){    
                PeopleEditReport per = new PeopleEditReport();
                per.setUserId(user.getId());
                per.setReportId(reportId);
                reportController.addPeopleEdit(per);
                List<Integer> userIds = reportController.getIdOfUserEdit(reportId);
                List<User> userEdittings = new ArrayList<>();
                for (int i : userIds){
                    User u = userController.getUser(i);
                    userEdittings.add(u);
                }
                try {
                    remoteReportImpl.h.updateUserEdittingTable(userEdittings, reportId);
                    GUIViewReport.reportContentTextArea.setEditable(true);
                    this.deleteReportButton.setEnabled(false);
                    GUIViewReport.reportTable.setEnabled(false);
                    GUIViewReport.reportTable.setRowSelectionAllowed(false);
                    this.editReportButton.setEnabled(false);
                    this.quitEdittingButton.setEnabled(true);
                } catch (RemoteException ex) {
                    Logger.getLogger("Can not update User editting table!");
                }
            }
            else{
                JOptionPane.showMessageDialog(rootPane, "You dont have permission for editting this report!");
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Choose a report first!");
        }
    }//GEN-LAST:event_editReportButtonActionPerformed

    private void reportContentTextAreaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_reportContentTextAreaKeyPressed

    }//GEN-LAST:event_reportContentTextAreaKeyPressed

    private void quitEdittingButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitEdittingButtonActionPerformed
        this.deleteReportButton.setEnabled(true);
        GUIViewReport.reportContentTextArea.setEditable(false);
        GUIViewReport.reportTable.setEnabled(true);
        GUIViewReport.reportTable.setRowSelectionAllowed(true);
        PeopleEditReport per = new PeopleEditReport();
        per.setUserId(user.getId());
        int reportId = reportSelected.getId();
        List<Integer> tempuserIds = reportController.getIdOfUserEdit(reportId);
        String author = "";
        for (int i : tempuserIds){
            User u = userController.getUser(i);
            author+=u.getUsername()+", ";
        }
        per.setReportId(reportId);
        int peopleEditId = reportController.getPeopleEdit(per);
        reportController.removePeopleEdit(peopleEditId);
        this.editReportButton.setEnabled(true);
        this.quitEdittingButton.setEnabled(false);
        String newContent = GUIViewReport.reportContentTextArea.getText();
        if (newContent.equals(this.selectedContent)){
            JOptionPane.showMessageDialog(rootPane, "No change!");
            List<Integer> userIds = reportController.getIdOfUserEdit(reportId);
            List<User> userEdittings = new ArrayList<>();
            for (int i : userIds){
                User u = userController.getUser(i);
                userEdittings.add(u);
            }
            try {
                remoteReportImpl.h.updateUserEdittingTable(userEdittings, reportId);
            } catch (RemoteException ex) {
                Logger.getLogger("Can not update User editting table!");
            }
            GUIViewReport.reportContentTextArea.setEditable(false);
            GUIViewReport.reportTable.setEnabled(true);
            this.editReportButton.setEnabled(true);
            this.quitEdittingButton.setEnabled(false);
            return;
        }
        Report report = reportController.getReport(reportId);
        report.setReportContent(newContent);
        author = author.substring(0,author.length()-2);
        report.setAuthors(author);
        int result = reportController.addReport(report);
        if (result == 0){
            JOptionPane.showMessageDialog(rootPane, "Failed! Try again!");
            return;
        }
        else{
            JOptionPane.showMessageDialog(rootPane, "Success!");
        }
        List<Integer> userIds = reportController.getIdOfUserEdit(reportId);
        List<User> userEdittings = new ArrayList<>();
        for (int i : userIds){
            User u = userController.getUser(i);
            userEdittings.add(u);
        }
        List<Report> list = reportController.getReports(GUIViewReport.meeting.getId());
        try {
            remoteReportImpl.h.updateUserEdittingTable(userEdittings, reportId);
            remoteReportImpl.h.updateReportTable(list);
        } catch (RemoteException ex) {
            Logger.getLogger("Can not update User editting table!");
        }
        GUIViewReport.reportContentTextArea.setEditable(false);
        GUIViewReport.reportTable.setEnabled(true);
        this.editReportButton.setEnabled(true);
        this.quitEdittingButton.setEnabled(false);
    }//GEN-LAST:event_quitEdittingButtonActionPerformed

    private void userEdittingTableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_userEdittingTableMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_userEdittingTableMousePressed

    private void reportContentTextAreaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_reportContentTextAreaKeyTyped
        
    }//GEN-LAST:event_reportContentTextAreaKeyTyped

    private void reportContentTextAreaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_reportContentTextAreaKeyReleased
        try {
            String newcontent = GUIViewReport.reportContentTextArea.getText();
            if (newcontent.length() - content.length() != 0){
                content = newcontent;
                remoteReportImpl.h.updateReportContent(newcontent, reportSelected.getId());
            }
        } catch (RemoteException ex) {
            Logger.getLogger("Can not update content of report");
        }
    }//GEN-LAST:event_reportContentTextAreaKeyReleased

    private void reportContentTextAreaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_reportContentTextAreaCaretUpdate
//        JTextArea editArea = (JTextArea)evt.getSource();
//        caretpos = editArea.getCaretPosition();
//        updateStatus(caretpos);
    }//GEN-LAST:event_reportContentTextAreaCaretUpdate

    private void deleteReportButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteReportButtonActionPerformed
        if(JOptionPane.showConfirmDialog(rootPane, "Are you sure?", "", JOptionPane.YES_NO_OPTION) == 0){
            if(reportController.getIdOfUserEdit(reportSelected.getId()).isEmpty()){
                int result = reportController.deleteReport(reportSelected.getId());
                if(result > 0){
                    JOptionPane.showMessageDialog(rootPane, "Success!");
                    List<Report> list = reportController.getReports(GUIViewReport.meeting.getId());
                    try {
                        remoteReportImpl.h.updateReportTable(list);
                    } catch (RemoteException ex) {
                        Logger.getLogger("Can not update User editting table!");
                    }
                }else{
                    JOptionPane.showMessageDialog(rootPane, "Failed! Try again!");
                }
            }
            else{
                JOptionPane.showMessageDialog(rootPane, "Can not delete! Some one are editting this!");
            }
        }
    }//GEN-LAST:event_deleteReportButtonActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (reportSelected == null) {
            JOptionPane.showMessageDialog(null, "Select a report first!");
            return;
        }
        List<Integer> userIds = reportController.getIdOfUserEdit(reportSelected.getId());
        if (!userIds.isEmpty()){
            JOptionPane.showMessageDialog(null, "Someone editting this! Cannot export!");
            return;
        }
        exportToWord();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void exportToWord() {
        if (reportSelected == null) {
            JOptionPane.showMessageDialog(null, "Select a report first!");
        } else if (JOptionPane.showConfirmDialog(rootPane, "Are you sure?", "", JOptionPane.YES_NO_OPTION) == 0) {
            reportController.exportReportToWord(reportSelected.getId());
        }
    }
    
    class RemoteReportImpl extends UnicastRemoteObject implements RemoteReportInterface{
        public RemoteInterface h;
        private Meeting meeting;
        private Report report;
        private User user;
        public RemoteReportImpl() throws RemoteException, NotBoundException, MalformedURLException {
            this.user = GUIViewReport.user;
            this.meeting = GUIViewReport.meeting;
            h = Register.registry();
            h.addRemoteReportInterface(this);
        }
        @Override
        public void updateReportTable(List<Report> list) throws RemoteException {
            SwingUtilities.invokeLater(new Runnable(){
                public void run(){
                    GUIViewReport.updateReportTable(list);
                    GUIViewReport.this.jLabel8.setText("Updated!");
                    java.util.Timer timer = new java.util.Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            GUIViewReport.this.jLabel8.setText("");
                        }
                    }, 3000);
                }
            });
        }

        @Override
        public void updateUserEdittingTable(List<User> list, int reportId) throws RemoteException {
            SwingUtilities.invokeLater(new Runnable(){
                public void run(){
                        if (GUIViewReport.this.reportSelected.getId() == reportId){
                            GUIViewReport.updateUserEdittingTable(list);
                        }
                    }
            });
        }

        @Override
        public void updateReportContent(String content, int reportId) throws RemoteException {
            SwingUtilities.invokeLater(new Runnable(){
                public void run() {
                    if (GUIViewReport.this.reportSelected.getId() == reportId){
                        GUIViewReport.updateReportContent(content);
                    }
                }
            });
        }
    
    }
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
            java.util.logging.Logger.getLogger(GUIViewReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUIViewReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUIViewReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUIViewReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUIViewReport().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton deleteReportButton;
    private javax.swing.JButton editReportButton;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JButton quitEdittingButton;
    public static javax.swing.JTextArea reportContentTextArea;
    public static javax.swing.JTable reportTable;
    public static javax.swing.JTable userEdittingTable;
    // End of variables declaration//GEN-END:variables
}
