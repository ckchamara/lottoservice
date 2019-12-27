package com.bingo.lottoservice.model;

import java.util.List;
import java.util.Map;

public class Configuration {
    private String ticketName;
    private int id;
    private List<SpecialChatactors> specialCharactors;
    private Map<Integer,String> ticketPositions;
    private List<Rule> rules;
}
