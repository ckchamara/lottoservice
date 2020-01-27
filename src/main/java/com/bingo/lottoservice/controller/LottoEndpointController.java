package com.bingo.lottoservice.controller;

import com.bingo.lottoservice.utils.YamlUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/lottery", produces = MediaType.APPLICATION_JSON_VALUE)
public class LottoEndpointController {

    @RequestMapping(value = "/projects", method = RequestMethod.POST)
    public final ResponseEntity<String> JsonToYaml(@RequestBody String projects) throws JsonProcessingException {
//        JsonNode jsonNodeTree = new ObjectMapper().readTree(projects);
//        String jsonAsYaml = new YAMLMapper().writeValueAsString(jsonNodeTree);
        String jsonAsYaml = YamlUtil.JsonToYaml(projects);
        return new ResponseEntity<>(jsonAsYaml, HttpStatus.OK);
    }

//    @RequestMapping(value = "/checkreward", method = RequestMethod.POST)
//    public final ResponseEntity<String> checkReward(@RequestBody String scannedLottery) throws Exception {
//        Yaml yaml = new Yaml(new Constructor(Lottery.class));
//        Lottery lottery = yaml.load(scannedLottery);
//
//        CombinationCheck combinationCheck = new CombinationCheck();
//        combinationCheck.setConfig(lottery);
//        return new ResponseEntity<>(combinationCheck.checkReward().toString(), HttpStatus.OK);
//    }

    @RequestMapping(value = "/check/{lotteryName}", method = RequestMethod.POST)
    public ResponseEntity<String> pathParam(@PathVariable(value = "lotteryName") String lotteryName) {
        return new ResponseEntity<>(lotteryName + " chamara", HttpStatus.OK);
    }

    @RequestMapping(value = "/YamlToJson", method = RequestMethod.POST)
    public ResponseEntity<String> YamlToJson(@RequestBody String yaml) throws JsonProcessingException {
        ObjectMapper yamlReader = new ObjectMapper(new YAMLFactory());
        Object obj = yamlReader.readValue(yaml, Object.class);
        ObjectMapper jsonWriter = new ObjectMapper();
        return new ResponseEntity<>(jsonWriter.writeValueAsString(obj), HttpStatus.OK);
    }

}
