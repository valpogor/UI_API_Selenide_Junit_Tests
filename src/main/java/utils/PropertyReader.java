package utils;

import lombok.SneakyThrows;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Properties;

// Singleton pattern
public class PropertyReader {
    private static PropertyReader instance = new PropertyReader();
    private Properties properties;
    public static String baseUrl = PropertyReader.getInstance().getBaseUrl();
    public static String pwd = PropertyReader.getInstance().getPwd();
    public static String email = PropertyReader.getInstance().getEmail();
    public static String emailErr = PropertyReader.getInstance().getEmailErr();
    public static String url = PropertyReader.getInstance().getURL();
    public static String tok = PropertyReader.getInstance().getTok();
    public static String errorMsg = PropertyReader.getInstance().getErrorMsg();
    public static String id = PropertyReader.getInstance().getId();
    public static String job = PropertyReader.getInstance().getJob();
    public static String errorMsgRegister = PropertyReader.getInstance().getErrorMsgRegister();

    @SneakyThrows
    public PropertyReader() {
        properties = new Properties();
        try (BufferedReader reader = new BufferedReader(new FileReader("./src/test/resources/test.properties"))) {
            properties.load(reader);
        }
    }

    public static PropertyReader getInstance() {
        return instance;
    }

    public String getURL(){ return properties.getProperty("url");}
    public String getPassword(){
        return properties.getProperty("password");
    }
    public String getUsername(){
        return properties.getProperty("username");
    }
    public String getBaseUrl(){
        return properties.getProperty("baseUrl");
    }
    public String getHeadless() { return properties.getProperty("headless"); }
    public String getBrowser() {return properties.getProperty("browser");}
    public String getPwd() {return properties.getProperty("pwd");}
    public String getEmail() {return properties.getProperty("email");}
    public String getEmailErr() {return properties.getProperty("emailErr");}
    public String getTok() {return properties.getProperty("tok");}
    public String getErrorMsg() {return properties.getProperty("errorMsg");}
    public String getErrorMsgRegister() {return properties.getProperty("errorMsgRegister");}
    public String getId() {return properties.getProperty("id");}
    public String getJob() {return properties.getProperty("job");}
}
