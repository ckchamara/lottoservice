package com.bingo.lottoservice.services;

import com.bingo.lottoservice.model.Configuration;
import com.bingo.lottoservice.utils.FileReader;
import org.apache.coyote.http11.InputFilter;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class CombinationCheck {

   // InputStream input = new FileInputStream(new FileReader().getFileContent("E:/Projects/lottoservice/src/main/java/com/bingo/lottoservice/DbUtil/test.yml"));
//    InputStream input = this.getClass()
//           .getClassLoader()
//           .getResourceAsStream("test.yml");
    Resource resource = new ClassPathResource("classpath:com/bingo/lottoservice/DbUtil/test.yml");
    InputStream input = resource.getInputStream();
    Configuration ymlConfig = new Yaml().load(input);

    public void print(){
        System.out.println(ymlConfig);
    }
    public CombinationCheck() throws IOException {
    }
}
