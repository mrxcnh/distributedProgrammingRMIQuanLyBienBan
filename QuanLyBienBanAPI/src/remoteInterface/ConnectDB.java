package remoteInterface;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import helpfile.ConfigFile;

/**
 *
 * @author thanhdovan
 */
public class ConnectDB {
    private static final String HOST = ConfigFile.getInstance().get("DBMS_HOST");
    private static final String PORT = ConfigFile.getInstance().get("DBMS_PORT");
    private static final String DATABASE = ConfigFile.getInstance().get("DBMS_DATABASE");
    private static final String USER = ConfigFile.getInstance().get("DBMS_USER");
    private static final String PASSWORD = ConfigFile.getInstance().get("DBMS_PASSWORD");
    private static final String DB_URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE + "?zeroDateTimeBehavior=convertToNull&autoReconnect=true&useSSL=false&characterEncoding=utf8"; //Địa chỉ DataBase

    public static Connection connectDB() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver"); ////Đăng kí drive
        } catch (ClassNotFoundException ex) {
            System.err.println(ex);
            return null;
        }
        System.out.println("Connecting to a selected database...");
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        } catch (SQLException ex) {
            System.err.println(ex);
            return null;
        }
        System.out.println("Connected database successfully...");
        return conn;
    }
}
