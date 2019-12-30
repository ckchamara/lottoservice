package com.bingo.lottoservice.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Result {
    String name;
    int id;
    int drawNo;
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

    public int getDrawNo() {
        return drawNo;
    }

    public void setDrawNo(int drawNo) {
        this.drawNo = drawNo;
    }

    public ArrayList<LinkedHashMap<Integer, String>> getPositions() {
        return positions;
    }

    public void setPositions(ArrayList<LinkedHashMap<Integer, String>> positions) {
        this.positions = positions;
    }
}
