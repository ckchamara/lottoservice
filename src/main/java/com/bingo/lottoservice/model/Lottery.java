package com.bingo.lottoservice.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Lottery {
    String name;
    int id;
    ArrayList<LinkedHashMap<Integer,Object>> positions;

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

    public ArrayList<LinkedHashMap<Integer, Object>> getPositions() {
        return positions;
    }

    public void setPositions(ArrayList<LinkedHashMap<Integer, Object>> positions) {
        this.positions = positions;
    }
}
