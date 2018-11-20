/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlybienbanclientController;

import entity.Meeting;
import entity.PeopleEditReport;
import entity.Report;
import java.util.List;
import quanlybienbanclientModel.ReportModel;

/**
 *
 * @author thanhdovan
 */
public class ReportController {
    private ReportModel reportModel;

    public ReportController() {
        this.reportModel = new ReportModel();
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
}
