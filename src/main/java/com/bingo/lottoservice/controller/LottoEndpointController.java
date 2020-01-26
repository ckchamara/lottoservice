package com.bingo.lottoservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/lottery", produces = MediaType.APPLICATION_JSON_VALUE)
public class LottoEndpointController {

    @RequestMapping(value = "/projects", method = RequestMethod.POST)
    public final ResponseEntity<String> JsonToYaml(@RequestBody String projects) throws JsonProcessingException {
        JsonNode jsonNodeTree = new ObjectMapper().readTree(projects);
        String jsonAsYaml = new YAMLMapper().writeValueAsString(jsonNodeTree);
        return new ResponseEntity<>(jsonAsYaml, HttpStatus.OK);
    }

    @RequestMapping(value = "/check/{lotteryName}", method = RequestMethod.POST)
    public ResponseEntity<String> pathParam(@PathVariable(value = "lotteryName") String lotteryName) {

        return new ResponseEntity<>(lotteryName + " chamara", HttpStatus.OK);
    }

    @RequestMapping(value = "/hello/{id}", method = RequestMethod.POST)
    public ResponseEntity<String> pathParam1(@PathVariable(value = "id") String id) {
        return new ResponseEntity<>(id + " chamara", HttpStatus.OK);
    }

    @RequestMapping(value = "/YamlToJson", method = RequestMethod.POST)
    public ResponseEntity<String> YamlToJson(@RequestBody String yaml) throws JsonProcessingException {
        ObjectMapper yamlReader = new ObjectMapper(new YAMLFactory());
        Object obj = yamlReader.readValue(yaml, Object.class);
        ObjectMapper jsonWriter = new ObjectMapper();
        return new ResponseEntity<>(jsonWriter.writeValueAsString(obj), HttpStatus.OK);
    }

}
