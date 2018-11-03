/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remoteImpl;

import entity.Meeting;
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
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import mainServer.GUIServer;
import remoteInterface.ConnectDB;
import remoteInterface.RemoteInterface;

/**
 *
 * @author thanhdovan
 */
public class RemoteImpl implements RemoteInterface {
    
    // Remote Implement for user
    
    @Override
    public List<User> getUsers() throws RemoteException {
        GUIServer.jTextArea1.append("Load user list . . .\n");
        List<User> users = new ArrayList<>();
        Connection conn = ConnectDB.connectDB();
        Statement stmt;
        String sql = "select * from users";
        ResultSet rs;
        try {
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
        Connection conn = ConnectDB.connectDB();
        System.out.println("Creating statement...");        
        String sql = "select * from users where username = ? and password = ?";
        try {
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
        Connection conn = ConnectDB.connectDB();
        System.out.println("Creating statement...");
        String sql = "INSERT INTO users (username, password, fullname, position) VALUES "
                +"('"+ user.getUsername() +"' ,'"+user.getPassword() + "', '"+ user.getFullname()+"', '"+user.getPosition()+"');";
        try {
            Statement stmt = conn.createStatement();
            int i = stmt.executeUpdate(sql);
            GUIServer.jTextArea1.append("Adding done!\n");
            return i;
        } catch (SQLException ex) {
            Logger.getLogger(RemoteImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public User getUser(int id) throws RemoteException {
        User user = new User();
        Connection conn = ConnectDB.connectDB();
        System.out.println("Creating statement...");        
        String sql = "select * from users where id = ?";
        try {
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
                
                return user;
            }
        } catch (SQLException ex) {
            Logger.getLogger(RemoteImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    @Override
    public int editUser(User user) throws RemoteException {
        Connection conn = ConnectDB.connectDB();
        System.out.println("Creating statement...");
        String sql = "update users set username = ?, password = ?, fullname = ?, position = ? where id = ?;";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getFullname());
            stmt.setString(4, user.getPosition());
            stmt.setInt(5, user.getId());
            System.out.println(stmt);
            int i = stmt.executeUpdate();
            GUIServer.jTextArea1.append("Edited user " +user.getUsername()+ "!\n");
            return i;
        } catch (SQLException ex) {
            Logger.getLogger(RemoteImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public int deleteUser(User user) throws RemoteException {
        Connection conn = ConnectDB.connectDB();
        System.out.println("Creating statement...");
        String sql = "DELETE FROM users WHERE users.id = ?;";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, user.getId());
            System.out.println(stmt);
            int i = stmt.executeUpdate();
            GUIServer.jTextArea1.append("Deleted user "+ user.getUsername() +"!\n");
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
        Connection conn = ConnectDB.connectDB();
        Statement stmt;
        System.out.println("Creating statement...");
        String sql = "select * from meetings";
        ResultSet rs;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next()) {
                // Retrieve by column name
                int id  = rs.getInt("id");
                String title = rs.getString("meetingTitle");
                Date date = rs.getDate("meetingDate");
                String time = rs.getString("timeStart");
                DateFormat formatter = new SimpleDateFormat("HH:mm");
                Time timeValue = new java.sql.Time(formatter.parse(time).getTime());
                // Setting the values
                Meeting meeting = new Meeting();
                meeting.setId(id);
                meeting.setTitle(title);
                meeting.setDate(date);
                meeting.setTimeStart(timeValue);
                list.add(meeting);
        }
        rs.close();
        GUIServer.jTextArea1.append("Load user list done! \n");
        return list;
        } catch (SQLException ex) {
            Logger.getLogger(RemoteImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(RemoteImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public int addMeeting(Meeting meeting) throws RemoteException {
        Connection conn = ConnectDB.connectDB();
        GUIServer.jTextArea1.append("Adding new meeting:"
                + "title: " + meeting.getTitle() + "\n"
                + "date: " + meeting.getDate() + "\n"
                + "time: " + meeting.getTimeStart() + "\n. . .");
        String sql = "INSERT INTO meetings (meetingTitle, meetingDate, timeStart) VALUES ( ?, ?, ?);";
        System.out.println(sql);
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, meeting.getTitle());
            stmt.setDate(2, meeting.getDate());
            stmt.setString(3, meeting.getTimeStart().toString());
            int i = stmt.executeUpdate();
            GUIServer.jTextArea1.append("Adding done! \n");
            return i;
        } catch (SQLException ex) {
            Logger.getLogger(RemoteImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public int editMeeting(Meeting meeting) throws RemoteException {
        Connection conn = ConnectDB.connectDB();
        String sql = "update meetings set meetingTitle = ?, meetingDate = ?, timeStart = ? where id = ?;";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, meeting.getTitle());
            stmt.setDate(2, meeting.getDate());
            stmt.setString(3, meeting.getTimeStart().toString());
            stmt.setInt(4, meeting.getId());
            int i = stmt.executeUpdate();
            GUIServer.jTextArea1.append("Editted meeting "+ meeting.getTitle() +"! \n");
            return i;
        } catch (SQLException ex) {
            Logger.getLogger(RemoteImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public Meeting getMeeting(int meetingId) throws RemoteException {
        Meeting meeting = new Meeting();
        Connection conn = ConnectDB.connectDB();
        System.out.println("Creating statement...");        
        String sql = "select * from meetings where id = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, meetingId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                // Retrieve by column name
                int id  = rs.getInt("id");
                String title = rs.getString("meetingTitle");
                Date date = rs.getDate("meetingDate");
                String time = rs.getString("timeStart");
                DateFormat formatter = new SimpleDateFormat("HH:mm");
                Time timeValue = new java.sql.Time(formatter.parse(time).getTime());
                // Setting the values
                meeting.setId(id);
                meeting.setTitle(title);
                meeting.setDate(date);
                meeting.setTimeStart(timeValue);

                
                return meeting;
            }
        } catch (SQLException ex) {
            Logger.getLogger(RemoteImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) { 
            Logger.getLogger(RemoteImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return meeting;
    }

    @Override
    public int deleteMeeting(Meeting meeting) throws RemoteException {
        Connection conn = ConnectDB.connectDB();
        String sql = "DELETE FROM meetings WHERE meetings.id = ?;";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, meeting.getId());
            int i = stmt.executeUpdate();
            GUIServer.jTextArea1.append("Deleted meeting "+ meeting.getTitle() +"! \n");
            return i;
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
    public int uploadFile(ReportPart reportPart) throws RemoteException{
        Connection conn = ConnectDB.connectDB();
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
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, reportPart.getMeetingId());
            stmt.setString(2, reportPart.getFileName());
            stmt.setInt(3, reportPart.getType());
            stmt.setString(4, content);
            int i = stmt.executeUpdate();
            GUIServer.jTextArea1.append("Uploaded file to MID"+ reportPart.getMeetingId() +"! \n");
            return i;
        } catch (SQLException ex) {
            Logger.getLogger(RemoteImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    // end Remote Implement for file
}
