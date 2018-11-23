/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remoteInterface;

import entity.Meeting;
import entity.PeopleEditReport;
import entity.Report;
import entity.ReportPart;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import entity.User;

/**
 *
 * @author thanhdovan
 */
public interface RemoteInterface extends Remote{
    // server
    public void clientLogoutMessage(User user) throws RemoteException;
    public void addRemoteClientInterface(TestRemoteClientInterface rci) throws RemoteException;
    public void removeRemoteClientInterface(TestRemoteClientInterface b) throws RemoteException;
    public String printMsg(String msg) throws RemoteException;
    public void updateStatus(int meetingId, int stat) throws RemoteException;
    public void addRemoteManagerInterface(RemoteManagerInterface rm) throws RemoteException;
    public void removeRemoteManagerInterface(RemoteManagerInterface rm) throws RemoteException;
    public void updateMeetingTable(List<Meeting> list) throws RemoteException;
    public void updateReporterComboBox(int meetingId) throws RemoteException;
    public void updateUserSharedComboBox(int meetingId) throws RemoteException;
    public void updateReporterTable(int meetingId) throws RemoteException;
    
    public void addRemoteAdminInterface(RemoteAdminInterface ra) throws RemoteException;
    public void removeRemoteAdminInterface(RemoteAdminInterface ra) throws RemoteException;
    public void adminUpdateUserTable(List<User> list) throws RemoteException;
    
    public void addRemoteStaffInterface(RemoteStaffInterface rs) throws RemoteException;
    public void removeRemoteStaffInterface(RemoteStaffInterface rs) throws RemoteException;
    public void staffUpdateMeetingTable(List<Meeting> list) throws RemoteException;
    public void staffUpdateReportPartTable(List<ReportPart> list, int meetingId, String userUpload) throws RemoteException;
    public void addRemoteReportInterface(RemoteReportInterface rr) throws RemoteException;
    public void removeRemoteReportInterface(RemoteReportInterface rr) throws RemoteException;
    public void updateUserEdittingTable(List<User> list, int reportId) throws RemoteException;
    public void updateReportTable(List<Report> list) throws RemoteException;
    public void updateReportContent(String content, int reportId) throws RemoteException;
    public void updatePermissionTable(List<User> list, Meeting meeting) throws RemoteException;
    
    
    // interface for user
    public List<User> getUsers() throws RemoteException;
    public User getUser(String username, String password) throws RemoteException;
    public User getUser(int id) throws RemoteException;
    public int addUser(User user) throws RemoteException;
    public int editUser(User user) throws RemoteException;
    public int deleteUser(User user) throws RemoteException;
    
    // end interface for user
    
    // interface for meeting
    public List<Meeting> getMeetings() throws RemoteException;
    public int getMeetingId(int reportId) throws RemoteException;
    public Meeting getMeeting(int meetingId) throws RemoteException;
    public int addMeeting(Meeting meeting) throws RemoteException;
    public int editMeeting(Meeting meeting) throws RemoteException;
    public int deleteMeeting(Meeting meeting) throws RemoteException;
    public int addReporter(User user, Meeting meeting) throws RemoteException;
    public List<Integer> getReporterIds(int meetingId) throws RemoteException;
    public int deleteReporter(User user, Meeting meeting) throws RemoteException;
    public int getMeetingCreatorId(Meeting meeting) throws RemoteException;
// end interface for Meeting
    
    //interface for report
    public int generateReport(Report report, Meeting meeting) throws RemoteException;
    public List<Report> getReports(int meetingId) throws RemoteException;
    public String getReportContent(int reportId) throws RemoteException;
    public Report getReport(int reportId) throws RemoteException;
    public int addReport(Report report) throws RemoteException;
    public int deleteReports(int meetingId) throws RemoteException;
    public int deleteReport(int reportId) throws RemoteException;
    //end interface for report
    
    //interface for filetext
    public int deleteReportPart(int reportPartId) throws RemoteException;
    public int uploadFile(ReportPart reportPart) throws RemoteException;
    public List<ReportPart> getReportParts(int type, int meetingId) throws RemoteException;
    public String getReportPartContent(int reportPartId) throws RemoteException;
    public int deleteReportParts(int meetingId) throws RemoteException;
    //end interface for filetext

    public int addPeopleEdit(PeopleEditReport per) throws RemoteException;
    public List<Integer> getIdOfUserEdit(int reportId) throws RemoteException;
    public int getPeopleEdit(PeopleEditReport per) throws RemoteException;
    public int removePeopleEdit(int peopleEditId) throws RemoteException;
    
    
    // permission remote interface
    public String getPermission(User user, Meeting meeting) throws RemoteException;
    public int addPermission(User user, Meeting meeting, String permission) throws RemoteException;
    public int deletePermission(int meetingId) throws RemoteException;
    public int deletePermission(int userId, int meetingId) throws RemoteException;
    // end permission remote interface
}
