/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlybienbanclientView;

import entity.ContentTime;
import entity.Meeting;
import entity.PersonContentTime;
import entity.PersonTime;
import entity.Report;
import entity.ReportPart;
import entity.User;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import quanlybienbanclientController.ReportController;
import quanlybienbanclientController.ReportPartController;

/**
 *
 * @author thanhdovan
 */
public class GenerateReport extends javax.swing.JFrame {
    final int PERSONCONTENT=0, CONTENTTIME=1;
    public static Meeting meeting;
    public static User user;
    private ReportPartController reportPartController;
    private ReportController reportController;
    /**
     * Creates new form GenerateReport
     */
    public GenerateReport() {
        reportPartController = new ReportPartController();
        reportController = new ReportController();
        initComponents();
        List<ReportPart> reportPartPersonContents = reportPartController.getReportPartIds(PERSONCONTENT, GenerateReport.meeting.getId());
        List<ReportPart> reportPartContentTimes = reportPartController.getReportPartIds(CONTENTTIME, GenerateReport.meeting.getId());
        for(ReportPart rp : reportPartPersonContents){
            this.jComboBox1.addItem(rp.getId()+ " - " + rp.getFileName());
        }
        for(ReportPart rp : reportPartContentTimes){
            this.jComboBox2.addItem(rp.getId()+ " - " + rp.getFileName());
        }
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
        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        generateReportButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        personTimePartTextArea = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        contentTimePartTextArea = new javax.swing.JTextArea();
        reportNameTextField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jLabel1.setText("Report Name");

        jLabel2.setText("Choose the Person-Time file");

        jLabel3.setText("Choose the Content-Time file");

        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jComboBox2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox2ItemStateChanged(evt);
            }
        });

        generateReportButton.setText("Generate Content");
        generateReportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateReportButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        jLabel4.setText("Preview PT file");

        jLabel5.setText("Preview CT file");

        personTimePartTextArea.setColumns(20);
        personTimePartTextArea.setRows(5);
        jScrollPane1.setViewportView(personTimePartTextArea);

        contentTimePartTextArea.setColumns(20);
        contentTimePartTextArea.setRows(5);
        jScrollPane2.setViewportView(contentTimePartTextArea);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(generateReportButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(reportNameTextField)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cancelButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(reportNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(generateReportButton)
                    .addComponent(cancelButton))
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

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        Object item = evt.getItem();
        int endIndex = item.toString().indexOf("-");
        int reportPartId = Integer.parseInt(item.toString().substring(0, endIndex).replaceAll(" ", ""));
        String reportPartContent = reportPartController.getReportPartContent(reportPartId);
        this.personTimePartTextArea.setText(reportPartContent);
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jComboBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox2ItemStateChanged
        Object item = evt.getItem();
        int endIndex = item.toString().indexOf("-");
        int reportPartId = Integer.parseInt(item.toString().substring(0, endIndex).replaceAll(" ", ""));
        String reportPartContent = reportPartController.getReportPartContent(reportPartId);
        this.contentTimePartTextArea.setText(reportPartContent);
    }//GEN-LAST:event_jComboBox2ItemStateChanged

    private void generateReportButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateReportButtonActionPerformed
        if(JOptionPane.showConfirmDialog(rootPane, "Are you sure?", "", JOptionPane.YES_NO_OPTION)==0){
            // get list all person-content in person-contentpart
            String personTimePart = this.personTimePartTextArea.getText();
            if("".equals(personTimePart)){
                JOptionPane.showMessageDialog(rootPane, "Input file have wrong type. Can't not generate report!\n Choose a right input format or Add new report part file!");
                return;
            }
            String[] linesInPTPart = personTimePart.split("\n");
            List<PersonTime> personTimes = new ArrayList<>();
            for (String line: linesInPTPart){
                if (line.length() > 1){
                    PersonTime personTime = new PersonTime();
                    String[] parts = line.split("\\[");
                    if (parts.length != 2){
                        JOptionPane.showMessageDialog(rootPane, "Input file have wrong type. Can't not generate report!\n Choose a right input format or Add new report part file!");
                        return;
                    }
                    personTime.setName(parts[0]);
                    String[] timeparts = parts[1].split("\\~");
                    if (timeparts.length != 2){
                        JOptionPane.showMessageDialog(rootPane, "Input file have wrong type. Can't not generate report!\n Choose a right input format or Add new report part file!");
                        return;
                    }
                    personTime.setTimeBegin(timeparts[0]);
                    personTime.setTimeEnd(timeparts[1].substring(0, timeparts[1].length()-1));
                    personTimes.add(personTime);
                }
            }

            // get list all content-time in content-timepart
            String contentTimePart = this.contentTimePartTextArea.getText();
            if("".equals(contentTimePart)){
                JOptionPane.showMessageDialog(rootPane, "Input file have wrong type. Can't not generate report!\n Choose a right input format or Add new report part file!");
                return;
            }
            String[] linesInCTPart = contentTimePart.split("\n");
            List<ContentTime> contentTimes = new ArrayList<>();
            for (String line: linesInCTPart){
                if (line.length() > 1){
                    ContentTime contentTime = new ContentTime();
                    String[] parts = line.split("\\[");
                    if (parts.length != 2){
                        JOptionPane.showMessageDialog(rootPane, "Input file have wrong type. Can't not generate report!\n Choose a right input format or Add new report part file!");
                        return;
                    }
                    contentTime.setContent(parts[0]);
                    String[] timeparts = parts[1].split("\\~");
                    if (timeparts.length != 2){
                        JOptionPane.showMessageDialog(rootPane, "Input file have wrong type. Can't not generate report!\n Choose a right input format or Add new report part file!");
                        return;
                    }
                    contentTime.setTimeBegin(timeparts[0]);
                    contentTime.setTimeEnd(timeparts[1].substring(0, timeparts[1].length()-1));
                    contentTimes.add(contentTime);
                }
            }
            if(contentTimes.size() != personTimes.size()){
                JOptionPane.showMessageDialog(rootPane, "Can not generate! Input have the different number of rows");
                return;
            }
            List<PersonContentTime> personContentTimes = new ArrayList<>();
            for(PersonTime pt : personTimes){
                PersonContentTime pct = new PersonContentTime();
                pct.setName(pt.getName());
                pct.setTimeBegin(pt.getTimeBegin());
                pct.setTimeEnd(pt.getTimeEnd());
                String content = "";
                for(ContentTime ct : contentTimes){
                    if (pct.getTimeBegin().equals(ct.getTimeBegin()) && pct.getTimeEnd().equals(ct.getTimeEnd())){
                        content += ct.getContent();
                    }
                }
                pct.setContent(content);
                personContentTimes.add(pct);
            }
            for(PersonContentTime pct: personContentTimes){
                if (pct.getContent().isEmpty()){
                    JOptionPane.showMessageDialog(rootPane, "Can not generate! Wrong time!");
                    return;
                }
            }
            List<ReportPart> reportPartTranscripts = reportPartController.getReportPartIds(2, GenerateReport.meeting.getId());
            List<PersonContentTime> personContentTimeTranscript = new ArrayList<>();
            if(reportPartTranscripts.isEmpty()){
                JOptionPane.showMessageDialog(rootPane, "Dont have Transcript. Cant compute accuracy!");
            }else{
                ReportPart reportPartTranscript = reportPartTranscripts.get(0);
                String contentTranscript = reportPartController.getReportPartContent(reportPartTranscript.getId());
                String[] linesInTranscript = contentTranscript.split("\n");
                for (String line: linesInTranscript){
                    if (line.length() > 1){
                        PersonContentTime pct = new PersonContentTime();
                        String[] parts = line.split("\\]");
                        if (parts.length != 2){
                            JOptionPane.showMessageDialog(rootPane, "Input file have wrong type. Can't not generate report!\n Choose a right input format or Add new report part file!");
                            return;
                        }
                        String[] timeparts = parts[0].split("\\~");
                        if (timeparts.length != 2){
                            JOptionPane.showMessageDialog(rootPane, "Input file have wrong type. Can't not generate report!\n Choose a right input format or Add new report part file!");
                            return;
                        }
                        pct.setTimeBegin(timeparts[0].substring(1));
                        pct.setTimeEnd(timeparts[1]);
                        String[] pcparts = parts[1].split("-");
                        if (pcparts.length != 2){
                            JOptionPane.showMessageDialog(rootPane, "Input file have wrong type. Can't not generate report!\n Choose a right input format or Add new report part file!");
                            return;
                        }
                        pct.setName(pcparts[0]);
                        pct.setContent(pcparts[1]);
                        personContentTimeTranscript.add(pct);
                    }
                }
            }
            personContentTimeTranscript.removeAll(personContentTimes);
            double accuracy = (personContentTimes.size() - personContentTimeTranscript.size()) / (double)personContentTimes.size();
            JOptionPane.showMessageDialog(rootPane, "Accuracy: "+accuracy);
            Report report = new Report();
            report.setMeetingId(GenerateReport.meeting.getId());
            report.setReportName(this.reportNameTextField.getText());
            report.setPersonContentTimes(personContentTimes);
            report.setAuthors(GenerateReport.user.getUsername());
            int result = reportController.generateReport(report, GenerateReport.meeting);
            if (result == 0){
                JOptionPane.showMessageDialog(rootPane, "Failed! Try again!");
                GenerateReport.meeting = null;
                this.dispose();
            }
            else{
                JOptionPane.showMessageDialog(rootPane, "Success!");
                GenerateReport.meeting = null;
                this.dispose();
            }
        }
    }//GEN-LAST:event_generateReportButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        GenerateReport.meeting = null;
        this.dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

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
            java.util.logging.Logger.getLogger(GenerateReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GenerateReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GenerateReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GenerateReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GenerateReport().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JTextArea contentTimePartTextArea;
    private javax.swing.JButton generateReportButton;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea personTimePartTextArea;
    private javax.swing.JTextField reportNameTextField;
    // End of variables declaration//GEN-END:variables
}
