package helpfile;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
/**
 *
 * @author Long
 * @date created Nov 3, 2018 5:10:34 PM
 * @class name ConfigFile
 */
public class ConfigFile {
    //Đường dẫn tới file config
    public static final String DEFAULT_NAME = (System.getProperty("os.name").contains("Windows") ?  (System.getProperty("user.dir").substring(0, System.getProperty("user.dir").indexOf("QuanLyBienBan\\") + "QuanLyBienBan\\".length())) : (System.getProperty("user.dir").substring(0, System.getProperty("user.dir").indexOf("QuanLyBienBan/") + "QuanLyBienBan/".length())))  + "config.properties";
    public static Properties p;
    
    private ConfigFile() {
        try {
            p = new Properties();
            p.load(new FileReader(new File(DEFAULT_NAME)));
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    public static ConfigFile getInstance() {
//        System.out.println(System.getProperty("user.dir"));
        return ConfigFileHolder.INSTANCE;
    }
    private static class ConfigFileHolder {
        private static final ConfigFile INSTANCE = new ConfigFile();
    }
    
    public boolean set(String key, String value) {
        try {
            String storeValue = get(key);
            if (value.equals(storeValue)) {
                return true;
            }
            p.setProperty(key, value);
            p.store(new FileOutputStream(DEFAULT_NAME), null);
            return true;
        } catch (IOException ex) {
            System.err.println(ex);
            return false;
        }
    }

    public String get(String key) {
        return p.getProperty(key);
    }


}
