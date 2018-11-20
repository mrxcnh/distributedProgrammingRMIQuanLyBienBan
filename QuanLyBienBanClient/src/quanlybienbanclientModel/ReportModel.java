/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlybienbanclientModel;

import entity.Meeting;
import entity.PeopleEditReport;
import entity.Report;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import registry.Register;
import remoteInterface.RemoteInterface;

/**
 *
 * @author thanhdovan
 */
public class ReportModel {
    
    public int addReport(Report report){
        try{
            RemoteInterface stub = Register.registry();
            return stub.addReport(report);
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(ReportModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public Report getReport(int reportId){
        try{
            RemoteInterface stub = Register.registry();
            return stub.getReport(reportId);
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(ReportModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public int generateReport(Report report, Meeting meeting){
        try {
            RemoteInterface stub = Register.registry();
            return stub.generateReport(report, meeting);
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(ReportModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public List<Report> getReports(int meetingId){
        List<Report> reports = new ArrayList<>();
        try {
            RemoteInterface stub = Register.registry();
            return stub.getReports(meetingId);
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(ReportModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reports;
    }
    
    public String getReportContent(int reportId){
        String reportContent = "";
        try {
            RemoteInterface stub = Register.registry();
            return stub.getReportContent(reportId);
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(ReportModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reportContent;
    }
    
    public int addPeopleEdit(PeopleEditReport per){
        try {
            RemoteInterface stub = Register.registry();
            return stub.addPeopleEdit(per);
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(ReportModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    public List<Integer> getIdOfUserEdit(int reportId){
        try {
            RemoteInterface stub = Register.registry();
            return stub.getIdOfUserEdit(reportId);
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(ReportModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public int getPeopleEdit(PeopleEditReport per){
        try {
            RemoteInterface stub = Register.registry();
            return stub.getPeopleEdit(per);
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(ReportModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public int removePeopleEdit(int peopleEditId){
        try {
            RemoteInterface stub = Register.registry();
            return stub.removePeopleEdit(peopleEditId);
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(ReportModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    public int deleteReports(int meetingId){
        try {
            RemoteInterface stub = Register.registry();
            return stub.deleteReports(meetingId);
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(ReportModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    public int deleteReport(int reportId){
        try {
            RemoteInterface stub = Register.registry();
            return stub.deleteReport(reportId);
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(ReportModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
