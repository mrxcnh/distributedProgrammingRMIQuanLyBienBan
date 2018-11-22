/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remoteImpl;

import entity.Meeting;
import entity.PeopleEditReport;
import entity.PersonContentTime;
import entity.Report;
import entity.ReportPart;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import entity.User;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.Vector;
import mainServer.GUIServer;
import remoteInterface.ConnectDB;
import remoteInterface.RemoteAdminInterface;
import remoteInterface.RemoteInterface;
import remoteInterface.RemoteManagerInterface;
import remoteInterface.RemoteReportInterface;
import remoteInterface.RemoteStaffInterface;
import remoteInterface.TestRemoteClientInterface;

/**
 *
 * @author thanhdovan
 */
public class RemoteImpl extends UnicastRemoteObject implements RemoteInterface {    
    private Vector testClients;
    private Vector managerClients;
    private Vector adminClients;
    private Vector staffClients;
    private Vector reportClients;
    public RemoteImpl() throws RemoteException{
        super();
        managerClients = new Vector();
        adminClients = new Vector();
        staffClients = new Vector();
        reportClients = new Vector();
    }
//Remote for callback
    @Override
    public synchronized void addRemoteClientInterface(TestRemoteClientInterface rci) throws RemoteException{
        testClients.addElement(rci);
    }
    @Override
    public synchronized void removeRemoteClientInterface(TestRemoteClientInterface b) throws RemoteException{
        testClients.removeElement(b);
    }
    @Override
    public String printMsg(String msg) throws RemoteException{
        for (int i = 0; i < testClients.size() ; i++){
            TestRemoteClientInterface rci = (TestRemoteClientInterface)testClients.elementAt(i);
            rci.printmsg(msg);
        }
        return msg;
    }
    @Override
    public synchronized void addRemoteManagerInterface(RemoteManagerInterface rm) throws RemoteException{
        managerClients.addElement(rm);
    }
    @Override
    public synchronized void removeRemoteManagerInterface(RemoteManagerInterface rm) throws RemoteException{
        managerClients.removeElement(rm);
    }
    @Override
    public void updateMeetingTable(List<Meeting> list) throws RemoteException{
        for (int i = 0; i < managerClients.size() ; i++){
            RemoteManagerInterface rm = (RemoteManagerInterface)managerClients.elementAt(i);
            rm.updateMeetingTable(list);
        }
    }
    @Override
    public void updateReporterComboBox(int meetingId) throws RemoteException{
        for (int i = 0; i < managerClients.size() ; i++){
            RemoteManagerInterface rm = (RemoteManagerInterface)managerClients.elementAt(i);
            rm.updateReporterComboBox(meetingId);
        }
    }
    @Override
    public void updateUserSharedComboBox(int meetingId) throws RemoteException{
        for (int i = 0; i < managerClients.size() ; i++){
            RemoteManagerInterface rm = (RemoteManagerInterface)managerClients.elementAt(i);
            rm.updateUserNotSharedYet(meetingId);
        }
    }
    @Override
    public void updateReporterTable(int meetingId) throws RemoteException{
        for (int i = 0; i < managerClients.size() ; i++){
            RemoteManagerInterface rm = (RemoteManagerInterface)managerClients.elementAt(i);
            rm.updateReporterTable(meetingId);
        }
    }
    @Override
    public synchronized void addRemoteAdminInterface(RemoteAdminInterface ra) throws RemoteException{
        adminClients.addElement(ra);
    }
    @Override
    public synchronized void removeRemoteAdminInterface(RemoteAdminInterface ra) throws RemoteException{
        adminClients.removeElement(ra);
    }
    @Override
    public void adminUpdateUserTable(List<User> list) throws RemoteException{
        for (int i = 0; i < adminClients.size() ; i++){
            RemoteAdminInterface ra = (RemoteAdminInterface)adminClients.elementAt(i);
            ra.adminUpdateUserTable(list);
        }
    }
    
    @Override
    public synchronized void addRemoteStaffInterface(RemoteStaffInterface rs) throws RemoteException{
        staffClients.addElement(rs);
    }
    @Override
    public synchronized void removeRemoteStaffInterface(RemoteStaffInterface rs) throws RemoteException{
        staffClients.removeElement(rs);
    }
    @Override
    public void staffUpdateMeetingTable(List<Meeting> list) throws RemoteException{
        for (int i = 0; i < staffClients.size() ; i++){
            RemoteStaffInterface rs = (RemoteStaffInterface)staffClients.elementAt(i);
            rs.updateMeetingTable(list);
        }
    }
    @Override
    public void staffUpdateReportPartTable(List<ReportPart> list, int meetingId) throws RemoteException{
        for (int i = 0; i < staffClients.size() ; i++){
            RemoteStaffInterface rs = (RemoteStaffInterface)staffClients.elementAt(i);
            rs.updateReportPartTable(list, meetingId);
        }
    }
    @Override
    public synchronized void addRemoteReportInterface(RemoteReportInterface rr) throws RemoteException{
        reportClients.addElement(rr);
    }
    @Override
    public synchronized void removeRemoteReportInterface(RemoteReportInterface rr) throws RemoteException{
        reportClients.removeElement(rr);
    }
    @Override
    public void updateUserEdittingTable(List<User> list, int reportId) throws RemoteException{
        for (int i = 0; i < reportClients.size() ; i++){
            RemoteReportInterface rr = (RemoteReportInterface)reportClients.elementAt(i);
            rr.updateUserEdittingTable(list, reportId);
        }
    }
    @Override
    public void updateReportTable(List<Report> list) throws RemoteException {
        for (int i = 0; i < reportClients.size() ; i++){
            RemoteReportInterface rr = (RemoteReportInterface)reportClients.elementAt(i);
            rr.updateReportTable(list);
        }
    }
    @Override
    public void updateReportContent(String content, int reportId, int caretOfClientChange, int statusChange) throws RemoteException {
        for (int i = 0; i < reportClients.size() ; i++){
            RemoteReportInterface rr = (RemoteReportInterface)reportClients.elementAt(i);
            rr.updateReportContent(content, reportId, caretOfClientChange, statusChange);
        }
    }
    @Override
    public void updatePermissionTable(List<User> list, Meeting meeting) throws RemoteException{
        for (int i = 0; i < managerClients.size() ; i++){
            RemoteManagerInterface rm = (RemoteManagerInterface)managerClients.elementAt(i);
            rm.updatePermissionTable(list, meeting);
        }
    }
// end for callback

// Remote Implement for user
    @Override
    public List<User> getUsers() throws RemoteException {
        GUIServer.jTextArea1.append("Load user list . . .\n");
        List<User> users = new ArrayList<>();
        Statement stmt;
        String sql = "select * from users";
        ResultSet rs;
        try {
            Connection conn = ConnectDB.connectDB();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next()) {
                // Retrieve by column name
                int id  = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String fullname = rs.getString("fullname");
                String position = rs.getString("position");
                
                // Setting the values
                User user = new User();
                user.setId(id);
                user.setUsername(username);
                user.setPassword(password);
                user.setFullname(fullname);
                user.setPosition(position);
                users.add(user);
            }
            rs.close();
            conn.close();
            GUIServer.jTextArea1.append("Load user list done! \n");
            return users;
        } catch (SQLException ex) {
            Logger.getLogger(RemoteImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;
    }
    @Override
    public User getUser(String username, String password) throws RemoteException {
        User user = new User();
        System.out.println("Creating statement...");        
        String sql = "select * from users where username = ? and password = ?";
        try {
            Connection conn = ConnectDB.connectDB();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                int id = rs.getInt("id");
                String fullname = rs.getString("fullname");
                String position = rs.getString("position");
                
                //add data
                user.setId(id);
                user.setUsername(username);
                user.setPassword(password);
                System.out.println("System get username: " + user.getUsername() + " for login!");
                GUIServer.jTextArea1.append("A client with username: " + user.getUsername() + " logged in! \n");
                System.out.println(GUIServer.jTextArea1.getText());
                user.setFullname(fullname);
                user.setPosition(position);
                rs.close();
                conn.close();
                return user;
            }
        } catch (SQLException ex) {
            Logger.getLogger(RemoteImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return user;
    }
    @Override
    public int addUser(User user) throws RemoteException {
        GUIServer.jTextArea1.append("Adding user with \n"
                + "username:"+ user.getUsername() + "\n"
                + "password:"+ user.getPassword() + "\n"
                + "fullname:"+ user.getFullname()+"\n "
                + "position:"+ user.getPosition()+"\n. . .");
        System.out.println("Creating statement...");
        String sql = "INSERT INTO users (username, password, fullname, position) VALUES "
                +"('"+ user.getUsername() +"' ,'"+user.getPassword() + "', '"+ user.getFullname()+"', '"+user.getPosition()+"');";
        try {
            Connection conn = ConnectDB.connectDB();
            Statement stmt = conn.createStatement();
            int i = stmt.executeUpdate(sql);
            GUIServer.jTextArea1.append("Adding done!\n");
            conn.close();
            return i;
        } catch (SQLException ex) {
            Logger.getLogger(RemoteImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    @Override
    public User getUser(int id) throws RemoteException {
        User user = new User();
        System.out.println("Creating statement...");        
        String sql = "select * from users where id = ?";
        try {
            Connection conn = ConnectDB.connectDB();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                String username = rs.getString("username");
                String password = rs.getString("password");
                String fullname = rs.getString("fullname");
                String position = rs.getString("position");
                
                //add data
                user.setId(id);
                user.setUsername(username);
                user.setPassword(password);
                user.setFullname(fullname);
                user.setPosition(position);
                rs.close();
                conn.close();
                return user;
            }
        } catch (SQLException ex) {
            Logger.getLogger(RemoteImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }
    @Override
    public int editUser(User user) throws RemoteException {
        System.out.println("Creating statement...");
        String sql = "update users set username = ?, password = ?, fullname = ?, position = ? where id = ?;";
        try {
            Connection conn = ConnectDB.connectDB();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getFullname());
            stmt.setString(4, user.getPosition());
            stmt.setInt(5, user.getId());
            System.out.println(stmt);
            int i = stmt.executeUpdate();
            GUIServer.jTextArea1.append("Edited user " +user.getUsername()+ "!\n");
            conn.close();
            return i;
        } catch (SQLException ex) {
            Logger.getLogger(RemoteImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    @Override
    public int deleteUser(User user) throws RemoteException {
        System.out.println("Creating statement...");
        String sql = "DELETE FROM users WHERE users.id = ?;";
        try {
            Connection conn = ConnectDB.connectDB();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, user.getId());
            System.out.println(stmt);
            int i = stmt.executeUpdate();
            GUIServer.jTextArea1.append("Deleted user "+ user.getUsername() +"!\n");
            conn.close();
            return i;
        } catch (SQLException ex) {
            Logger.getLogger(RemoteImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
// end Remote Implement for user
    
// Remote Implement for Meeting
    @Override
    public List<Meeting> getMeetings() throws RemoteException {
        GUIServer.jTextArea1.append("Load meeting list . . .\n");
        List<Meeting> list = new ArrayList<>();
        Statement stmt;
        System.out.println("Creating statement...");
        String sql = "select * from meetings";
        ResultSet rs;
        try {
            Connection conn = ConnectDB.connectDB();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next()) {
                // Retrieve by column name
                int id  = rs.getInt("id");
                String title = rs.getString("meetingTitle");
                Date date = rs.getDate("meetingDate");
                String time = rs.getString("timeStart");
                // Setting the values
                Meeting meeting = new Meeting();
                meeting.setId(id);
                meeting.setTitle(title);
                meeting.setDate(date);
                meeting.setTimeStart(time);
                list.add(meeting);
            }
        rs.close();
        conn.close();
        GUIServer.jTextArea1.append("Load meeting list done! \n");
        return list;
        } catch (SQLException ex) {
            Logger.getLogger(RemoteImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    @Override
    public int addMeeting(Meeting meeting) throws RemoteException {
        
        GUIServer.jTextArea1.append("Adding new meeting:"
                + "title: " + meeting.getTitle() + "\n"
                + "date: " + meeting.getDate() + "\n"
                + "time: " + meeting.getTimeStart() + "\n. . .");
        String sql = "INSERT INTO meetings (meetingTitle, meetingDate, timeStart, userCreateId) VALUES (?, ?, ?, ?);";
        System.out.println(sql);
        try {
            Connection conn = ConnectDB.connectDB();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, meeting.getTitle());
            stmt.setDate(2, meeting.getDate());
            stmt.setString(3, meeting.getTimeStart());
            stmt.setInt(4, meeting.getUserCreateId());
            System.out.println(stmt);
            int o = stmt.executeUpdate();
            GUIServer.jTextArea1.append("Adding done! \n");
            conn.close();
            return o;
        } catch (SQLException ex) {
            Logger.getLogger(RemoteImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return 0;
    }
    @Override
    public int editMeeting(Meeting meeting) throws RemoteException {
        
        String sql = "update meetings set meetingTitle = ?, meetingDate = ?, timeStart = ? where id = ?;";
        try {
            Connection conn = ConnectDB.connectDB();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, meeting.getTitle());
            stmt.setDate(2, meeting.getDate());
            stmt.setString(3, meeting.getTimeStart());
            stmt.setInt(4, meeting.getId());
            int i = stmt.executeUpdate();
            GUIServer.jTextArea1.append("Editted meeting "+ meeting.getTitle() +"! \n");
            conn.close();
            return i;
        } catch (SQLException ex) {
            Logger.getLogger(RemoteImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    @Override
    public Meeting getMeeting(int meetingId) throws RemoteException {
        Meeting meeting = new Meeting();
        System.out.println("Creating statement...");        
        String sql = "select * from meetings where id = ?";
        try {
            Connection conn = ConnectDB.connectDB();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, meetingId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                // Retrieve by column name
                int id  = rs.getInt("id");
                String title = rs.getString("meetingTitle");
                int userCreateId = rs.getInt("userCreateId");
                Date date = rs.getDate("meetingDate");
                String time = rs.getString("timeStart");
                // Setting the values
                meeting.setId(id);
                meeting.setUserCreateId(userCreateId);
                meeting.setTitle(title);
                meeting.setDate(date);
                meeting.setTimeStart(time);
                
                conn.close();
                return meeting;
            }
        } catch (SQLException ex) {
            Logger.getLogger(RemoteImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return meeting;
    }
    @Override
    public int deleteMeeting(Meeting meeting) throws RemoteException {
        
        String sql = "DELETE FROM meetings WHERE meetings.id = ?;";
        try {
            Connection conn = ConnectDB.connectDB();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, meeting.getId());
            int i = stmt.executeUpdate();
            GUIServer.jTextArea1.append("Deleted meeting "+ meeting.getTitle() +"! \n");
            conn.close();
            return i;
        } catch (SQLException ex) {
            Logger.getLogger(RemoteImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }  
    @Override
    public int addReporter(User user, Meeting meeting) throws RemoteException{
        String sql = "INSERT INTO userpermission (userId, meetingId, permission) VALUES ( ?, ?, 'u');";
        try {
            Connection conn = ConnectDB.connectDB();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, user.getId());
            stmt.setInt(2, meeting.getId());
            int i = stmt.executeUpdate();
            conn.close();
            return i;
        } catch (SQLException ex) {
            Logger.getLogger(RemoteImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    @Override
    public List<Integer> getReporterIds(int meetingId) throws RemoteException{
        List<Integer> list = new ArrayList<>();
        String sql = "select * from userpermission where meetingId = ? and permission = ?";
        PreparedStatement stmt;
        ResultSet rs;
        try {
            Connection conn = ConnectDB.connectDB();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, meetingId);
            stmt.setString(2, "u");
            rs = stmt.executeQuery();
            while(rs.next()) {
                // Retrieve by column name
                int id  = rs.getInt("userId");
                list.add(id);
        }
        rs.close();
        conn.close();
        return list;
        } catch (SQLException ex) {
            Logger.getLogger(RemoteImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    @Override
    public int deleteReporter(User user, Meeting meeting) throws RemoteException{
        
        String sql = "DELETE FROM userpermission WHERE userId = ? and meetingId = ? and permission = 'u';";
        try {
            Connection conn = ConnectDB.connectDB();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, user.getId());
            stmt.setInt(2, meeting.getId());
            int i = stmt.executeUpdate();
            conn.close();
            return i;
        } catch (SQLException ex) {
            Logger.getLogger(RemoteImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    @Override
    public int getMeetingCreatorId(Meeting meeting) throws RemoteException{
        
        System.out.println("Creating statement...");        
        String sql = "select * from meetings where id = ?";
        try {
            Connection conn = ConnectDB.connectDB();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, meeting.getId());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                int creatorId = rs.getInt("userCreateId");
                conn.close();
                return creatorId;
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(RemoteImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
// end RemoteImplement for meeting

    // Server status
    @Override
    public void clientLogoutMessage(User user) throws RemoteException {
        System.out.println("Client " + user.getUsername() + " logged out!");
        GUIServer.jTextArea1.append("Client " + user.getUsername() + " logged out! \n");
    }
    
    // Remote Implement for file
    @Override
    public int uploadFile(ReportPart reportPart) throws RemoteException{
        
        String content = "";
        //
        BufferedInputStream bufferedStream;
        try {
            bufferedStream = new BufferedInputStream(new FileInputStream(reportPart.getContent()));
            int nextByte;
            StringBuffer localBuffer = new StringBuffer();
            while( -1 != (nextByte = bufferedStream.read())) {
                char nextChar = (char) nextByte;
                localBuffer.append(nextChar);
            }
            content += localBuffer.toString();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RemoteImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RemoteImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "insert into reportparts (meetingId, fileName, type, reportPartContent) values (?, ?, ?, ?);";
        try {
            Connection conn = ConnectDB.connectDB();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, reportPart.getMeetingId());
            stmt.setString(2, reportPart.getFileName());
            stmt.setInt(3, reportPart.getType());
            stmt.setString(4, content);
            int i = stmt.executeUpdate();
            GUIServer.jTextArea1.append("Uploaded file to MID"+ reportPart.getMeetingId() +"! \n");
            conn.close();
            return i;
        } catch (SQLException ex) {
            Logger.getLogger(RemoteImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    @Override
    public int deleteReportPart(int reportPartId) throws RemoteException{
        
        String sql = "DELETE FROM reportparts WHERE id = ?;";
        try {
            Connection conn = ConnectDB.connectDB();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, reportPartId);
            int i = stmt.executeUpdate();
            conn.close();
            return i;
        } catch (SQLException ex) {
            Logger.getLogger(RemoteImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    @Override
    public List<ReportPart> getReportParts(int i, int y) throws RemoteException {
        final int PERSONCONTENT = 0, CONTENTTIME=1;
        GUIServer.jTextArea1.append("Load reportpart list . . .\n");
        List<ReportPart> list = new ArrayList<>();
        PreparedStatement stmt;
        System.out.println("Creating statement...");
        String sql = "select * from reportparts where type = ? and meetingId = ?";
        ResultSet rs;
        try {
            Connection conn = ConnectDB.connectDB();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, i);
            stmt.setInt(2, y);
            rs = stmt.executeQuery();
            while(rs.next()) {
                // Retrieve by column name
                int id  = rs.getInt("id");
                int meetingId = y;
                String fileName = rs.getString("fileName");
                int type = i;
                String reportPartContent = rs.getString("reportPartContent");
                // Setting the values
                ReportPart reportPart = new ReportPart();
                reportPart.setId(id);
                reportPart.setMeetingId(meetingId);
                reportPart.setType(type);
                reportPart.setFileName(fileName);
                list.add(reportPart);
        }
        rs.close();
        conn.close();
        GUIServer.jTextArea1.append("Load reportpart list done! \n");
        return list;
        } catch (SQLException ex) {
            Logger.getLogger(RemoteImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    @Override
    public String getReportPartContent(int reportPartId) throws RemoteException{
        
        PreparedStatement stmt = null;
        String sql = "select * from reportparts where id = ?";
        String out="";
        try {
            Connection conn = ConnectDB.connectDB();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, reportPartId);
            System.out.println(stmt);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                out = rs.getString("reportPartContent");
                conn.close();
                return out;
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(RemoteImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return out;
    }
    @Override
    public int deleteReportParts(int meetingId) throws RemoteException{
        String sql = "DELETE FROM reportparts WHERE meetingId = ?;";
        try {
            Connection conn = ConnectDB.connectDB();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, meetingId);
            int i = stmt.executeUpdate();
            conn.close();
            return i;
        } catch (SQLException ex) {
            Logger.getLogger(RemoteImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    // end Remote Implement for file
// Remote implement for report
    @Override
    public int addReport(Report report) throws RemoteException{
        
        String sql = "INSERT INTO reports (meetingId, reportName, reportContent, timeCreate, authors) VALUES (?, ?, ?, ?, ?);";
        Calendar cal = new GregorianCalendar();
        int second = cal.get(Calendar.SECOND);
        int minute = cal.get(Calendar.MINUTE);
        int hour = cal.get(Calendar.HOUR);

        String timeCreate = hour + ":"+minute+":"+second;
        try {
            Connection conn = ConnectDB.connectDB();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, report.getMeetingId());
            stmt.setString(2, report.getReportName());
            stmt.setString(3, report.getReportContent());
            stmt.setString(5, report.getAuthors());
            stmt.setString(4, timeCreate);
            int i = stmt.executeUpdate();
            conn.close();
            return i;
        } catch (SQLException ex) {
            Logger.getLogger(RemoteImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    @Override
    public Report getReport(int reportId) throws RemoteException{
        Report report = new Report();
        PreparedStatement stmt;
        String sql = "select * from reports where id = ?";
        try {
            Connection conn = ConnectDB.connectDB();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, reportId);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                int id = rs.getInt("id");
                int meetingId = rs.getInt("meetingId");
                String reportName = rs.getString("reportName");
                String reportContent = rs.getString("reportContent");
                String timeCreate = rs.getString("timeCreate");
                String authors = rs.getString("authors");
                report.setId(id);
                report.setMeetingId(meetingId);
                report.setReportName(reportName);
                report.setReportContent(reportContent);
                report.setTimeCreate(timeCreate);
                report.setAuthors(authors);
                conn.close();
                return report;
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(RemoteImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    @Override
    public int generateReport(Report report, Meeting meeting) throws RemoteException {
        String reportContent = "";
        Collections.sort(report.getPersonContentTimes());
        for (PersonContentTime pct: report.getPersonContentTimes()){
            reportContent += "["+ pct.getTimeBegin() +"~"+ pct.getTimeEnd() +"]"+pct.getName()+"-"+ pct.getContent() +"\n";
        }
        Calendar cal = new GregorianCalendar();
        int second = cal.get(Calendar.SECOND);
        int minute = cal.get(Calendar.MINUTE);
        int hour = cal.get(Calendar.HOUR);

        String timeCreate = hour + ":"+minute+":"+second;
        
        String sql = "insert into reports (meetingId, reportName, reportContent, timeCreate, authors) values (?, ?, ?, ?, ?);";
        try {
            Connection conn = ConnectDB.connectDB();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, report.getMeetingId());
            ps.setString(2, report.getReportName());
            ps.setString(3, reportContent);
            ps.setString(4, timeCreate);
            ps.setString(5, report.getAuthors());
            int i = ps.executeUpdate();
            conn.close();
            return i;
        } catch (SQLException ex) {
            Logger.getLogger(RemoteImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    @Override
    public List<Report> getReports(int meetingId) throws RemoteException {
        List<Report> listReport = new ArrayList<>();
        PreparedStatement stmt;
        String sql = "select * from reports where meetingId = ?";
        String out="";
        try {
            Connection conn = ConnectDB.connectDB();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, meetingId);
            System.out.println(stmt);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                
                int id = rs.getInt("id");
                String reportName = rs.getString("reportName");
                String reportContent = rs.getString("reportContent");
                String time = rs.getString("timeCreate");
                String authors = rs.getString("authors");
                Report report = new Report();
                report.setId(id);
                report.setMeetingId(meetingId);
                report.setReportName(reportName);
                report.setTimeCreate(time);
                report.setAuthors(authors);
                listReport.add(report);
            }
            conn.close();
            return listReport;
        } catch (SQLException ex) {
            Logger.getLogger(RemoteImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    @Override
    public String getReportContent(int reportId) throws RemoteException {
        PreparedStatement stmt = null;
        String sql = "select * from reports where id = ?";
        String out="";
        try {
            Connection conn = ConnectDB.connectDB();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, reportId);
            System.out.println(stmt);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                out = rs.getString("reportContent");
                conn.close();
                return out;
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(RemoteImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return out;
    }
    @Override
    public int addPeopleEdit(PeopleEditReport per) throws RemoteException {
        String sql = "INSERT INTO peopleeditreport (reportId, userId) VALUES ( ?, ?);";
        System.out.println(sql);
        try {
            Connection conn = ConnectDB.connectDB();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, per.getReportId());
            stmt.setInt(2, per.getUserId());
            int i = stmt.executeUpdate();
            conn.close();
            return i;
        } catch (SQLException ex) {
            Logger.getLogger(RemoteImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    @Override
    public List<Integer> getIdOfUserEdit(int reportId) throws RemoteException {
        PreparedStatement stmt = null;
        String sql = "select * from peopleeditreport where reportId = ?";
        List<Integer> list= new ArrayList<>();
        try {
            Connection conn = ConnectDB.connectDB();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, reportId);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                list.add(rs.getInt("userId"));
            }
            conn.close();
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(RemoteImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    @Override
    public int getPeopleEdit(PeopleEditReport per) throws RemoteException {
        PreparedStatement stmt = null;
        String sql = "select * from peopleeditreport where reportId = ? and userId = ?";
        int out;
        try {
            Connection conn = ConnectDB.connectDB();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, per.getReportId());
            stmt.setInt(2, per.getUserId());
            System.out.println(stmt);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                out = rs.getInt("id");
                conn.close();
                return out;
            }
        } catch (SQLException ex) {
            Logger.getLogger(RemoteImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    @Override
    public int removePeopleEdit(int peopleEditId) throws RemoteException{
        PreparedStatement stmt;
        String sql = "DELETE FROM peopleeditreport WHERE id = ?";
        int out;
        try {
            Connection conn = ConnectDB.connectDB();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, peopleEditId);
            int i = stmt.executeUpdate();
            conn.close();
            return i;
        } catch (SQLException ex) {
            Logger.getLogger(RemoteImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    @Override
    public int deleteReports(int meetingId) throws RemoteException{
        String sql = "DELETE FROM reports WHERE meetingId = ?;";
        try {
            Connection conn = ConnectDB.connectDB();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, meetingId);
            int i = stmt.executeUpdate();
            conn.close();
            return i;
        } catch (SQLException ex) {
            Logger.getLogger(RemoteImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    @Override
    public int deleteReport(int reportId) throws RemoteException{
        String sql = "DELETE FROM reports WHERE id = ?;";
        try {
            Connection conn = ConnectDB.connectDB();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, reportId);
            int i = stmt.executeUpdate();
            conn.close();
            return i;
        } catch (SQLException ex) {
            Logger.getLogger(RemoteImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
// End remote implement for report
    
// Remote implement for permission
    @Override
    public String getPermission(User user, Meeting meeting) throws RemoteException{
        PreparedStatement stmt;
        String sql = "select * from userpermission WHERE userId = ? and meetingId = ?";
        try {
            Connection conn = ConnectDB.connectDB();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, user.getId());
            stmt.setInt(2, meeting.getId());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                String out = rs.getString("permission");
                conn.close();
                return out;
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(RemoteImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    @Override
    public int addPermission(User user, Meeting meeting, String permission) throws RemoteException{
        String sql = "INSERT INTO userpermission (userId, meetingId, permission) VALUES (?, ?, ?);";
        try {
            Connection conn = ConnectDB.connectDB();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, user.getId());
            stmt.setInt(2, meeting.getId());
            stmt.setString(3, permission);
            int i = stmt.executeUpdate();
            conn.close();
            return i;
        } catch (SQLException ex) {
            Logger.getLogger(RemoteImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    @Override
    public int deletePermission(int meetingId) throws RemoteException{
        String sql = "DELETE FROM userpermission WHERE meetingId = ?;";
        try {
            Connection conn = ConnectDB.connectDB();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, meetingId);
            int i = stmt.executeUpdate();
            conn.close();
            return i;
        } catch (SQLException ex) {
            Logger.getLogger(RemoteImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    public int deletePermission(int userId, int meetingId) throws RemoteException{
        String sql = "DELETE FROM userpermission WHERE meetingId = ? and userId = ?;";
        try {
            Connection conn = ConnectDB.connectDB();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, meetingId);
            stmt.setInt(2, userId);
            int i = stmt.executeUpdate();
            conn.close();
            return i;
        } catch (SQLException ex) {
            Logger.getLogger(RemoteImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
//end for permission
}
