package com.bingo.lottoservice.repository;

import com.bingo.lottoservice.AppConfiguration;
import com.bingo.lottoservice.model.Result;
import com.bingo.lottoservice.model.Result2;
import com.bingo.lottoservice.utils.FileReader;
import com.bingo.lottoservice.utils.HttpClient;
import com.bingo.lottoservice.utils.YamlUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import javax.management.StandardEmitterMBean;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/db", produces = MediaType.APPLICATION_JSON_VALUE)
public class dbController {

    @Autowired
    private AppConfiguration appConfiguration;

    @RequestMapping(value = "/createIndex", method = RequestMethod.POST)
    public ResponseEntity<String> createIndex(@RequestBody String indexData) throws Exception {

        Result2 resultObj = new ObjectMapper().readValue(indexData, Result2.class);
        Map<String,String> headers = new HashMap<>();
        headers.put("Content-Type","application/json");
        //create index
        String createIndexUrl = appConfiguration.getElasticHost()+"/"+ resultObj.getName();
        HttpClient.call1(createIndexUrl,headers,"PUT",null);
        //set mapping
        String indexMappingUrl = appConfiguration.getElasticHost()+"/"+ resultObj.getName() +"/_mapping";
        FileReader fileReader = new FileReader();
        HttpClient.call1(indexMappingUrl,headers,"PUT", fileReader.getFileContent("resultDbMapping.json"));
        //enter values
        String indexValueEnterUrl = appConfiguration.getElasticHost()+"/"+ resultObj.getName() +"/_doc/" + resultObj.getDrawNo();
        String valueEnterResponce = HttpClient.call1(indexValueEnterUrl,headers,"POST", indexData);

        System.out.println(valueEnterResponce);
        return new ResponseEntity<>(valueEnterResponce, HttpStatus.OK);
    }

}
