package com.bingo.lottoservice.services;

import com.bingo.lottoservice.model.Configuration;
import com.bingo.lottoservice.model.Lottery;
import com.bingo.lottoservice.model.Result;
import com.bingo.lottoservice.model.Rule;
import com.bingo.lottoservice.utils.LoadYAML;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class CombinationCheck {

    private Configuration configuration = LoadYAML.load(Configuration.class, "govisetha_config.yml");

    public CombinationCheck() throws IOException {

    }

    private Result result = LoadYAML.load(Result.class, "result.yml");
    private Lottery lottery = LoadYAML.load(Lottery.class, "lottery.yml");


    public void print() {
        System.out.println(configuration.getId());
        System.out.println(result.getName());
        System.out.println(lottery.getName());

        configuration.getRules().stream()
                .filter(rule -> rule.getNonFixedPositions() != null)
                .forEach(rule -> System.out.println(rule.getNonFixedPositions() + rule.getRule()));
    }

    public void checkReward() throws Exception {
        double rewardPrize = 0;
        List<Integer> matchingPositions = null;
        String ruleName = null;
        //get result positions
        LinkedHashMap<Integer, Object> resultPositions = mergeHashmaps(result.getPositions());
        LinkedHashMap<Integer, String> lotteryPositionTypes = mergeHashmaps(configuration.getPositions());
        LinkedHashMap<Integer, Object> lotteryPositions = mergeHashmaps(lottery.getPositions());

        //get config rule
        ruleCheckingLoop:
        for (Rule rule:configuration.getRules()) {
            System.out.println(rule.getRule());
            matchingPositions = new ArrayList<>();
            boolean noMatchingPositionalValues = false;

            //logic for fixed positions
            if (rule.getPositions() != null) {
                for (int rulePosition : rule.getPositions()) {
                    System.out.println("Fix " + rulePosition);

                    if ("number".equals(lotteryPositionTypes.get(rulePosition))) {
                        if (resultPositions.get(rulePosition).toString()
                                .equals(lotteryPositions.get(rulePosition).toString())) {
                            matchingPositions.add(rulePosition);
                        } else {
                            noMatchingPositionalValues = true;
                            break;
                        }
                    } else if ("letter".equals(lotteryPositionTypes.get(rulePosition))) {
                        if (resultPositions.get(rulePosition).toString()
                                .equals(lotteryPositions.get(rulePosition).toString())) {
                            matchingPositions.add(rulePosition);
                        } else {
                            noMatchingPositionalValues = true;
                            break;
                        }
                    } else
                        throw new Exception("Invalid Lottery configuration number type"); //throw exception here - invalid lottery type

//                    ruleName = rule.getRule();
//                    rewardPrize = rule.getPrize();
                }
            }

            //logic for non-fixed positions
            if (rule.getNonFixedPositions() != null && noMatchingPositionalValues == false) {
                for (int nonFixPosition : rule.getNonFixedPositions()) {
                    System.out.println("Non-Fix " + nonFixPosition);
                    if (checkPositionIndexType(nonFixPosition, lotteryPositionTypes).equals("number")) {

                        List<Integer> filteredIndices = resultPositions.keySet()
                                .stream()
                                .filter(resultIndex -> checkPositionIndexType(resultIndex, lotteryPositionTypes).equals("number"))
                                .collect(Collectors.toList());

//                        Set<Object> existing = new HashSet<>();
//                        lotteryPositions = lotteryPositions.entrySet()
//                                .stream()
//                                .filter(entry -> existing.add(entry.getValue()))
//                                .collect(Collectors.toMap());

                        //if identical values exist in lottery -> check
                        for (int filteredIndex : filteredIndices) {
                            if (lotteryPositions.get(nonFixPosition).toString()
                                    .equals(resultPositions.get(filteredIndex).toString())) {
                                matchingPositions.add(nonFixPosition);
                                break;
                            }
                        }
                        ruleName = rule.getRule();
                        rewardPrize = rule.getPrize();
                        if (matchingPositions.size() == rule.getMatchingCount())
                            break ruleCheckingLoop;
                    }
                }
            }

            ruleName = rule.getRule();
            rewardPrize = rule.getPrize();
            if (matchingPositions.size() == rule.getMatchingCount())
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

    public LinkedHashMap<Integer,Object> mergeHash(ArrayList<LinkedHashMap<Integer, Object>> mapList){
        LinkedHashMap<Integer, Object> mergeMap = new LinkedHashMap<>();
        for (LinkedHashMap<Integer,Object> singleMap:mapList) {
            mergeMap.putAll(singleMap);
        }
       return mergeMap;
    }

    public static <T, V> LinkedHashMap<T, V> mergeHashmaps(ArrayList<LinkedHashMap<T, V>> mapList) {
        LinkedHashMap<T, V> mergeMap = new LinkedHashMap<>();
        for (LinkedHashMap<T,V> singleMap:mapList) {
            mergeMap.putAll(singleMap);
        }
        return mergeMap;
    }
    public static LinkedHashMap mergeHashmu(ArrayList<LinkedHashMap> mapList) {
        LinkedHashMap mergeMap = new LinkedHashMap<>();
        for (LinkedHashMap singleMap:mapList) {
            mergeMap.putAll(singleMap);
        }
        return mergeMap;
    }

    public String checkPositionIndexType(int positionIndex, LinkedHashMap<Integer, String> lotteryPositionTypes) {
        if (lotteryPositionTypes.get(positionIndex).equals("number"))
            return "number";
        else if (lotteryPositionTypes.get(positionIndex).equals("letter"))
            return "letter";
        return null;
    }


}
