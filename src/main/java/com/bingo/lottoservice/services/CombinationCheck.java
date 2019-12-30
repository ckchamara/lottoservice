package com.bingo.lottoservice.services;

import com.bingo.lottoservice.model.Configuration;
import com.bingo.lottoservice.model.Lottery;
import com.bingo.lottoservice.model.Result;
import com.bingo.lottoservice.model.Rule;
import com.bingo.lottoservice.utils.LoadYAML;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class CombinationCheck {

    public CombinationCheck() throws IOException {
    }

    Configuration configuration = LoadYAML.load(Configuration.class,"test.yml");
    Result result = LoadYAML.load(Result.class,"result.yml");
    Lottery lottery = LoadYAML.load(Lottery.class,"lottery.yml");

    ArrayList<LinkedHashMap<Integer, String>> resultpositions = result.getPositions();
    List<Rule> s =configuration.getRules();

    public void print() {
        System.out.println(configuration.getId());
        System.out.println(result.getName());
        System.out.println(lottery.getName());
    }

    public void checkReward(){
       ArrayList<LinkedHashMap<Integer, String>> resultpositions = result.getPositions();

       List<Rule> Rules =configuration.getRules();
       for (Rule rule : Rules){
           List<Integer> rulePositions = rule.getPositions();
           for (int rulePosition : rulePositions){
               for (LinkedHashMap<Integer, String> resultPosition: resultpositions) {
//                   if (rulePosition==resultPosition)
               }
           }
       }

    }

}
