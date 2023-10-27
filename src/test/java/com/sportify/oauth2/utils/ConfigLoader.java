package com.sportify.oauth2.utils;

import java.util.Properties;

public class ConfigLoader {

    private final Properties properties;
    private static ConfigLoader configLoader;

    private ConfigLoader() {
        properties = PropertyUtils.propertyLoader("src/test/resources/config.properties");
    }

    //This ensures that we load the config properties only once.
    public static ConfigLoader getInstance() {
        if (configLoader == null) {
            configLoader = new ConfigLoader();
        }
        return configLoader;
    }

    public String getClientId() {
        String properties = this.properties.getProperty("client_id");
        if (properties != null) return properties;
        else throw new RuntimeException("Property client_id is not specified in the config.properties file");
    }
    public String getClientSecret() {
        String properties = this.properties.getProperty("client_secret");
        if (properties != null) return properties;
        else throw new RuntimeException("Property client_secret is not specified in the config.properties file");
    }
    public String getGrantType() {
        String properties = this.properties.getProperty("grant_type");
        if (properties != null) return properties;
        else throw new RuntimeException("Property grant_type is not specified in the config.properties file");
    }
    public String getRefreshToken() {
        String properties = this.properties.getProperty("refresh_token");
        if (properties != null) return properties;
        else throw new RuntimeException("Property refresh_token is not specified in the config.properties file");
    }
    public String getUserId() {
        String properties = this.properties.getProperty("user_id");
        if (properties != null) return properties;
        else throw new RuntimeException("Property user_id is not specified in the config.properties file");
    }

}
