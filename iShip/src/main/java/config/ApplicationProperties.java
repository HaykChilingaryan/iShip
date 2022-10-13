package config;

import java.io.IOException;
import java.util.Properties;

public class ApplicationProperties {

    private final Properties properties;

    public ApplicationProperties() {
        properties = new Properties();
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("config.properties"));

        } catch (IOException ioex) {
        	ioex.printStackTrace();
        }
    }

    public String readProperty(String keyName) {
        return properties.getProperty(keyName, "There is no key in the properties file");
    }

}
