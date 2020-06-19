package com.bingo.lottoservice.controller;

import com.bingo.lottoservice.AppConfiguration;
import com.bingo.lottoservice.model.DeleteElasticDoc;
import com.bingo.lottoservice.model.Result2;
import com.bingo.lottoservice.utils.FileReader;
import com.bingo.lottoservice.utils.HttpClient;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/db", produces = MediaType.APPLICATION_JSON_VALUE)
public class dbController {

    @Autowired
    private AppConfiguration appConfiguration;

    private final Map<String, String> headers = new HashMap<>();

    @RequestMapping(value = "/createIndex", method = RequestMethod.POST)
    public ResponseEntity<String> createIndex(@RequestBody String indexData) throws Exception {

        Result2 resultObj = new ObjectMapper().readValue(indexData, Result2.class);
        headers.put("Content-Type", "application/json");
        //create index
        String createIndexUrl = appConfiguration.getElasticHost() + "/" + resultObj.getName();
        HttpClient.call1(createIndexUrl, headers, "PUT", null);
        //set mapping
        String indexMappingUrl = appConfiguration.getElasticHost() + "/" + resultObj.getName() + "/_mapping";
        FileReader fileReader = new FileReader();
        HttpClient.call1(indexMappingUrl, headers, "PUT", fileReader.getFileContent("resultDbMapping.json"));
        //enter values
        String indexValueEnterUrl = appConfiguration.getElasticHost() + "/" + resultObj.getName() + "/_doc/" + resultObj.getDrawNo();
        String valueEnterResponce = HttpClient.call1(indexValueEnterUrl, headers, "POST", indexData);

        System.out.println(valueEnterResponce);
        return new ResponseEntity<>(valueEnterResponce, HttpStatus.OK);
    }

    @RequestMapping(value = "/deleteDoc", method = RequestMethod.POST)
    public ResponseEntity<String> deleteDoc(@RequestBody String deleteDocDetails) throws Exception {
        DeleteElasticDoc docDetails = new ObjectMapper().readValue(deleteDocDetails, DeleteElasticDoc.class);
        String url = appConfiguration.getElasticHost() + "/" + docDetails.getLotteryName() + "/_doc/" + docDetails.getDrawNo();
        String responce = HttpClient.call1(url, headers, "DELETE", null);
        System.out.println(responce);
        return new ResponseEntity<>(responce, HttpStatus.OK);
    }

    @RequestMapping(value = "/retriveDoc", method = RequestMethod.POST)
    public ResponseEntity<String> retriveDoc(@RequestBody String retriveDocDetails) throws Exception {
        DeleteElasticDoc docDetails = new ObjectMapper().readValue(retriveDocDetails, DeleteElasticDoc.class);
        String getDocValueUrl = appConfiguration.getElasticHost() + "/" + docDetails.getLotteryName() + "/_doc/" + docDetails.getDrawNo();
        String respond = HttpClient.call1(getDocValueUrl, headers, "GET", null);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode actualObj = mapper.readTree(respond);
        System.out.println(actualObj.get("_source"));
        return new ResponseEntity<>(actualObj.get("_source").toString(), HttpStatus.OK);
    }

}

