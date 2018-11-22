/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlybienbanclientView;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import entity.User;
import helpfile.EncryptPassword;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import quanlybienbanclientController.UserController;
import registry.Register;
import remoteInterface.RemoteAdminInterface;
import remoteInterface.RemoteInterface;

/**
 *
 * @author thanhdovan
 */
public class GUIAdminClient extends javax.swing.JFrame {
    private final UserController userController;
    private String passwdOfUserSelected;
    private RemoteAdminImpl remoteAdminImpl;
    public static User user;
    public static void updateTable(List<User> list){
        Object[] column = {"Id", "Username", "Password", "Fullname", "Position" };
            DefaultTableModel model = new DefaultTableModel();
            model.setColumnIdentifiers(column);
            try {
                for (User u : list ){
                    Object[] row = {u.getId(), u.getUsername(), u.getPassword(), u.getFullname(), u.getPosition()};
                    model.addRow(row);
                }
                GUIAdminClient.userTable.setModel(model);
            } catch (Exception ex) {
                Logger.getLogger(GUIAdminClient.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    /**
     * Creates new form GUIClient
     */
    public GUIAdminClient() {
        try {
            remoteAdminImpl = new RemoteAdminImpl();
        } catch (RemoteException | NotBoundException | MalformedURLException ex) {
            Logger.getLogger("Can not init callback Object!");
        }
        initComponents();
        this.jLabel2.setText(user.getUsername());
        userController = new UserController();
        List<User> list = userController.getUsers();
        GUIAdminClient.updateTable(list);
        this.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e)
            {
                try {
                    remoteAdminImpl.h.removeRemoteAdminInterface(remoteAdminImpl);
                } catch (RemoteException ex) {
                    System.out.println("Can not remove remote admin interface!");
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        userTable = new javax.swing.JTable();
        addButton = new javax.swing.JButton();
        editButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        usernameTF = new javax.swing.JTextField();
        passwordTF = new javax.swing.JTextField();
        fullnameTF = new javax.swing.JTextField();
        clearButton = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        idTF = new javax.swing.JTextField();
        positionComboBox = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel1.setText("User Management");

        jLabel2.setForeground(new java.awt.Color(21, 101, 241));
        jLabel2.setText("You're logging in as ");

        jButton2.setText("Logout");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        userTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Username", "Password", "Fullname", "Position"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        userTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                userTableMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(userTable);

        addButton.setText("Add new");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        editButton.setText("Edit");
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });

        deleteButton.setText("Delete");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        jLabel3.setText("You are logging in as");

        jLabel4.setText("Username");

        jLabel5.setText("Password");

        jLabel6.setText("Fullname");

        jLabel7.setText("Position");

        usernameTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameTFActionPerformed(evt);
            }
        });

        clearButton.setText("Clear fields");
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });

        jLabel8.setText("Id");

        idTF.setEditable(false);
        idTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idTFActionPerformed(evt);
            }
        });

        positionComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "admin", "manager", "staff" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(264, 264, 264))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 679, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(clearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(editButton, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(passwordTF))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(usernameTF)
                            .addComponent(idTF)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(positionComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(fullnameTF))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jButton2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addGap(24, 24, 24)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editButton)
                    .addComponent(deleteButton)
                    .addComponent(addButton)
                    .addComponent(clearButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(idTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(usernameTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(passwordTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(fullnameTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(positionComboBox, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        String susername = this.usernameTF.getText();
        String spassword = this.passwordTF.getText();
        String sfullname = this.fullnameTF.getText();
        String sposition = this.positionComboBox.getItemAt(this.positionComboBox.getSelectedIndex());
        if("".equals(susername) || "".equals(spassword) || "".equals(sfullname)){
            JOptionPane.showMessageDialog(rootPane, "These fields are required");
            return;
        }
        String encryptPasswd = EncryptPassword.getMD5(spassword);
        User tempuser = new User();
        tempuser.setUsername(susername);
        tempuser.setPassword(encryptPasswd);
        tempuser.setPosition(sposition);
        tempuser.setFullname(sfullname);
        int i = userController.addUser(tempuser);
        if (i > 0){
            JOptionPane.showMessageDialog(rootPane, "Success!");
            List<User> list = userController.getUsers();
            try {
                remoteAdminImpl.h.adminUpdateUserTable(list);
            } catch (RemoteException ex) {
                Logger.getLogger("Khong update duoc table!");
            }
            this.usernameTF.setText("");
            this.passwordTF.setText("");
            this.fullnameTF.setText("");
        }
        else {
            JOptionPane.showMessageDialog(rootPane, "Failed! Please try again!");
        }
    }//GEN-LAST:event_addButtonActionPerformed

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        if (!"".equals(idTF.getText())){
            String susername = this.usernameTF.getText();
            String spassword = this.passwordTF.getText();
            String sfullname = this.fullnameTF.getText();
            String sposition = this.positionComboBox.getItemAt(this.positionComboBox.getSelectedIndex());
            if("".equals(susername) || "".equals(spassword) || "".equals(sfullname)){
                JOptionPane.showMessageDialog(rootPane, "These fields are required");
                return;
            }
            String encryptedPasswd;
            if (spassword.equals(this.passwdOfUserSelected)){
                encryptedPasswd = spassword;
            }
            else{ encryptedPasswd = EncryptPassword.getMD5(spassword); }
            User tempuser = new User();
            tempuser.setId(Integer.parseInt(idTF.getText()));
            tempuser.setUsername(susername);
            tempuser.setPassword(encryptedPasswd);
            tempuser.setPosition(sposition);
            tempuser.setFullname(sfullname);

            int i = userController.editUser(tempuser);
            if (i > 0){
                JOptionPane.showMessageDialog(rootPane, "Success!");
                List<User> list = userController.getUsers();
                GUIAdminClient.updateTable(list);
                try {
                    remoteAdminImpl.h.adminUpdateUserTable(list);
                } catch (RemoteException ex) {
                    Logger.getLogger("Khong update duoc table!");
                }
                GUIAdminClient.this.idTF.setText("");
                GUIAdminClient.this.usernameTF.setText("");
                GUIAdminClient.this.passwordTF.setText("");
                GUIAdminClient.this.fullnameTF.setText("");
            }
            else {
                JOptionPane.showMessageDialog(rootPane, "Failed! Please try again!");
            }
        }
        else
            JOptionPane.showMessageDialog(rootPane, "Choose a user first!");
        
    }//GEN-LAST:event_editButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        if (!"".equals(idTF.getText())){
            if(JOptionPane.showConfirmDialog(rootPane, "Are you sure?","",JOptionPane.YES_NO_OPTION) == 0){
                int userId = Integer.parseInt(idTF.getText());
                User deluser = userController.getUser(userId);
                int i = userController.deleteUser(deluser);
                if (i > 0){
                    JOptionPane.showMessageDialog(rootPane, "Deleted!");
                    List<User> list = userController.getUsers();
                    GUIAdminClient.updateTable(list);
                    try {
                        remoteAdminImpl.h.adminUpdateUserTable(list);
                    } catch (RemoteException ex) {
                        Logger.getLogger("Khong update duoc table!");
                    }
                    this.idTF.setText("");
                    this.usernameTF.setText("");
                    this.passwordTF.setText("");
                    this.fullnameTF.setText("");
                }
                else
                    JOptionPane.showMessageDialog(rootPane, "Something's happened! Try again!");
            }
        }
        else
            JOptionPane.showMessageDialog(rootPane, "Choose a user first!");
        
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if(JOptionPane.showConfirmDialog(rootPane,"Are you sure?", "", JOptionPane.YES_NO_OPTION) == 0){
            LoginForm lg = new LoginForm();
            lg.setVisible(true);
            this.setVisible(false);
            try{
                    RemoteInterface stub = Register.registry();
                    stub.clientLogoutMessage(user);
            } catch (RemoteException | NotBoundException ex) {
                Logger.getLogger("Log out error");
            }
            try {
                this.remoteAdminImpl.h.removeRemoteAdminInterface(remoteAdminImpl);
            } catch (RemoteException ex) {
                Logger.getLogger("unregistration admin failed!");
            }
            GUIAdminClient.user = null;
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void usernameTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameTFActionPerformed

    private void userTableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_userTableMousePressed
        int row = GUIAdminClient.userTable.getSelectedRow();
        if(row!=-1){
            this.idTF.setText(GUIAdminClient.userTable.getValueAt(row, 0).toString());
            this.usernameTF.setText(GUIAdminClient.userTable.getValueAt(row, 1).toString());
            this.passwordTF.setText(GUIAdminClient.userTable.getValueAt(row, 2).toString());
            this.passwdOfUserSelected = GUIAdminClient.userTable.getValueAt(row, 2).toString();
            this.fullnameTF.setText(GUIAdminClient.userTable.getValueAt(row, 3).toString());
            this.positionComboBox.setSelectedItem(GUIAdminClient.userTable.getValueAt(row, 4).toString());
        }
    }//GEN-LAST:event_userTableMousePressed

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
        GUIAdminClient.userTable.clearSelection();
        this.idTF.setText("");
        this.usernameTF.setText("");
        this.passwordTF.setText("");
        this.fullnameTF.setText("");
    }//GEN-LAST:event_clearButtonActionPerformed

    private void idTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idTFActionPerformed

    class RemoteAdminImpl extends UnicastRemoteObject implements RemoteAdminInterface{
        public RemoteInterface h;
        public RemoteAdminImpl() throws RemoteException, NotBoundException, MalformedURLException {
            h = Register.registry();
            h.addRemoteAdminInterface(this);
        }
        @Override
        public void adminUpdateUserTable(List<User> list) throws RemoteException {
            SwingUtilities.invokeLater(new Runnable(){
                public void run(){
                    GUIAdminClient.updateTable(list);
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
            java.util.logging.Logger.getLogger(GUIAdminClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUIAdminClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUIAdminClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUIAdminClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUIAdminClient().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JButton clearButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton editButton;
    javax.swing.JTextField fullnameTF;
    private javax.swing.JTextField idTF;
    private javax.swing.JButton jButton2;
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
    javax.swing.JTextField passwordTF;
    private javax.swing.JComboBox<String> positionComboBox;
    public static javax.swing.JTable userTable;
    javax.swing.JTextField usernameTF;
    // End of variables declaration//GEN-END:variables
}
