/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlybienbanclientView;

import entity.Meeting;
import entity.Report;
import entity.ReportPart;
import entity.User;
import helpfile.CheckReportPart;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import quanlybienbanclientController.MeetingController;
import quanlybienbanclientController.PermissionController;
import quanlybienbanclientController.ReportController;
import quanlybienbanclientController.ReportPartController;
import quanlybienbanclientController.UserController;
import registry.Register;
import remoteInterface.RemoteInterface;
import remoteInterface.RemoteStaffInterface;

/**
 *
 * @author thanhdovan
 */
public class GUIStaffClient extends javax.swing.JFrame {
    private RemoteStaffImpl remoteStaffImpl;
    private final UserController userController;
    private final PermissionController permissionController;
    private final MeetingController meetingController;
    private final ReportPartController reportPartController;
    private final ReportController reportController;
    private Meeting meetingSelected;
    private File selectedFile;
    public static User user;
    public static void updateMeetingTable(List<Meeting> list){
        Object[] column = {"Meeting Id", "Meeting Title"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(column);
        try {
            for (Meeting u : list ){
                Object[] row = { "MID"+u.getId(), u.getTitle()};
                model.addRow(row);
            }
            GUIStaffClient.meetingTable.setModel(model);
        } catch (Exception ex) {
            Logger.getLogger(GUIAdminClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void updateReportPartTable(List<ReportPart> list){
        Object[] column = {"Id","File Uploaded"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(column);
        try {
            for (ReportPart rp : list ){
                Object[] row = { rp.getId(), rp.getFileName() };
                model.addRow(row);
            }
            GUIStaffClient.reportPartTable.setModel(model);
        } catch (Exception ex) {
            Logger.getLogger(GUIAdminClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Creates new form GUIStaffClient
     */
    public GUIStaffClient() {
        try {
            remoteStaffImpl = new RemoteStaffImpl();
        } catch (RemoteException | NotBoundException | MalformedURLException ex) {
            Logger.getLogger("Can not init callback Object!");
        }
        userController = new UserController();
        permissionController = new PermissionController();
        meetingController = new MeetingController();
        reportPartController = new ReportPartController();
        reportController = new ReportController();
        initComponents();
        this.nameLabel.setText(GUIStaffClient.user.getUsername());
        List<Meeting> list = meetingController.getMeetings();
        List<Meeting> listHavePermission = new ArrayList<>();
            for(Meeting m: list){
                String permission = permissionController.getPermission(user, m);
                if ("r".equals(permission) || "w".equals(permission) || "u".equals(permission)){
                    listHavePermission.add(m);
                }
            }
        GUIStaffClient.updateMeetingTable(listHavePermission);
        GUIStaffClient.meetingTable.setAutoCreateRowSorter(true);
        GUIStaffClient.reportPartTable.setAutoCreateRowSorter(true);
        this.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e)
            {
                try {
                    remoteStaffImpl.h.removeRemoteStaffInterface(remoteStaffImpl);
                } catch (RemoteException ex) {
                    System.out.println("Can not remove remote staff interface!");
                } finally{
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        logoutButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        meetingTable = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        meetingIdTF = new javax.swing.JTextField();
        meetingTitleTF = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        fileNameTextField = new javax.swing.JTextField();
        chooseButton = new javax.swing.JButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        uploadButton = new javax.swing.JButton();
        viewReportButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        generateReportButton = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        reportPartTable = new javax.swing.JTable();
        deleteUploadButton = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        filePreviewTextArea = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jRadioButton3 = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLabel1.setText("You are logging in as Staff");

        nameLabel.setForeground(new java.awt.Color(21, 101, 241));
        nameLabel.setText("jLabel2");

        logoutButton.setText("Logout");
        logoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutButtonActionPerformed(evt);
            }
        });

        meetingTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Meeting ID", "Meeting Title"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        meetingTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                meetingTableMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(meetingTable);

        jLabel2.setText("Meeting title");

        jLabel3.setText("Meeting Id");

        meetingIdTF.setEditable(false);

        meetingTitleTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                meetingTitleTFActionPerformed(evt);
            }
        });

        jLabel4.setText("Upload a report file");

        fileNameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileNameTextFieldActionPerformed(evt);
            }
        });

        chooseButton.setText("Choose a file");
        chooseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseButtonActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setText("Person-[TimeBegin~TimeEnd]");

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("Content[TimeBegin~TimeEnd]");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        uploadButton.setText("Upload File");
        uploadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uploadButtonActionPerformed(evt);
            }
        });

        viewReportButton.setText("View Report");
        viewReportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewReportButtonActionPerformed(evt);
            }
        });

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        generateReportButton.setText("Generate Report");
        generateReportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateReportButtonActionPerformed(evt);
            }
        });

        reportPartTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "File Uploaded", "File Type"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        reportPartTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                reportPartTableMousePressed(evt);
            }
        });
        jScrollPane3.setViewportView(reportPartTable);

        deleteUploadButton.setText("Delete File Selected");
        deleteUploadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteUploadButtonActionPerformed(evt);
            }
        });

        jLabel5.setText("Preview File selected");

        filePreviewTextArea.setEditable(false);
        filePreviewTextArea.setColumns(20);
        filePreviewTextArea.setRows(5);
        jScrollPane4.setViewportView(filePreviewTextArea);

        jLabel6.setText("Preview File Upload");

        jLabel7.setText("(only file .txt)");

        buttonGroup1.add(jRadioButton3);
        jRadioButton3.setText("Transcript");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(logoutButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRadioButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jRadioButton3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(uploadButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(meetingTitleTF, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(generateReportButton, javax.swing.GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)
                            .addComponent(viewReportButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(fileNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(chooseButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jScrollPane2)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel6)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel4)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel7)))
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jRadioButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(6, 6, 6)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(deleteUploadButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(meetingIdTF, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane4)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(nameLabel)
                            .addComponent(logoutButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fileNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chooseButton))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(deleteUploadButton)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(meetingIdTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(meetingTitleTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jRadioButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(generateReportButton)
                    .addComponent(jRadioButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(uploadButton)
                    .addComponent(viewReportButton))
                .addGap(7, 7, 7))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void meetingTableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_meetingTableMousePressed
        int row = GUIStaffClient.meetingTable.getSelectedRow();
        meetingSelected = meetingController.getMeeting(Integer.parseInt(GUIStaffClient.meetingTable.getValueAt(row, 0).toString().substring(3)));
        this.meetingIdTF.setText(GUIStaffClient.meetingTable.getValueAt(row, 0).toString());
        this.meetingTitleTF.setText(GUIStaffClient.meetingTable.getValueAt(row, 1).toString());
        this.filePreviewTextArea.setText("");
        List<ReportPart> listReportPartPC = reportPartController.getReportPartIds(0, Integer.parseInt(GUIStaffClient.meetingTable.getValueAt(row, 0).toString().substring(3)));
        List<ReportPart> listReportPartCT = reportPartController.getReportPartIds(1, Integer.parseInt(GUIStaffClient.meetingTable.getValueAt(row, 0).toString().substring(3)));
        List<ReportPart> listReportPartTr = reportPartController.getReportPartIds(2, Integer.parseInt(GUIStaffClient.meetingTable.getValueAt(row, 0).toString().substring(3)));
        List<ReportPart> listAllReportPart = new ArrayList<>(listReportPartPC);
        listAllReportPart.addAll(listReportPartCT);
        listAllReportPart.addAll(listReportPartTr);
        GUIStaffClient.updateReportPartTable(listAllReportPart);
    }//GEN-LAST:event_meetingTableMousePressed

    private void chooseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooseButtonActionPerformed
        JFileChooser jfc = new JFileChooser();
        jfc.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = jfc.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFile = jfc.getSelectedFile();
            if (!selectedFile.getName().endsWith(".txt")) {
                JOptionPane.showMessageDialog(null, "Invalid File Type");
            } else {
                this.fileNameTextField.setText(selectedFile.getAbsolutePath());
                System.out.println("Selected file: " + selectedFile.getAbsolutePath());
                BufferedInputStream bufferedStream;
                try {
                    bufferedStream = new BufferedInputStream(new FileInputStream(selectedFile));
                    int nextByte;
                    StringBuffer localBuffer = new StringBuffer();

                    while (-1 != (nextByte = bufferedStream.read())) {
                        char nextChar = (char) nextByte;
                        localBuffer.append(nextChar);
                    }
                    this.jTextArea1.setText(localBuffer.toString());
                    } catch (FileNotFoundException ex) {
                    System.err.println(ex);
                } catch (IOException ex) {
                    System.err.println(ex);
                }
            }

        }
    }//GEN-LAST:event_chooseButtonActionPerformed

    private void uploadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uploadButtonActionPerformed
        final int PERSONCONTENT=0, CONTENTTIME=1, TRANSCRIPT=2;
        if (GUIStaffClient.meetingTable.getSelectedRow() == -1){
            JOptionPane.showMessageDialog(rootPane, "Please choose a meeting first!");
            return;
        }
        int row = GUIStaffClient.meetingTable.getSelectedRow();
        int meetingId = Integer.parseInt(GUIStaffClient.meetingTable.getValueAt(row, 0).toString().substring(3));
        Meeting meeting = meetingController.getMeeting(meetingId);
        String permission = permissionController.getPermission(GUIStaffClient.user, meeting);
        if ("u".equals(permission)){
            if("".equals(this.fileNameTextField.getText())){
                JOptionPane.showMessageDialog(rootPane, "Please choose a file first!");
                return;
            }
            if(JOptionPane.showConfirmDialog(rootPane, "Are you sure?","",JOptionPane.YES_NO_OPTION) == 0){
                
                ReportPart reportPart = new ReportPart();
                reportPart.setMeetingId(Integer.parseInt(this.meetingIdTF.getText().substring(3)));
                if(this.jRadioButton1.isSelected()){
                    reportPart.setType(PERSONCONTENT);
                    if(!CheckReportPart.checkReportPart(this.jTextArea1.getText(), PERSONCONTENT))
                    {   
                        JOptionPane.showMessageDialog(rootPane, "Invalid PC Type");
                        return;
                    }
                }
                else if(this.jRadioButton2.isSelected())
                {
                    reportPart.setType(CONTENTTIME);
                    if(!CheckReportPart.checkReportPart(this.jTextArea1.getText(), CONTENTTIME)){
                        JOptionPane.showMessageDialog(rootPane, "Invalid CT Type");
                        return;
                    }
                }
                else{
                    reportPart.setType(TRANSCRIPT);
                    if(!CheckReportPart.checkReportPart(this.jTextArea1.getText(), TRANSCRIPT)){
                        JOptionPane.showMessageDialog(rootPane, "Invalid transcipt Type");
                        return;
                    }
                }
                reportPart.setFileName(selectedFile.getName());
                reportPart.setContent(selectedFile);
                int i = reportPartController.uploadFile(reportPart);
                if( i > 0 ){
                    JOptionPane.showMessageDialog(rootPane, "Success!");
                    this.jTextArea1.setText("");
                    this.fileNameTextField.setText("");
                    this.buttonGroup1.clearSelection();
                    List<ReportPart> listReportPartPC = reportPartController.getReportPartIds(0, Integer.parseInt(GUIStaffClient.meetingTable.getValueAt(row, 0).toString().substring(3)));
                    List<ReportPart> listReportPartCT = reportPartController.getReportPartIds(1, Integer.parseInt(GUIStaffClient.meetingTable.getValueAt(row, 0).toString().substring(3)));
                    List<ReportPart> listReportPartTr = reportPartController.getReportPartIds(2, Integer.parseInt(GUIStaffClient.meetingTable.getValueAt(row, 0).toString().substring(3)));
                    List<ReportPart> listAllReportPart = new ArrayList<>(listReportPartPC);
                    listAllReportPart.addAll(listReportPartCT);
                    listAllReportPart.addAll(listReportPartTr);
        //            GUIStaffClient.updateReportPartTable(listAllReportPart);
                    try {
                        remoteStaffImpl.h.staffUpdateReportPartTable(listAllReportPart, meetingId);
                    } catch (RemoteException ex) {
                        Logger.getLogger("Khong update duoc table!");
                    }
                }
                else
                    JOptionPane.showMessageDialog(rootPane, "Failed! Try again!");
            }

            List<ReportPart> listReportPartPC = reportPartController.getReportPartIds(0, Integer.parseInt(GUIStaffClient.meetingTable.getValueAt(row, 0).toString().substring(3)));
            List<ReportPart> listReportPartCT = reportPartController.getReportPartIds(1, Integer.parseInt(GUIStaffClient.meetingTable.getValueAt(row, 0).toString().substring(3)));
            List<ReportPart> listReportPartTr = reportPartController.getReportPartIds(2, Integer.parseInt(GUIStaffClient.meetingTable.getValueAt(row, 0).toString().substring(3)));
            List<ReportPart> listAllReportPart = new ArrayList<>(listReportPartPC);
            listAllReportPart.addAll(listReportPartTr);
            listAllReportPart.addAll(listReportPartCT);
//            GUIStaffClient.updateReportPartTable(listAllReportPart);
            try {
                remoteStaffImpl.h.staffUpdateReportPartTable(listAllReportPart, meetingId);
            } catch (RemoteException ex) {
                Logger.getLogger("Khong update duoc table!");
            }
        }
        else {
            int creatorId = meetingController.getMeetingCreatorId(meeting);
            User x = userController.getUser(creatorId);
            JOptionPane.showMessageDialog(rootPane, "You don't have permission to upload!\n"
                    + "Contact to " + x.getUsername() + " to have permission!");
        }
    }//GEN-LAST:event_uploadButtonActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void fileNameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileNameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fileNameTextFieldActionPerformed

    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutButtonActionPerformed
        if(JOptionPane.showConfirmDialog(rootPane,"Are you sure?", "", JOptionPane.YES_NO_OPTION) == 0){
            LoginForm lg = new LoginForm();
            lg.setVisible(true);
            this.setVisible(false);
            try{
                RemoteInterface stub = Register.registry();
                stub.clientLogoutMessage(GUIStaffClient.user);
            } catch (RemoteException | NotBoundException ex) {
                Logger.getLogger(GUIAdminClient.class.getName()).log(Level.SEVERE, null, ex);
            }
            GUIStaffClient.user = new User();
        }
    }//GEN-LAST:event_logoutButtonActionPerformed

    private void generateReportButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateReportButtonActionPerformed
        int row = GUIStaffClient.meetingTable.getSelectedRow();
        if (row == -1){
            JOptionPane.showMessageDialog(rootPane, "Choose a meeting first!");
        }
        else{
            int meetingId = Integer.parseInt(GUIStaffClient.meetingTable.getValueAt(row, 0).toString().substring(3));
            Meeting meeting = meetingController.getMeeting(meetingId);
            String permission = permissionController.getPermission(GUIStaffClient.user, meeting);
            if ("u".equals(permission)){
                GenerateReport.user = GUIStaffClient.user;
                GenerateReport.meeting = meetingController.getMeeting(Integer.parseInt(this.meetingIdTF.getText().substring(3)));
                List<ReportPart> reportPartPersonContents = reportPartController.getReportPartIds(0, meetingController.getMeeting(Integer.parseInt(this.meetingIdTF.getText().substring(3))).getId());
                List<ReportPart> reportPartContentTimes = reportPartController.getReportPartIds(1, meetingController.getMeeting(Integer.parseInt(this.meetingIdTF.getText().substring(3))).getId());
                if(reportPartPersonContents.isEmpty()){
                    JOptionPane.showMessageDialog(rootPane, "Haven't have PersonTime file yet! Upload file first!");
                    return;
                }
                if(reportPartContentTimes.isEmpty()){
                    JOptionPane.showMessageDialog(rootPane, "Haven't have ContentTime file yet! Upload file first!");
                    return;
                }
                GenerateReport generateReport = new GenerateReport();
                generateReport.setVisible(true);
            }
            else {
                int creatorId = meetingController.getMeetingCreatorId(meeting);
                User x = userController.getUser(creatorId);
                JOptionPane.showMessageDialog(rootPane, "You don't have permission to upload!\n"
                    + "Contact to " + x.getUsername() + " to have permission!");
            }
        }
    }//GEN-LAST:event_generateReportButtonActionPerformed

    private void reportPartTableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reportPartTableMousePressed
        int row = GUIStaffClient.reportPartTable.getSelectedRow();
        int reportPartId = Integer.parseInt(GUIStaffClient.reportPartTable.getValueAt(row, 0).toString());
        String reportPartContent = reportPartController.getReportPartContent(reportPartId);
        this.filePreviewTextArea.setText(reportPartContent);
    }//GEN-LAST:event_reportPartTableMousePressed

    private void deleteUploadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteUploadButtonActionPerformed
        int row = GUIStaffClient.reportPartTable.getSelectedRow();
        int meetingRow = GUIStaffClient.meetingTable.getSelectedRow();
        int meetingId = Integer.parseInt(GUIStaffClient.meetingTable.getValueAt(meetingRow, 0).toString().substring(3));
        if(row == -1){
            JOptionPane.showMessageDialog(rootPane, "Choose a report part file first!");
        }
        else{
            if(JOptionPane.showConfirmDialog(rootPane, "Are you sure?", "", JOptionPane.YES_NO_OPTION)==0){
                reportPartController.deleteReportPart(Integer.parseInt(GUIStaffClient.reportPartTable.getValueAt(row, 0).toString()));
                List<ReportPart> listReportPartPC = reportPartController.getReportPartIds(0, meetingId);
                List<ReportPart> listReportPartCT = reportPartController.getReportPartIds(1, meetingId);
                List<ReportPart> listAllReportPart = new ArrayList<>(listReportPartPC);
                listAllReportPart.addAll(listReportPartCT);
                try {
                    remoteStaffImpl.h.staffUpdateReportPartTable(listAllReportPart, meetingId);
                } catch (RemoteException ex) {
                    Logger.getLogger("Khong update duoc table!");
                }
                this.filePreviewTextArea.setText("");
            }
        }
    }//GEN-LAST:event_deleteUploadButtonActionPerformed

    private void viewReportButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewReportButtonActionPerformed
        if (GUIStaffClient.meetingTable.getSelectedRow() == -1){
            JOptionPane.showMessageDialog(rootPane, "Choose a meeting first!");
        }
        else {
            
            GUIViewReport.meeting = meetingController.getMeeting(Integer.parseInt(this.meetingIdTF.getText().substring(3)));
            List<Report> reports = reportController.getReports(meetingController.getMeeting(Integer.parseInt(this.meetingIdTF.getText().substring(3))).getId());
            if(reports.isEmpty()){
                JOptionPane.showMessageDialog(rootPane, "Haven't have report yet! Generate report first!");
                return;
            }
            GUIViewReport.user= GUIStaffClient.user;
            GUIViewReport viewReport = new GUIViewReport();
            viewReport.setVisible(true);
        }
    }//GEN-LAST:event_viewReportButtonActionPerformed

    private void meetingTitleTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_meetingTitleTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_meetingTitleTFActionPerformed
    class RemoteStaffImpl extends UnicastRemoteObject implements RemoteStaffInterface{
        public RemoteInterface h;
        private String username;
        public RemoteStaffImpl() throws RemoteException, NotBoundException, MalformedURLException{
            h = Register.registry();
            h.addRemoteStaffInterface(this);
        }
        public String getUsername() {
            return username;
        }
        public void setUsername(String username) {
            this.username = username;
        }
        @Override
        public void updateMeetingTable(List<Meeting> list) throws RemoteException {
            SwingUtilities.invokeLater(new Runnable(){
                public void run(){
                    List<Meeting> listHavePermission = new ArrayList<>();
                    for(Meeting m: list){
                        String permission = permissionController.getPermission(user, m);
                        if ("r".equals(permission) || "w".equals(permission) || "u".equals(permission)){
                            listHavePermission.add(m);
                        }
                    }
                    GUIStaffClient.updateMeetingTable(listHavePermission);
                    GUIStaffClient.this.meetingIdTF.setText("");
                    GUIStaffClient.this.meetingTitleTF.setText("");
                }
            });
        }

        @Override
        public void updateReportPartTable(List<ReportPart> list, int meetingId) throws RemoteException {
            SwingUtilities.invokeLater(new Runnable(){
                public void run(){
                    if(GUIStaffClient.this.meetingSelected.getId() == meetingId){
                        GUIStaffClient.updateReportPartTable(list);
                        GUIStaffClient.this.filePreviewTextArea.setText("");
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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUIStaffClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUIStaffClient().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton chooseButton;
    private javax.swing.JButton deleteUploadButton;
    private javax.swing.JTextField fileNameTextField;
    private javax.swing.JTextArea filePreviewTextArea;
    private javax.swing.JButton generateReportButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JButton logoutButton;
    private javax.swing.JTextField meetingIdTF;
    public static javax.swing.JTable meetingTable;
    private javax.swing.JTextField meetingTitleTF;
    private javax.swing.JLabel nameLabel;
    public static javax.swing.JTable reportPartTable;
    private javax.swing.JButton uploadButton;
    private javax.swing.JButton viewReportButton;
    // End of variables declaration//GEN-END:variables
}
