package com.bingo.lottoservice.model;

import java.util.List;

public class Rule {
    private String rule;
    private List<Integer> positions;
    private Boolean positionalValue;
    private double prize;

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public List<Integer> getPositions() {
        return positions;
    }

    public void setPositions(List<Integer> positions) {
        this.positions = positions;
    }

    public Boolean getPositionalValue() {
        return positionalValue;
    }

    public void setPositionalValue(Boolean positionalValue) {
        this.positionalValue = positionalValue;
    }

    public double getPrize() {
        return prize;
    }

    public void setPrize(double prize) {
        this.prize = prize;
    }
}
