package com.bingo.lottoservice.utils;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
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


    public static <T> T loadYamlfromFTP(Class<T> myClass, String ftpFileLink) throws IOException {
        URL url = new URL(ftpFileLink);
        URLConnection urlc = url.openConnection();
        InputStream inputStream = urlc.getInputStream();
        Yaml yaml = new Yaml(new Constructor(myClass));
        return yaml.load(inputStream);
    }

}
