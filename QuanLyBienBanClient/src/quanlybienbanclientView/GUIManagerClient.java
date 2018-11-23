/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlybienbanclientView;

import entity.Meeting;
import entity.Report;
import entity.User;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import remoteInterface.RemoteManagerInterface;

/**
 *
 * @author thanhdovan
 */
public class GUIManagerClient extends javax.swing.JFrame {
    
    private static PermissionController permissionController;
    private RemoteManagerImpl remoteManagerImpl;
    private Meeting meetingSelected;
    private final MeetingController meetingController;
    private final UserController userController;
    private final ReportPartController reportPartController;
    private final ReportController reportController;
    public static User user;
    public static void updateReporterTable(List<User> reporters){
        Object[] column = {"UserId", "Username", "User Position"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(column);
        try {
            for (User u : reporters ){
                Object[] row = {u.getId(), u.getUsername(), u.getPosition()};
                model.addRow(row);
            }
            GUIManagerClient.reporterTable.setModel(model);
        } catch (Exception ex) {
            Logger.getLogger(GUIAdminClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void updateTable(List<Meeting> list){
        Object[] column = {"Id", "Meeting", "Date", "Time Start"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(column);
        try {
            for (Meeting u : list ){
                Object[] row = {"MID"+u.getId(), u.getTitle(), u.getDate(), u.getTimeStart()};
                model.addRow(row);
            }
            GUIManagerClient.jTable1.setModel(model);
        } catch (Exception ex) {
            Logger.getLogger(GUIAdminClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public List<User> getUserDontHavePermission(Meeting meeting){
        List<User> allUsers = userController.getUsers();
        List<User> out = new ArrayList<>();
        for (User u: allUsers){
            if (permissionController.getPermission(u, meeting) == null && ("staff".equals(u.getPosition()) || "manager".equals(u.getPosition()))){
                out.add(u);
            }
        }
        int k = 0;
        for (int i = 0; i < out.size(); i++){
            if (out.get(i).getId() == meeting.getUserCreateId()){
                k=i;
                break;
            }
        }
        out.remove(k);
        return out;
    }
    public static void updateReporterComboBox(List<User> listReporter){
        GUIManagerClient.selectReporterComboBox.removeAll();
        for (User u : listReporter){
            GUIManagerClient.selectReporterComboBox.addItem(u.getId()+"-"+u.getUsername()+ "-"+ u.getPosition());
        }
    }
    public static void updateListPermission(List<User> users, Meeting meeting){
        Object[] column = {"UserId", "Username", "UserPermission"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(column);
        try {
            for (User u : users){
                String permission = permissionController.getPermission(u, meeting);
                if (permission!=null){
                    Object[] row = {u.getId(), u.getUsername(), permission};
                    model.addRow(row);
                }
            }
            GUIManagerClient.permissionTable.setModel(model);
        } catch (Exception ex) {
            Logger.getLogger("Can not update permission table!");
        }
    }
    public static void updateUserSharedComboBox(List<User> listUserDontHavePermission){
        GUIManagerClient.userSharedComboBox.removeAll();
        for (User u : listUserDontHavePermission){
            GUIManagerClient.userSharedComboBox.addItem(u.getId()+"-"+u.getUsername()+ "-"+ u.getPosition());
        }
    }
    /**
     * Creates new form GUIManagerClient
     */
    public GUIManagerClient() {
        try {
            remoteManagerImpl = new RemoteManagerImpl();
        } catch (RemoteException | NotBoundException | MalformedURLException ex) {
            Logger.getLogger("Can not init callback Object!");
        }
        initComponents();
        this.nameLabel.setText(user.getUsername());
        reportPartController = new ReportPartController();
        permissionController = new PermissionController();
        meetingController = new MeetingController();
        reportController = new ReportController();
        userController = new UserController();
        List<Meeting> list = meetingController.getMeetings();
        List<Meeting> listHavePermission = new ArrayList<>();
        for(Meeting m: list){
            String permission = permissionController.getPermission(user, m);
            if ("r".equals(permission) || "w".equals(permission)){
                listHavePermission.add(m);
            }
            int creatorId = meetingController.getMeetingCreatorId(m);
            if(creatorId == user.getId()){
                listHavePermission.add(m);
            }
        }
        GUIManagerClient.updateTable(listHavePermission);
        this.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e)
            {
                try {
                    remoteManagerImpl.h.removeRemoteManagerInterface(remoteManagerImpl);
                } catch (RemoteException ex) {
                    System.out.println("Can not remove remote manager interface!");
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
        welcomeLabel = new javax.swing.JLabel();
        logoutButton = new javax.swing.JButton();
        nameLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        meetingText = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        timeText = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        addMeetingButton = new javax.swing.JButton();
        viewReportButton = new javax.swing.JButton();
        dateChooserCombo1 = new datechooser.beans.DateChooserCombo();
        editButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        addReporterButton = new javax.swing.JButton();
        shareButton = new javax.swing.JButton();
        userSharedComboBox = new javax.swing.JComboBox<>();
        readOnlyRadioButton = new javax.swing.JRadioButton();
        writeRadioButton = new javax.swing.JRadioButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        reporterTable = new javax.swing.JTable();
        selectReporterComboBox = new javax.swing.JComboBox<>();
        deleteReporterButton = new javax.swing.JButton();
        deletePermissionButton = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        permissionTable = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        welcomeLabel.setText("You are loggin in as Manager");

        logoutButton.setText("Logout");
        logoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutButtonActionPerformed(evt);
            }
        });

        nameLabel.setForeground(new java.awt.Color(21, 101, 241));
        nameLabel.setText("jLabel1");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Id", "Meeting", "Date", "TimeStart"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable1MousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setText("Meeting");

        meetingText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                meetingTextActionPerformed(evt);
            }
        });

        jLabel2.setText("Date");

        timeText.setText("HH:mm:ss");
        timeText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timeTextActionPerformed(evt);
            }
        });

        jLabel3.setText("Time");

        addMeetingButton.setText("Add meeting");
        addMeetingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addMeetingButtonActionPerformed(evt);
            }
        });

        viewReportButton.setText("View Report");
        viewReportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewReportButtonActionPerformed(evt);
            }
        });

        editButton.setText("Save Editting");
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });

        deleteButton.setText("Delete meeting");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        jButton1.setText("Clear");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        addReporterButton.setText("Add Reporter");
        addReporterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addReporterButtonActionPerformed(evt);
            }
        });

        shareButton.setText("Share");
        shareButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shareButtonActionPerformed(evt);
            }
        });

        buttonGroup1.add(readOnlyRadioButton);
        readOnlyRadioButton.setText("Read-Only");
        readOnlyRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                readOnlyRadioButtonActionPerformed(evt);
            }
        });

        buttonGroup1.add(writeRadioButton);
        writeRadioButton.setText("Write");
        writeRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                writeRadioButtonActionPerformed(evt);
            }
        });

        reporterTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "UserId", "UserName", "UserPosition"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        reporterTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                reporterTableMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(reporterTable);
        if (reporterTable.getColumnModel().getColumnCount() > 0) {
            reporterTable.getColumnModel().getColumn(1).setHeaderValue("UserName");
        }

        selectReporterComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectReporterComboBoxActionPerformed(evt);
            }
        });

        deleteReporterButton.setText("Delete Reporter");
        deleteReporterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteReporterButtonActionPerformed(evt);
            }
        });

        deletePermissionButton.setText("Delete Permission");
        deletePermissionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletePermissionButtonActionPerformed(evt);
            }
        });

        permissionTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "UserId", "Username", "UserPermission"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        permissionTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                permissionTableMousePressed(evt);
            }
        });
        jScrollPane3.setViewportView(permissionTable);

        jLabel4.setText("Id");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(welcomeLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(nameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(logoutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(addMeetingButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(dateChooserCombo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(timeText)))
                                .addGap(1, 1, 1))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(editButton, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(addReporterButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(deleteButton, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                                    .addComponent(deleteReporterButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(selectReporterComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(meetingText)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(userSharedComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(writeRadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(deletePermissionButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(viewReportButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(readOnlyRadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(shareButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(welcomeLabel)
                    .addComponent(nameLabel)
                    .addComponent(logoutButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(addMeetingButton))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(meetingText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(dateChooserCombo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(timeText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(editButton)
                            .addComponent(deleteButton)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(selectReporterComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(deleteReporterButton)
                            .addComponent(addReporterButton)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(userSharedComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(readOnlyRadioButton)
                            .addComponent(shareButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(writeRadioButton)
                            .addComponent(deletePermissionButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(viewReportButton)))
                .addContainerGap())
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

    private void jTable1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MousePressed
        
        this.userSharedComboBox.removeAllItems();
        int row = GUIManagerClient.jTable1.getSelectedRow();
        int meetingId = Integer.parseInt(GUIManagerClient.jTable1.getValueAt(row, 0).toString().substring(3));
        this.jLabel5.setText(GUIManagerClient.jTable1.getValueAt(row, 0).toString());
        meetingSelected = meetingController.getMeeting(meetingId);
        List<User> us = this.getUserDontHavePermission(meetingSelected);
        for (User u : us){
            this.userSharedComboBox.addItem(u.getId()+" - "+u.getUsername()+" - "+u.getPosition());
        }
        this.meetingText.setText(meetingSelected.getTitle());
        Calendar cal = new GregorianCalendar();
        cal.setTime(meetingSelected.getDate());
        this.dateChooserCombo1.setSelectedDate(cal);
        this.timeText.setText(meetingSelected.getTimeStart().toString());

        //
        this.selectReporterComboBox.removeAllItems();
        List<Integer> reporterIds = meetingController.getReporterIds(meetingId);
        List<User> reporters = new ArrayList<>();
        if (reporterIds != null){
            for (Integer reporterId : reporterIds){

                User reporter = userController.getUser(reporterId);
                reporters.add(reporter);
            }
        }
        GUIManagerClient.updateReporterTable(reporters);

        List<Integer> userIds = new ArrayList<>();
        List<User> users = userController.getUsers();
        GUIManagerClient.updateListPermission(users, meetingSelected);
        for (User u : users){
            userIds.add(u.getId());
        }

        userIds.removeAll(reporterIds);
        for (int i : userIds){
            User u = userController.getUser(i);
            if("staff".equals(u.getPosition())){
                        this.selectReporterComboBox.addItem(u.getId()+" - "+u.getUsername() + " - " + u.getPosition());
            }
        }
    }//GEN-LAST:event_jTable1MousePressed

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        int row = GUIManagerClient.jTable1.getSelectedRow();
        if(row == -1){
            JOptionPane.showMessageDialog(rootPane, "Choose a meeting first!");
            return;
        }
        if (meetingSelected.getUserCreateId() == GUIManagerClient.user.getId()){
            if(JOptionPane.showConfirmDialog(rootPane, "Are you sure?", "", JOptionPane.YES_NO_OPTION) == 0){        
                String title = this.meetingText.getText();
                Calendar cal = this.dateChooserCombo1.getSelectedDate();
                java.util.Date date = cal.getTime();
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                String timeStart = this.timeText.getText();
                if("".equals(title) || "".equals(timeStart)){
                    JOptionPane.showMessageDialog(rootPane, "These fields are required");
                    return;
                }
                Meeting meetingx = new Meeting();
                meetingx.setId(Integer.parseInt(GUIManagerClient.jTable1.getValueAt(GUIManagerClient.jTable1.getSelectedRow(), 0).toString().substring(3)));
                meetingx.setTitle(title);
                meetingx.setDate(sqlDate);
                meetingx.setTimeStart(timeStart);
                int i = meetingController.editMeeting(meetingx);
                if (i > 0){
                    JOptionPane.showMessageDialog(rootPane, "Success!");
                    List<Meeting> list = meetingController.getMeetings();
                    try {
                        remoteManagerImpl.h.updateMeetingTable(list);
                        remoteManagerImpl.h.staffUpdateMeetingTable(list);
                    } catch (RemoteException ex) {
                        Logger.getLogger("Khong update duoc table!");
                    }
                    this.jLabel5.setText("");
                    this.meetingText.setText("");
                    this.timeText.setText("HH:mm:ss");
                    GUIManagerClient.jTable1.clearSelection();
                    GUIManagerClient.selectReporterComboBox.removeAllItems();
                    this.userSharedComboBox.removeAllItems();
                    this.buttonGroup1.clearSelection();
                }
            }
        }
        else{
            JOptionPane.showMessageDialog(rootPane, "You can not edit reporter for this meeting!");
        }
        
    }//GEN-LAST:event_editButtonActionPerformed

    private void addMeetingButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addMeetingButtonActionPerformed
        if(JOptionPane.showConfirmDialog(rootPane, "Are you sure?", "", JOptionPane.YES_NO_OPTION) == 0){
            String title = this.meetingText.getText();
            Calendar cal = this.dateChooserCombo1.getSelectedDate();
            Date date = cal.getTime();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            String timeStart = this.timeText.getText();
            if("".equals(title) || "".equals(timeStart)){
                JOptionPane.showMessageDialog(rootPane, "These fields are required");
                return;
            }
            String time = "";
            String[] timeSyntax = timeStart.split("\\:");
            if(timeSyntax.length == 3){
                String hour = timeSyntax[0];
                String minute = timeSyntax[1];
                String second = timeSyntax[2];
                if(hour.matches("[0-9]+")) {
                    if (Integer.parseInt(hour)> 23){
                        JOptionPane.showMessageDialog(rootPane, "Wrong Time value");
                        return;
                    }
                    if (hour.length()==1){
                        hour = "0"+hour;
                    }
                }
                else{
                    JOptionPane.showMessageDialog(rootPane, "Wrong Time value");
                    return;
                }
                if(minute.matches("[0-9]+")) {
                    if (Integer.parseInt(minute)> 59){
                        JOptionPane.showMessageDialog(rootPane, "Wrong Time value");
                        return;
                    }
                    if (minute.length()==1){
                        minute = "0"+minute;
                    }
                }
                else{
                    JOptionPane.showMessageDialog(rootPane, "Wrong Time value");
                    return;
                }
                if(second.matches("[0-9]+")) {
                    if (Integer.parseInt(second)> 59){
                        JOptionPane.showMessageDialog(rootPane, "Wrong Time value");
                        return;
                    }
                    if (second.length()==1){
                        second = "0"+second;
                    }
                }
                else{
                    JOptionPane.showMessageDialog(rootPane, "Wrong Time value");
                    return;
                }
                time = hour + ":" + minute+":" + second;
            }
            else if(timeSyntax.length == 2){
                String hour = timeSyntax[0];
                String minute = timeSyntax[1];
                String second = "00";
                if(hour.matches("[0-9]+")) {
                    if (Integer.parseInt(hour)> 23){
                        JOptionPane.showMessageDialog(rootPane, "Wrong Time value");
                        return;
                    }
                    if (hour.length()==1){
                        hour = "0"+hour;
                    }
                }
                else{
                    JOptionPane.showMessageDialog(rootPane, "Wrong Time value");
                    return;
                }
                if(minute.matches("[0-9]+")) {
                    if (Integer.parseInt(minute)> 59){
                        JOptionPane.showMessageDialog(rootPane, "Wrong Time value");
                        return;
                    }
                    if (minute.length()==1){
                        minute = "0"+minute;
                    }
                }
                else{
                    JOptionPane.showMessageDialog(rootPane, "Wrong Time value");
                    return;
                }
                time = hour + ":" + minute+":" + second;
            }
            else{
                JOptionPane.showMessageDialog(rootPane, "Wrong Time value");
                return;
            }
            Meeting meeting = new Meeting();
            meeting.setTitle(title);
            meeting.setDate(sqlDate);
            meeting.setTimeStart(time);
            meeting.setUserCreateId(GUIManagerClient.user.getId());
            MeetingController meetingController = new MeetingController();
            int i = meetingController.addMeeting(meeting);
            if (i > 0){
                JOptionPane.showMessageDialog(rootPane, "Success!");
                List<Meeting> list = meetingController.getMeetings();
                try {
                    remoteManagerImpl.h.updateMeetingTable(list);
                } catch (RemoteException ex) {
                    Logger.getLogger("Khong update duoc table!");
                }
                this.jLabel5.setText("");
            this.meetingText.setText("");
            this.timeText.setText("HH:mm:ss");
            GUIManagerClient.jTable1.clearSelection();
            GUIManagerClient.selectReporterComboBox.removeAllItems();
            this.userSharedComboBox.removeAllItems();
            this.buttonGroup1.clearSelection();
            } else {
                JOptionPane.showMessageDialog(rootPane, "Failed! Please try again!");
            }
        }
    }//GEN-LAST:event_addMeetingButtonActionPerformed

    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutButtonActionPerformed
        if(JOptionPane.showConfirmDialog(rootPane,"Are you sure?", "", JOptionPane.YES_NO_OPTION) == 0){
            try {
                this.remoteManagerImpl.h.removeRemoteManagerInterface(remoteManagerImpl);
            } catch (RemoteException ex) {
                Logger.getLogger("unregistration failed!");
            }
            LoginForm lg = new LoginForm();
            lg.setVisible(true);
            this.setVisible(false);
            try{
                RemoteInterface stub = Register.registry();
                stub.clientLogoutMessage(GUIManagerClient.user);
            } catch (RemoteException | NotBoundException ex) {
                Logger.getLogger(GUIAdminClient.class.getName()).log(Level.SEVERE, null, ex);
            }
            GUIManagerClient.user = null;
        }
        
    }//GEN-LAST:event_logoutButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        int rowSelected = GUIManagerClient.jTable1.getSelectedRow();
        if (rowSelected > -1){
            int meetingId = Integer.parseInt(GUIManagerClient.jTable1.getValueAt(rowSelected, 0).toString().substring(3));
            if (meetingSelected.getUserCreateId() == GUIManagerClient.user.getId()){
                if(JOptionPane.showConfirmDialog(rootPane, "Are you sure?","",JOptionPane.YES_NO_OPTION) == 0){
                    Meeting delmeeting = meetingController.getMeeting(meetingId);
                    permissionController.deletePermission(meetingId);
                    reportController.deleteReports(meetingId);
                    reportPartController.deleteReportParts(meetingId);
                    int result = meetingController.deleteMeeting(delmeeting);
                    if (result > 0){
                        JOptionPane.showMessageDialog(rootPane, "Deleted!");
                        List<Meeting> list = meetingController.getMeetings();
                        GUIManagerClient.updateTable(list);
                        try {
                            remoteManagerImpl.h.updateMeetingTable(list);
                            remoteManagerImpl.h.staffUpdateMeetingTable(list);
                        } catch (RemoteException ex) {
                            Logger.getLogger("Khong update duoc table!");
                        }
                        this.jLabel5.setText("");
                        this.meetingText.setText("");
                        this.timeText.setText("HH:mm:ss");
                        GUIManagerClient.jTable1.clearSelection();
                        GUIManagerClient.selectReporterComboBox.removeAllItems();
                        this.userSharedComboBox.removeAllItems();
                        this.buttonGroup1.clearSelection();
                    } else
                        JOptionPane.showMessageDialog(rootPane, "Something's happened! Try again!");
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "You can not delete this meeting!");
            }
        }
        else
            JOptionPane.showMessageDialog(rootPane, "Choose a meeting first!");
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void viewReportButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewReportButtonActionPerformed
        int row = GUIManagerClient.jTable1.getSelectedRow();
        if (row == -1){
            JOptionPane.showMessageDialog(rootPane, "Choose a meeting first!");
        } else {
            int meetingId = Integer.parseInt(GUIManagerClient.jTable1.getValueAt(row, 0).toString().substring(3));
            if (meetingSelected.getUserCreateId() == GUIManagerClient.user.getId() || "r".equals(permissionController.getPermission(user, meetingSelected))){
                List<Report> reports = reportController.getReports(meetingId);
                if(reports.isEmpty()){
                    JOptionPane.showMessageDialog(rootPane, "Haven't have report yet! Generate report first!");
                    return;
                }
                GUIViewReport.meeting = meetingController.getMeeting(meetingId);
                GUIViewReport.user= GUIManagerClient.user;
                GUIViewReport viewReport = new GUIViewReport();
                viewReport.setVisible(true);
            }
            else {
                int creatorId = meetingController.getMeetingCreatorId(meetingSelected);
                User creator = userController.getUser(creatorId);
                JOptionPane.showMessageDialog(rootPane, "You don't have permission to view report of this Meeting!\n"
                        + "Contact "+ creator.getUsername()+" to have the permission!");
            }
        }
    }//GEN-LAST:event_viewReportButtonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.jLabel5.setText("");
        this.meetingText.setText("");
        this.timeText.setText("HH:mm:ss");
        GUIManagerClient.jTable1.clearSelection();
        GUIManagerClient.selectReporterComboBox.removeAllItems();
        this.userSharedComboBox.removeAllItems();
        this.buttonGroup1.clearSelection();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void addReporterButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addReporterButtonActionPerformed
        int row = GUIManagerClient.jTable1.getSelectedRow();
        if(row == -1){
            JOptionPane.showMessageDialog(rootPane, "Choose a meeting first!");
            return;
        }
        int meetingId = Integer.parseInt(GUIManagerClient.jTable1.getValueAt(row, 0).toString().substring(3));
        if (meetingSelected.getUserCreateId() == GUIManagerClient.user.getId()){
            int endPosition = GUIManagerClient.selectReporterComboBox.getItemAt(GUIManagerClient.selectReporterComboBox.getSelectedIndex()).indexOf("-");
            String selected = GUIManagerClient.selectReporterComboBox.getItemAt(GUIManagerClient.selectReporterComboBox.getSelectedIndex()).substring(0,endPosition).replaceAll(" ","");
            User reporter = userController.getUser(Integer.parseInt(selected));
            String oldPermission = permissionController.getPermission(reporter, meetingSelected);
            if("r".equals(oldPermission)){
                permissionController.deletePermission(reporter.getId(), meetingSelected.getId());
            }
            else if("w".equals(oldPermission)){
                permissionController.deletePermission(reporter.getId(), meetingSelected.getId());
            }
            int result = meetingController.addReporter(reporter, meetingSelected);
            if (result == 0){
                JOptionPane.showMessageDialog(rootPane, "Failed! Try again!");
                return;
            } else {
                JOptionPane.showMessageDialog(rootPane, "Success!");
            }
            try {
                remoteManagerImpl.h.updateReporterComboBox(meetingSelected.getId());
                remoteManagerImpl.h.updateReporterTable(meetingSelected.getId());
                remoteManagerImpl.h.updateUserSharedComboBox(meetingId);
                List<Meeting> list = meetingController.getMeetings();
                remoteManagerImpl.h.staffUpdateMeetingTable(list);
                List<User> users = userController.getUsers();
                remoteManagerImpl.h.updatePermissionTable(users, meetingSelected);
            } catch (RemoteException ex) {
                Logger.getLogger("Khong update duoc Reporter!");
            }
        }
        else{
            JOptionPane.showMessageDialog(rootPane, "You can not set reporter for this meeting!");
        }
        
        
        /// new reporter
    }//GEN-LAST:event_addReporterButtonActionPerformed

    private void meetingTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_meetingTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_meetingTextActionPerformed

    private void timeTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timeTextActionPerformed
        this.timeText.setText("");
    }//GEN-LAST:event_timeTextActionPerformed

    private void shareButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shareButtonActionPerformed
        if(meetingSelected == null){
            JOptionPane.showMessageDialog(rootPane, "Choose a meeting first!");
            return;
        }
        if (meetingSelected.getUserCreateId() == GUIManagerClient.user.getId()){
            User sharedUser = userController.getUser(Integer.parseInt(this.userSharedComboBox.getItemAt(this.userSharedComboBox.getSelectedIndex()).substring(0,1)));
            String permission;
            if (this.readOnlyRadioButton.isSelected()){
                permission="r";
            }
            else if(this.writeRadioButton.isSelected()){
                permission="w";
            }
            else{
                permission=null;
            }
            if(permission==null){
                JOptionPane.showMessageDialog(rootPane, "Choose permission first!");
                return;
            }
            int result = permissionController.addPermission(sharedUser, meetingSelected, permission);
            if(result > 0){
                JOptionPane.showMessageDialog(rootPane, "Success!");
                List<Meeting> list = meetingController.getMeetings();
                List<User> users = userController.getUsers();
                try {
                    remoteManagerImpl.h.updateMeetingTable(list);
                    //remoteManagerImpl.h.updateReporterTable(meetingSelected.getId());
                    remoteManagerImpl.h.updateReporterComboBox(meetingSelected.getId());
                    remoteManagerImpl.h.updateUserSharedComboBox(meetingSelected.getId());
                    remoteManagerImpl.h.staffUpdateMeetingTable(list);
                    remoteManagerImpl.h.updatePermissionTable(users, meetingSelected);
                } catch (RemoteException ex) {
                    Logger.getLogger(GUIManagerClient.class.getName()).log(Level.SEVERE, null, ex);
                }
                this.buttonGroup1.clearSelection();
            }
            else{
                JOptionPane.showMessageDialog(rootPane, "Failed! Can not share!");
            }
        }
        else{
            JOptionPane.showMessageDialog(rootPane, "You can not share this meeting!");
        }
    }//GEN-LAST:event_shareButtonActionPerformed

    private void writeRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_writeRadioButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_writeRadioButtonActionPerformed

    private void readOnlyRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_readOnlyRadioButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_readOnlyRadioButtonActionPerformed

    private void reporterTableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reporterTableMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_reporterTableMousePressed

    private void selectReporterComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectReporterComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectReporterComboBoxActionPerformed

    private void deleteReporterButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteReporterButtonActionPerformed
        int row = GUIManagerClient.reporterTable.getSelectedRow();
        if (row != -1){
            if (meetingSelected.getUserCreateId() == GUIManagerClient.user.getId()){
                if(JOptionPane.showConfirmDialog(rootPane, "Are you sure?", "", JOptionPane.YES_NO_OPTION) == 0){
                    int userId = Integer.parseInt(GUIManagerClient.reporterTable.getValueAt(row, 0).toString());
                    User reporter = userController.getUser(userId);
                    int result = meetingController.deleteReporter(reporter, meetingSelected);
                    if (result == 0){
                        JOptionPane.showMessageDialog(rootPane, "Failed! Try again!");
                    }
                    else{
                        JOptionPane.showMessageDialog(rootPane, "Success!");
                        GUIManagerClient.selectReporterComboBox.removeAllItems();
                    }

                    try {
                        remoteManagerImpl.h.updateReporterComboBox(meetingSelected.getId());
                        remoteManagerImpl.h.updateReporterTable(meetingSelected.getId());
                        remoteManagerImpl.h.updateUserSharedComboBox(meetingSelected.getId());
                        List<Meeting> list = meetingController.getMeetings();
                        remoteManagerImpl.h.staffUpdateMeetingTable(list);
                        List<User> users = userController.getUsers();
                        remoteManagerImpl.h.updatePermissionTable(users, meetingSelected);
                    } catch (RemoteException ex) {
                        Logger.getLogger("Khong update duoc Reporter!");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "You can not set reporter for this meeting!");
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Choose a reporter first!");
        }
    }//GEN-LAST:event_deleteReporterButtonActionPerformed

    private void deletePermissionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletePermissionButtonActionPerformed
        if(JOptionPane.showConfirmDialog(rootPane, "Are you sure?","", JOptionPane.YES_NO_OPTION)==0){
            int rowPermission = permissionTable.getSelectedRow();
            if (rowPermission != -1){
                int userId = Integer.parseInt(permissionTable.getValueAt(rowPermission, 0).toString());
                if ("u".equals(permissionTable.getValueAt(rowPermission, 2).toString())){
                    JOptionPane.showMessageDialog(rootPane, "This is reporter! Please remove in reporter part!");
                    return;
                }
                permissionController.deletePermission(userId, meetingSelected.getId());
                List<User> list = userController.getUsers();
                List<Meeting> listm = meetingController.getMeetings();
                try {
                    remoteManagerImpl.h.updateReporterTable(meetingSelected.getId());
                    remoteManagerImpl.h.updateReporterComboBox(meetingSelected.getId());
                    remoteManagerImpl.h.updatePermissionTable(list, meetingSelected);
                    remoteManagerImpl.h.updateMeetingTable(listm);
                    remoteManagerImpl.h.updateUserSharedComboBox(meetingSelected.getId());
                    remoteManagerImpl.h.staffUpdateMeetingTable(listm);
                } catch (RemoteException ex) {
                    Logger.getLogger("Can not update Permission table!");
                }
            }
            else{
                JOptionPane.showMessageDialog(rootPane, "Choose a permission first!");
            }
        }
    }//GEN-LAST:event_deletePermissionButtonActionPerformed

    private void permissionTableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_permissionTableMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_permissionTableMousePressed
    class RemoteManagerImpl extends UnicastRemoteObject implements RemoteManagerInterface{
        public RemoteInterface h;
        public RemoteManagerImpl() throws RemoteException, NotBoundException, MalformedURLException {
            h = Register.registry();
            h.addRemoteManagerInterface(this);
        }
        @Override
        public void updateMeetingTable(List<Meeting> list) throws RemoteException {
            SwingUtilities.invokeLater(new Runnable(){
                public void run(){
                    List<Meeting> listHavePermission = new ArrayList<>();
                    for(Meeting m: list){
                        String permission = permissionController.getPermission(user, m);
                        if ("r".equals(permission) || "w".equals(permission)){
                            listHavePermission.add(m);
                        }
                        int creatorId = meetingController.getMeetingCreatorId(m);
                        if(creatorId == user.getId()){
                            listHavePermission.add(m);
                        }
                    }
                    GUIManagerClient.updateTable(listHavePermission);
                }
            });
        }

        @Override
        public void updateReporterComboBox(int meetingId) throws RemoteException {
            SwingUtilities.invokeLater(new Runnable(){
                public void run(){
                    if (GUIManagerClient.this.meetingSelected == null){
                        return;
                    }
                    if (GUIManagerClient.this.meetingSelected.getId() == meetingId){
                        System.out.println(GUIManagerClient.this.meetingSelected.getId());
                        GUIManagerClient.selectReporterComboBox.removeAllItems();
                        List<Integer> reporterIds = meetingController.getReporterIds(meetingId);
                        List<Integer> userIds = new ArrayList<>();
                        List<User> users = userController.getUsers();
                        for (User u : users){
                            userIds.add(u.getId());
                        }
                        userIds.removeAll(reporterIds);
                        List<User> userComboBox = new ArrayList<>();
                        for (int i : userIds){
                            User u = userController.getUser(i);
                            if("staff".equals(u.getPosition())){
                                userComboBox.add(u);
                            }
                        }
                        GUIManagerClient.updateReporterComboBox(userComboBox);
                    }
                }
            });
        }

        @Override
        public void updateReporterTable(int meetingId) throws RemoteException {
            SwingUtilities.invokeLater(new Runnable(){
                public void run(){
                    if (GUIManagerClient.this.meetingSelected == null){
                        return;
                    }
                    if (GUIManagerClient.this.meetingSelected.getId() == meetingId){
                        List<Integer> reporterIds = meetingController.getReporterIds(meetingId);
                        List<User> reporters = new ArrayList<>();
                        if (reporterIds != null){
                            for (Integer reporterId : reporterIds){
                                User reporterx = userController.getUser(reporterId);
                                reporters.add(reporterx);
                            }
                        }
                        GUIManagerClient.updateReporterTable(reporters);
                    }
                }
            });
        }
        @Override
        public void updateUserNotSharedYet(int meetingId) throws RemoteException {
            SwingUtilities.invokeLater(new Runnable(){
                public void run(){
                    if (GUIManagerClient.this.meetingSelected == null){
                        return;
                    }
                    if (GUIManagerClient.this.meetingSelected.getId() == meetingId){
                        GUIManagerClient.userSharedComboBox.removeAllItems();
                        List<User> users = GUIManagerClient.this.getUserDontHavePermission(GUIManagerClient.this.meetingSelected);
                        GUIManagerClient.updateUserSharedComboBox(users);
                    }
                }
            });
        }
        @Override
        public void updatePermissionTable(List<User> list, Meeting meeting) throws RemoteException{
            SwingUtilities.invokeLater(new Runnable(){
                public void run(){
                    if (GUIManagerClient.this.meetingSelected == null){
                        return;
                    }
                    if (GUIManagerClient.this.meetingSelected.getId() == meeting.getId()){
                        GUIManagerClient.updateListPermission(list, meeting);
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
            java.util.logging.Logger.getLogger(GUIManagerClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUIManagerClient().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addMeetingButton;
    private javax.swing.JButton addReporterButton;
    private javax.swing.ButtonGroup buttonGroup1;
    private datechooser.beans.DateChooserCombo dateChooserCombo1;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton deletePermissionButton;
    private javax.swing.JButton deleteReporterButton;
    private javax.swing.JButton editButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    public static javax.swing.JTable jTable1;
    private javax.swing.JButton logoutButton;
    private javax.swing.JTextField meetingText;
    private javax.swing.JLabel nameLabel;
    public static javax.swing.JTable permissionTable;
    private javax.swing.JRadioButton readOnlyRadioButton;
    public static javax.swing.JTable reporterTable;
    public static javax.swing.JComboBox<String> selectReporterComboBox;
    private javax.swing.JButton shareButton;
    private javax.swing.JTextField timeText;
    public static javax.swing.JComboBox<String> userSharedComboBox;
    private javax.swing.JButton viewReportButton;
    private javax.swing.JLabel welcomeLabel;
    private javax.swing.JRadioButton writeRadioButton;
    // End of variables declaration//GEN-END:variables
}
