package api.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * ConfigManager loads properties from config.properties file.
 * It supports both required and default-value retrieval.
 */
public class ConfigManager {

    private static Properties properties = new Properties();

    static {
        try {
            // Load from src/test/resources/config.properties
            FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
            properties.load(fis);
        } catch (IOException e) {
            System.err.println("⚠️ Could not load config.properties file: " + e.getMessage());
        }
    }

    /**
     * Get a property value (required).
     * @param key property name
     * @return value or null if not found
     */
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    /**
     * Get a property value with a default fallback.
     * @param key property name
     * @param defaultValue fallback if not found
     * @return value or defaultValue
     */
    public static String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }
}
