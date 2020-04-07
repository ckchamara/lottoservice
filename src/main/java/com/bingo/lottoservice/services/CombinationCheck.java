package com.bingo.lottoservice.services;

import com.bingo.lottoservice.model.*;
import com.bingo.lottoservice.utils.LoadYAML;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.StringWriter;
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
        lottery = Lottery;
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

    public static <T, V> LinkedHashMap<T, V> mergeHashmaps(ArrayList<LinkedHashMap<T, V>> mapList) {
        LinkedHashMap<T, V> mergeMap = new LinkedHashMap<>();
        for (LinkedHashMap<T, V> singleMap : mapList) {
            mergeMap.putAll(singleMap);
        }
        return mergeMap;
    }

    public StringWriter checkReward() throws Exception {
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
                        int originalLotteryNumberCount = lotteryPositions.size();
                        lotteryPositions = lotteryPositions.entrySet()
                                .stream()
                                .filter(entry -> existing.add(entry.getValue()))
                                .collect((Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (x, y) -> y, LinkedHashMap::new)));
                        int identicalLotteryNumberCount = lotteryPositions.size();
                        boolean isDuplicateValuesExsist = originalLotteryNumberCount != identicalLotteryNumberCount;
                        if (isDuplicateValuesExsist) {
                            for (int numberPosition = 1; numberPosition <= originalLotteryNumberCount; numberPosition++) {
                                if (!lotteryPositions.keySet().contains(numberPosition)) {
                                    int duplicateValuePosition = numberPosition;
                                    lotteryPositions.put(duplicateValuePosition, -1);
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

        RewardResponce rewardResponce = new RewardResponce();
        rewardResponce.setName(configuration.getName());
        rewardResponce.setReward(rewardPrize);
        rewardResponce.setRuleName(ruleName);
        rewardResponce.setMatchingPositions(matchingPositions);
        rewardResponce.setTimestamp(System.currentTimeMillis());
        rewardResponce.setDrawNo(result.getDrawNo());

        Yaml yaml = new Yaml();
        StringWriter writer = new StringWriter();
        yaml.dump(rewardResponce, writer);
//        yaml.dumpAs(rewardResponce, Tag.MAP, null);  //without tag

        System.out.println(writer.toString());
        return writer;

    }

    private String checkPositionIndexType(int positionIndex, LinkedHashMap<Integer, String> lotteryPositionTypes) {
        if (lotteryPositionTypes.get(positionIndex).equals("number"))
            return "number";
        else if (lotteryPositionTypes.get(positionIndex).equals("letter"))
            return "letter";
        return null;
    }

}
