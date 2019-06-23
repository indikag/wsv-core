package com.wsv.core.util;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigProperty {

    private static ConfigProperty instance = new ConfigProperty();
    private InputStream inputStream;
    private Properties properties = new Properties();

    public static ConfigProperty getInstance() {
        return instance;
    }

    private ConfigProperty() {
        inputStream = getClass().getClassLoader().getResourceAsStream("config.properties");
        if (inputStream != null) {
            try {
                properties.load(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String readProperty(String propertyName) {
        return properties.getProperty(propertyName);
    }
}
