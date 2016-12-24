package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * utilities.ConfigProperties class
 * Stores all configuration from the property for fast retrieval.
 * The values can also be modified at runtime.
 */
public class ConfigProperties {
    private static Logger log = LoggerHelper.getLogger(ConfigProperties.class.getName());
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

    public static Set<String> getAllPropertyStartingWith(String startsWith) {
        Set<String> prop = ConfigProperties.getAllPropertiesNames();
        Set<String> propStarts = new HashSet<>();
        for (String p : prop) {
            if (p.startsWith(startsWith)) {
                propStarts.add(p);
            }
        }
        return propStarts;
    }

    public static String getProperty(String property) {
        return (String) properties.get(property);
    }

    /*
    Would throw an NumberFormatException if the string is invalid.
     */
    public static int getPropertyInt(String prop) {
        int ret = 0;
        try{
            ret = Integer.valueOf(properties.getProperty(prop));
        } catch (NumberFormatException e){
            log.log(Level.SEVERE, "Integer return could not be found for property: " + prop + " - Exception: " + e, e);
        }
        return ret;
    }

    public static Set<String> getAllPropertiesNames() {
        return properties.stringPropertyNames();
    }

    public static Integer getServerPort(String name){
        String serverBaseName = Constants.SERVER_CONFIG_PREFIX_STRING;
        return ConfigProperties.getPropertyInt(serverBaseName + name);
    }

}
