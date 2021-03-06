package com.bingo.lottoservice.controller;

import com.bingo.lottoservice.model.Lottery;
import com.bingo.lottoservice.services.CombinationCheck;
import com.bingo.lottoservice.utils.YamlUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/lottery", produces = MediaType.APPLICATION_JSON_VALUE)
public class LottoEndpointController {

    @RequestMapping(value = "/projects", method = RequestMethod.POST)
    public final ResponseEntity<String> JsonToYaml(@RequestBody String projects) throws JsonProcessingException {
//        JsonNode jsonNodeTree = new ObjectMapper().readTree(projects);
//        String jsonAsYaml = new YAMLMapper().writeValueAsString(jsonNodeTree);
        String jsonAsYaml = YamlUtil.JsonToYaml(projects);
        return new ResponseEntity<>(jsonAsYaml, HttpStatus.OK);
    }

    //    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/checkRewardAsJson", method = RequestMethod.POST)
    public final ResponseEntity<String> checkRewardAsJson(@RequestBody String scannedLottery) throws Exception {
        String scannedLotteryAsYaml = YamlUtil.JsonToYaml(scannedLottery);
        String quotesRemoved = scannedLotteryAsYaml.replace("\"", "");
        Yaml yaml = new Yaml(new Constructor(Lottery.class));
        Lottery lottery = yaml.load(quotesRemoved);

        CombinationCheck combinationCheck = new CombinationCheck();
        combinationCheck.setConfig(lottery);
        String checkedReward = YamlUtil.YamlToJson(combinationCheck.checkReward().toString());
        return new ResponseEntity<>(checkedReward, HttpStatus.OK);
    }

    @RequestMapping(value = "/checkrewardAsYaml", method = RequestMethod.POST)
    public final ResponseEntity<String> checkrewardAsYaml(@RequestBody String scannedLottery) throws Exception {
        Yaml yaml = new Yaml(new Constructor(Lottery.class));
        Lottery lottery = yaml.load(scannedLottery);

        CombinationCheck combinationCheck = new CombinationCheck();
        combinationCheck.setConfig(lottery);
        return new ResponseEntity<>(combinationCheck.checkReward().toString(), HttpStatus.OK);
    }

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
