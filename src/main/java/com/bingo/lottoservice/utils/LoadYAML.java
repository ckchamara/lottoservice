package com.bingo.lottoservice.utils;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class LoadYAML {
    public static <T> T load(Class clz, Class<T> myClass, String ymlFilePath) throws IOException, URISyntaxException {
        Path filePath = Paths.get(Objects.requireNonNull(clz.getClassLoader().getResource(ymlFilePath)).toURI());
        Yaml yaml = new Yaml(new Constructor(myClass));
        //InputStream inputStream = new ByteArrayInputStream(new FileReader().getFileContent(filePath.toString()).getBytes());
        InputStream inputStream = new FileInputStream(filePath.toFile());
        return yaml.load(inputStream);
    }

    public static <T> T loadByString(Class Class, String ymlPath) {
        Yaml yaml = new Yaml();
//        InputStream inputStream = Class.getClass()
//                .getClassLoader()
//                .getResourceAsStream(ymlPath);
        return yaml.load(ymlPath);
    }

}
