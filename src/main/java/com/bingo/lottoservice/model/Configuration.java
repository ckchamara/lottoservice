package com.bingo.lottoservice.model;

import java.util.List;
import java.util.Map;

public class Configuration {
    private String name;
    private int id;
    private List<SpecialChatactor> specialCharactors;
    private Map<Integer,String> ticketPositions;
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

    public Map<Integer, String> getTicketPositions() {
        return ticketPositions;
    }

    public void setTicketPositions(Map<Integer, String> ticketPositions) {
        this.ticketPositions = ticketPositions;
    }

    public List<Rule> getRules() {
        return rules;
    }

    public void setRules(List<Rule> rules) {
        this.rules = rules;
    }
}
