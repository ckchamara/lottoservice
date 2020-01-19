package com.bingo.lottoservice.model;

import java.util.List;

public class Rule {

    private String rule;
    private List<Integer> positions;
    private List<Integer> nonFixedPositions;
    private int level;
    private Boolean positionalValue;
    private String nonFixedType;
    private int matchingCount;
    private double prize;

    public List<Integer> getNonFixedPositions() {
        return nonFixedPositions;
    }

    public void setNonFixedPositions(List<Integer> nonFixedPositions) {
        this.nonFixedPositions = nonFixedPositions;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public void setPositions(List<Integer> positions) {
        this.positions = positions;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setPositionalValue(Boolean positionalValue) {
        this.positionalValue = positionalValue;
    }

    public void setNonFixedType(String nonFixedType) {
        this.nonFixedType = nonFixedType;
    }

    public void setMatchingCount(int matchingCount) {
        this.matchingCount = matchingCount;
    }

    public void setPrize(double prize) {
        this.prize = prize;
    }

    public String getRule() {
        return rule;
    }

    public List<Integer> getPositions() {
        return positions;
    }

    public int getLevel() {
        return level;
    }

    public Boolean getPositionalValue() {
        return positionalValue;
    }

    public String getNonFixedType() {
        return nonFixedType;
    }

    public int getMatchingCount() {
        return matchingCount;
    }

    public double getPrize() {
        return prize;
    }
}
