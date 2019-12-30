package com.bingo.lottoservice.utils;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class LoadYAML {
    public static <T> T load(Class<T> myClass,String ymlFileName) throws IOException {
         Yaml yaml = new Yaml(new Constructor(myClass));
         InputStream inputStream = new ByteArrayInputStream(new FileReader().getFileContent(ymlFileName).getBytes());
         return yaml.load(inputStream);
    }
}
