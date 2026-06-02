import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream("test.properties")) {
            if (input != null) {
                properties.load(input);
            }
        } catch (IOException exception) {
            throw new RuntimeException("Could not load test.properties", exception);
        }
    }

    public static String get(String key, String defaultValue) {
        String environmentValue = System.getenv(key.toUpperCase().replace('.', '_'));

        if (environmentValue != null && !environmentValue.trim().isEmpty()) {
            return environmentValue;
        }

        return properties.getProperty(key, defaultValue);
    }
}