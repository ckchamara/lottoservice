package com.bingo.lottoservice.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {

    private final static Logger logger = LoggerFactory.getLogger(PropertyReader.class);
    private static PropertyReader instance = new PropertyReader();
    private final static String APPLICATION_PROPERTIES = "application.properties";
    Properties props = new Properties();
    private PropertyReader() {
        load();
    }
    private void load() {
        File file = new File("./" + APPLICATION_PROPERTIES);
        if (file.exists()) {
            try (InputStream is = new FileInputStream(file)) {
                props.load(is);
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }
        } else {
            try (InputStream input = this.getClass().getClassLoader().getResourceAsStream(APPLICATION_PROPERTIES)) {
                props.load(input);
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }
        }
    }
    public static PropertyReader getInstance() {
        return instance;
    }
    public String getProperty(String key) {
        String value = props.getProperty(key);
        logger.debug("requesting property value for " + key + " ( value = " + value + " )");
        return value;
    }
}
