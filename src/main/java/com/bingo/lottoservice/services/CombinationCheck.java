package com.bingo.lottoservice.services;

import com.bingo.lottoservice.model.Configuration;
import com.bingo.lottoservice.utils.FileReader;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class CombinationCheck {

    private Yaml yaml = new Yaml(new Constructor(Configuration.class));
    private InputStream inputStream = new ByteArrayInputStream(new FileReader().getFileContent("test.yml").getBytes());
    Configuration configuration = yaml.load(inputStream);
    public void print() {
        System.out.println(yaml.load(inputStream).toString());
    }
    public CombinationCheck() throws IOException {
    }

}
