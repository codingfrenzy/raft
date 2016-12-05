import utilities.ConfigProperties;

import java.util.Set;

public class ConfigPropertiesTester {

    public static void main(String[] args) {
        Set<String> prop = ConfigProperties.getAllPropertiesNames();
        for ( String p : prop) {
            System.out.println(p + " : " + ConfigProperties.getProperty(p));
        }
//        System.out.println(Integer.valueOf("100"));
        System.out.println(Float.valueOf(ConfigProperties.getProperty("portOffset.firstServer")));
    }
}
