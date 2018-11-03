/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remoteInterface;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author thanhdovan
 */
public class ConnectDB {
    public static Connection connectDB(){
        String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        String DB_URL = "jdbc:mysql://localhost:3306/quanlybienban";
        String usr = "root";
        String pass = "Abc@123456";
        Connection conn = null;        
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Connecting to a selected database..."); 
        try {
            conn = DriverManager.getConnection(DB_URL, usr, pass);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Connected database successfully...");  
        return conn;
    }
}
