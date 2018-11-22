/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlybienbanclientController;

import entity.Meeting;
import entity.PeopleEditReport;
import entity.Report;
import org.apache.poi.hwpf.HWPFDocument;
import quanlybienbanclientModel.ReportModel;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import quanlybienbanclientModel.MeetingModel;

/**
 *
 * @author thanhdovan
 */
public class ReportController {
    private ReportModel reportModel;
    private MeetingModel meetingModel;

    public ReportController() {
        this.reportModel = new ReportModel();
        this.meetingModel = new MeetingModel();
    }
    
    public int addReport(Report report){
        return reportModel.addReport(report);
    }
    
    public Report getReport(int reportId){
        return reportModel.getReport(reportId);
    }
    
    public List<Report> getReports(int meetingId){
        return reportModel.getReports(meetingId);
    }
    
    public String getReportContent(int reportId){
        return reportModel.getReportContent(reportId);
    }
    
    public int generateReport(Report report, Meeting meeting){
        return reportModel.generateReport(report, meeting);
    }
    
    public List<Integer> getIdOfUserEdit(int reportId){
        return reportModel.getIdOfUserEdit(reportId);
    }
    
    public int addPeopleEdit(PeopleEditReport per){
        return reportModel.addPeopleEdit(per);
    }
    
    public int getPeopleEdit(PeopleEditReport per){
        return reportModel.getPeopleEdit(per);
    }
    
    public int removePeopleEdit(int peopleEditId){
        return reportModel.removePeopleEdit(peopleEditId);
    }
    public int deleteReports(int meetingId){
        return reportModel.deleteReports(meetingId);
    }
    public int deleteReport(int reportId){
        return reportModel.deleteReport(reportId);
    }
    
    public void exportReportToWord(int reportId) {
        String templateFolder = (System.getProperty("user.dir").substring(0, System.getProperty("user.dir").indexOf("QuanLyBienBan" + System.getProperty("file.separator")) + ("QuanLyBienBan" + System.getProperty("file.separator")).length())) +  "template";
        Report report = reportModel.getReport(reportId);
        int meetingId = meetingModel.getMeetingId(reportId);
        Meeting meeting = meetingModel.getMeeting(meetingId);
        String timeStart = meeting.getTimeStart();
        final File folder = new File(templateFolder);
        ArrayList<String> templatesArayList = listAllTemplate(folder);
        if (templatesArayList == null || templatesArayList.size() == 0) {
            JOptionPane.showMessageDialog(null, "Không có template có sẵn nào");
            return ;
        }
        String[] templates = templatesArayList.toArray(new String[templatesArayList.size()]);
        int x = JOptionPane.showOptionDialog(null, "Bạn hãy lựa chọn 1 template",
                "Chọn template",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, templates, templates[0]);
        String template = templates[x];

        String templatePath = templateFolder + ((System.getProperty("os.name").contains("Windows") ? "\\" : "/") + template);
        HWPFDocument doc = HWPFTest.openDocument(templatePath);
        if (doc == null) {
            System.out.println("Can't open template " + template);
            return ;
        }
        String content = report.getReportContent();
        String[] lines = content.split("\n");
        String member ="";
        List<String> endTime= new ArrayList<>();
        for (String line: lines){
            System.out.println("1: "+ line);
            String[] parts = line.split("\\]");
            String[] ts = parts[0].split("\\~");
            endTime.add(ts[1]);
            String[] pc = parts[1].split("-");
            member += pc[0] + " \n";
        }
        String timeE = endTime.get(endTime.size()-1);
        String[] timeEn = timeE.split(":");
        String[] timeS = timeStart.split(":");
        int iendHour = (Integer.parseInt(timeS[0]) + Integer.parseInt(timeEn[0]));
        String endHour = Integer.toString(iendHour);
        int iendMinute = (Integer.parseInt(timeS[1]) + Integer.parseInt(timeEn[1]));
        String endMinute = Integer.toString(iendMinute);
        String dayM = meeting.getDate().toString();
        String[] dayMs = dayM.split("-");
        doc = HWPFTest.replaceText(doc, "TIME_START", timeStart);
        doc = HWPFTest.replaceText(doc, "MEMBER_PATICIPATE", member);
        doc = HWPFTest.replaceText(doc, "END_HOUR", endHour);
        doc = HWPFTest.replaceText(doc, "END_MINUTE", endMinute);
        doc = HWPFTest.replaceText(doc, "DAY_MEETING", dayMs[2]);
        doc = HWPFTest.replaceText(doc, "MONTH_MEETING", dayMs[1]);
        doc = HWPFTest.replaceText(doc, "YEAR_MEETING", dayMs[0]);
        doc = HWPFTest.replaceText(doc, "REPORT_NAME", report.getReportName());
        doc = HWPFTest.replaceText(doc, "REPORT_CONTENT", report.getReportContent());
        doc = HWPFTest.replaceText(doc, "SECRETARY", report.getAuthors());

        String exportFolder = (System.getProperty("user.dir").substring(0, System.getProperty("user.dir").indexOf("QuanLyBienBan" + System.getProperty("file.separator")) + ("QuanLyBienBan" + System.getProperty("file.separator")).length())) +  "export";
        String reportFileName = report.getReportName() + "[" + new SimpleDateFormat("dd_MM_yyyy hh_mm").format(new Date()) + "].doc";
        if(HWPFTest.saveDocument(exportFolder + System.getProperty("file.separator") +  reportFileName, doc)){
            if (JOptionPane.showConfirmDialog(null, "Xuất file " +reportFileName +" thành công!\n" +"Bạn có muốn mở không ?") == JOptionPane.YES_OPTION) {
                openFile(exportFolder + System.getProperty("file.separator") +  reportFileName);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Đã có lỗi xảy ra");
        }


    }
    public static void openFile(String filePath) {
        try {
            //Process p = Runtime.getRuntime().exec ("\"" +url + "\""); cach nay cham hon
            if (Desktop.isDesktopSupported()) {
                File myFile = new File(filePath);
                Desktop.getDesktop().open(myFile);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Đã có lỗi xảy ra khi mở file " + filePath + "\n" + ex, "ERROR", 2);
        }

    }
    public ArrayList<String> listAllTemplate(final File folder) {
        try {
            File[] listFile = folder.listFiles();
            if (listFile == null || listFile.length == 0) return null;
            ArrayList<String> files = new ArrayList<>();
            for (final File fileEntry : listFile) {
                if (fileEntry.isDirectory()) {
                    ArrayList<String> strings = listAllTemplate(fileEntry);
                    if (strings != null && strings.size() > 0) files.addAll(strings);
                } else {
                    if (fileEntry.getName().endsWith(".doc") && !fileEntry.getName().startsWith("~"))
                        files.add(fileEntry.getName());
                }
            }
            return files.size() == 0 ? null : files;
        } catch (Exception ex) {
            return null;
        }
    }
}
