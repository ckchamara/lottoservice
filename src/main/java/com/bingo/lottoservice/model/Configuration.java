package com.bingo.lottoservice.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class Configuration {
    private String name;
    private int id;
    private List<SpecialChatactor> specialCharactors;
    private ArrayList<LinkedHashMap<Integer,String>>positions;
    private List<Rule> rules;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<SpecialChatactor> getSpecialCharactors() {
        return specialCharactors;
    }

    public void setSpecialCharactors(List<SpecialChatactor> specialCharactors) {
        this.specialCharactors = specialCharactors;
    }

    public ArrayList<LinkedHashMap<Integer, String>> getPositions() {
        return positions;
    }

    public void setPositions(ArrayList<LinkedHashMap<Integer, String>> positions) {
        this.positions = positions;
    }

    public List<Rule> getRules() {
        return rules;
    }

    public void setRules(List<Rule> rules) {
        this.rules = rules;
    }
}
