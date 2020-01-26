package com.bingo.lottoservice.model;

import java.util.List;

public class RewardResponce {
    String name;
    double reward;
    String ruleName;
    List<Integer> matchingPositions;

    public List<Integer> getMatchingPositions() {
        return matchingPositions;
    }

    public void setMatchingPositions(List<Integer> matchingPositions) {
        this.matchingPositions = matchingPositions;
    }

    public double getReward() {
        return reward;
    }

    public void setReward(double reward) {
        this.reward = reward;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

}
