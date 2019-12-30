package com.bingo.lottoservice.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Lottery {
    String name;
    int id;
    ArrayList<LinkedHashMap<Integer,String>> positions;

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

    public ArrayList<LinkedHashMap<Integer, String>> getPositions() {
        return positions;
    }

    public void setPositions(ArrayList<LinkedHashMap<Integer, String>> positions) {
        this.positions = positions;
    }
}
