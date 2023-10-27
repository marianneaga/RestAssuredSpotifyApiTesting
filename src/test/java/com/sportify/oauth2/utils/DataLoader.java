package com.sportify.oauth2.utils;

import java.util.Properties;

public class DataLoader {

    private final Properties properties;
    private static DataLoader dataLoader;

    private DataLoader() {
        properties = PropertyUtils.propertyLoader("src/test/resources/data.properties");
    }

    //This ensures that we load the config properties only once.
    public static DataLoader getInstance() {
        if (dataLoader == null) {
            dataLoader = new DataLoader();
        }
        return dataLoader;
    }

    public String getPlaylistId() {
        String properties = this.properties.getProperty("get_playlist_id");
        if (properties != null) return properties;
        else throw new RuntimeException("Property get_playlist_id is not specified in the config.properties file");
    }
    public String getUpdatePlaylistId() {
        String properties = this.properties.getProperty("update_playlist_id");
        if (properties != null) return properties;
        else throw new RuntimeException("Property update_playlist_id is not specified in the config.properties file");
    }
}
