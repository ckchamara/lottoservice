package com.bingo.lottoservice.model;

import java.util.List;

public class Rule {
    private String rule;
    private List<Integer> positions;
    private int level;
    private Boolean positionalValue;
    private String nonFixedType;
    private int matchingCount;
    private double prize;

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
