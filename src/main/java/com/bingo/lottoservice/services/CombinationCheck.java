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

    public void checkReward() throws Exception {
        double rewardPrize = 0;
        List<Integer> matchingPositions = null;
        String ruleName = null;
        //get result positions
        LinkedHashMap<Integer, Object> resultpositions = mergeHashmaps(result.getPositions());
        LinkedHashMap<Integer, String> lotteryPositionTypes = mergeHashmaps(configuration.getPositions());
        LinkedHashMap<Integer, Object> lotteryPositions = mergeHashmaps(lottery.getPositions());
        //get config rule
        ruleCheckingLoop:
        for (Rule rule:configuration.getRules()) {
            System.out.println(rule.getRule());
            matchingPositions = new ArrayList<>();
            //get rule positions
            for (int rulePosition:rule.getPositions()) {
                System.out.println(rulePosition);
                int t = rule.getPositions().size();

                if ("number".equals(lotteryPositionTypes.get(rulePosition))) {
                    if (resultpositions.get(rulePosition).toString()
                            .equals(lotteryPositions.get(rulePosition).toString())) {
                        matchingPositions.add(rulePosition);
                    } else break;
                } else if ("letter".equals(lotteryPositionTypes.get(rulePosition))) {
                    if (resultpositions.get(rulePosition).toString()
                            .equals(lotteryPositions.get(rulePosition).toString())) {
                        matchingPositions.add(rulePosition);
                    } else break;
                } else
                    throw new Exception("Invalid Lottery configuration number type"); //throw exception here - invalid lottery type

                ruleName = rule.getRule();
                rewardPrize = rule.getPrize();
            }
            int o = matchingPositions.size();
            String s = matchingPositions.toString();
            if (rule.getPositions().size() == matchingPositions.size())
                break;
        }
        Map<String, Object> rewardAndValues = new HashMap<>();
        rewardAndValues.put("reward", rewardPrize);
        rewardAndValues.put("positions", matchingPositions);
        rewardAndValues.put("ruleName", ruleName);
        System.out.println(rewardAndValues);
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

    public <T, V> LinkedHashMap<T, V> mergeHashmaps(ArrayList<LinkedHashMap<T, V>> mapList) {
        LinkedHashMap<T, V> mergeMap = new LinkedHashMap<>();
        for (LinkedHashMap<T,V> singleMap:mapList) {
            mergeMap.putAll(singleMap);
        }
        return mergeMap;
    }
}
