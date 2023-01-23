package FileManagner;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBconfig {

    static ObjectMapper mapper = new ObjectMapper();
    static JsonNode config;

    static {
        try {
            config = mapper.readTree(new File("src\\main\\java\\FileManagner\\config.json"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String url = config.get("url").asText();
    private static String username = config.get("username").asText();
    private static String password = config.get("password").asText();


    public DBconfig() throws IOException {
    }
    public static String getUrl() {
        return url;
    }
    public static String getUsername() {
        return username;
    }
    public static String getPassword() {
        return password;
    }
}
