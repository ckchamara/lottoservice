package com.bingo.lottoservice.services;

import com.bingo.lottoservice.model.Configuration;
import com.bingo.lottoservice.model.Lottery;
import com.bingo.lottoservice.model.Result;
import com.bingo.lottoservice.model.Rule;
import com.bingo.lottoservice.utils.LoadYAML;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.Collectors;

public class CombinationCheck {

    private Configuration configuration;
    private Result result;
    private Lottery lottery;

    public CombinationCheck() {

    }

    public void setConfig(Lottery Lottery) throws IOException, URISyntaxException {
        lottery = (Lottery) Lottery;
        configuration = LoadYAML.load(this.getClass(), Configuration.class, lottery.getName() + "/configuration.yml");
        result = LoadYAML.load(this.getClass(), Result.class, lottery.getName() + "/result.yml");
    }

    public void print() {
        System.out.println(configuration.getId());
        System.out.println(result.getName());
        System.out.println(lottery.getName());

        configuration.getRules().stream()
                .filter(rule -> rule.getNonFixedPositions() != null)
                .forEach(rule -> System.out.println(rule.getNonFixedPositions() + rule.getRule()));
    }

    private static <T, V> LinkedHashMap<T, V> mergeHashmaps(ArrayList<LinkedHashMap<T, V>> mapList) {
        LinkedHashMap<T, V> mergeMap = new LinkedHashMap<>();
        for (LinkedHashMap<T, V> singleMap : mapList) {
            mergeMap.putAll(singleMap);
        }
        return mergeMap;
    }

    public Map<String, Object> checkReward() throws Exception {
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
                }
            }

            //logic for non-fixed positions
            if (rule.getNonFixedPositions() != null && !noMatchingPositionalValues) {
                for (int nonFixPosition : rule.getNonFixedPositions()) {
                    System.out.println("Non-Fix " + nonFixPosition);
                    if (checkPositionIndexType(nonFixPosition, lotteryPositionTypes).equals("number")) {

                        //filter only number indices
                        List<Integer> filteredIndices = resultPositions.keySet()
                                .stream()
                                .filter(resultIndex -> checkPositionIndexType(resultIndex, lotteryPositionTypes).equals("number"))
                                .collect(Collectors.toList());

                        //Eliminate Duplicates and fill dplicate values with -1
                        Set<Object> existing = new HashSet<>();
                        int origilalLotteryNumbrCount = lotteryPositions.size();
                        lotteryPositions = lotteryPositions.entrySet()
                                .stream()
                                .filter(entry -> existing.add(entry.getValue()))
                                .collect((Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (x, y) -> y, LinkedHashMap::new)));
                        int identicalLotteryNumberCount = lotteryPositions.size();
                        boolean isDuplicateValuesExsist = origilalLotteryNumbrCount != identicalLotteryNumberCount;
                        if (isDuplicateValuesExsist) {
                            for (int numberPosition = 1; numberPosition <= origilalLotteryNumbrCount; numberPosition++) {
                                if (!lotteryPositions.keySet().contains(numberPosition)) {
                                    lotteryPositions.put(numberPosition, -1);
                                }
                            }
                        }

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

        return rewardAndValues;
    }

    private String checkPositionIndexType(int positionIndex, LinkedHashMap<Integer, String> lotteryPositionTypes) {
        if (lotteryPositionTypes.get(positionIndex).equals("number"))
            return "number";
        else if (lotteryPositionTypes.get(positionIndex).equals("letter"))
            return "letter";
        return null;
    }

}
