package com.bingo.lottoservice.utils;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;

public class FileReader {

    private final static Logger logger = LoggerFactory.getLogger(FileReader.class);
    public String getFileContent(String fileName) throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        logger.debug("requesting file : " + fileName);
        return IOUtils.toString(classLoader.getResourceAsStream(fileName), "UTF-8");
    }
}
