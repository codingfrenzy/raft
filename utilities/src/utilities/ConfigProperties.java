package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

/**
 * utilities.ConfigProperties class
 * Stores all configuration from the property for fast retrieval.
 * The values can also be modified at runtime.
 */
public class ConfigProperties {
    private static Properties properties;

    static {
        properties = new Properties();
        try (FileInputStream configFile = new FileInputStream(Constants.PROPERTIES_FILE_PATH)) {
            properties.load(configFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String property){
        return (String) properties.get(property);
    }

    /*
    Would throw an NumberFormatException if the string is invalid.
     */
    public static int getPropertyInt(String prop){
        return Integer.valueOf(properties.getProperty(prop));
    }

    public static Set<String> getAllPropertiesNames(){
        return properties.stringPropertyNames();
    }
}
