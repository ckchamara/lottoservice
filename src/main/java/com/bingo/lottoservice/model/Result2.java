package com.bingo.lottoservice.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Result2 {
    String name;
    int id;
    String date;
    int drawNo;
    ArrayList<LinkedHashMap<String,Object>> positions;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

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

    public ArrayList<LinkedHashMap<String, Object>> getPositions() {
        return positions;
    }

    public void setPositions(ArrayList<LinkedHashMap<String, Object>> positions) {
        this.positions = positions;
    }
}