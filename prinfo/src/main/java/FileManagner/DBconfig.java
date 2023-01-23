package FileManagner;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

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

    private static final String url = config.get("url").asText();
    private static final String username = config.get("username").asText();
    private static final String password = config.get("password").asText();


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
