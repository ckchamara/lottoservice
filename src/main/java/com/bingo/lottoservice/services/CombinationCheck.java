package com.bingo.lottoservice.services;

import com.bingo.lottoservice.model.Configuration;
import com.bingo.lottoservice.model.Lottery;
import com.bingo.lottoservice.model.Result;
import com.bingo.lottoservice.model.Rule;
import com.bingo.lottoservice.utils.LoadYAML;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.IOException;
import java.util.*;

public class CombinationCheck {

    public CombinationCheck() throws IOException {
    }

    Configuration configuration = LoadYAML.load(Configuration.class, "configuration.yml");
    Result result = LoadYAML.load(Result.class,"result.yml");
    Lottery lottery = LoadYAML.load(Lottery.class,"lottery.yml");

    public void print() {
        System.out.println(configuration.getId());
        System.out.println(result.getName());
        System.out.println(lottery.getName());
    }

    public void checkReward() throws NullPointerException{
        //get config rule
        for (Rule rule:configuration.getRules()) {
            System.out.println(rule.getRule());
            //get rule positions
            for (int rulePosition:rule.getPositions()) {
                //get result position
                ArrayList<LinkedHashMap<Integer, Object>> resultPositions = result.getPositions();
                //get lotteryPositions
                ArrayList<LinkedHashMap<Integer, Object>> lotteryPositions = lottery.getPositions();
                //check wether resultPosition match to loteryPositoin
                LinkedHashMap<Integer,Object> avc = merge133(lotteryPositions);
                System.out.println(merge133(lotteryPositions));
                //for loop to retrive LinkedHashMap

            }
        }

    }

    public boolean compareLottreryResultMatch(Object resultValue,int resultPosition){
        ArrayList<LinkedHashMap<Integer,Object>> lotteryPositions = lottery.getPositions();
        for (LinkedHashMap<Integer,Object> lotteryPosition: lotteryPositions) {
            if (lotteryPosition.get(resultPosition) == resultValue){
                return true;
            }
        }
        return false;
    }

    //combine all the LinkedHashMapa into one
    public List<Integer> extractMapValuesToList(ArrayList<LinkedHashMap<Integer, Object>> mapList){
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (LinkedHashMap<Integer, Object> map:mapList) {
            map.values().stream()
                    .filter(e -> NumberUtils.isCreatable(e.toString()))
                    .forEach(e -> arrayList.add(Integer.valueOf(e.toString())));
        }
        return arrayList;
    }

    public LinkedHashMap<Integer,Object> merge(ArrayList<LinkedHashMap<Integer, Object>> mapList){
        LinkedHashMap<Integer, Object> mergeMap = new LinkedHashMap<>();
        for (LinkedHashMap<Integer,Object> singleMap:mapList) {
            mergeMap.putAll(singleMap);
        }
       return mergeMap;
    }

    public <T,V> LinkedHashMap<T,V> merge133(ArrayList<LinkedHashMap<T, V>> mapList){
        LinkedHashMap<T, V> mergeMap = new LinkedHashMap<>();
        for (LinkedHashMap<T,V> singleMap:mapList) {
            mergeMap.putAll(singleMap);
        }
        return mergeMap;
    }
}
