package com.bingo.lottoservice.model;

public class DeleteElasticDoc {
    private String lotteryName;
    private String drawNo;

    public String getLotteryName() {
        return lotteryName;
    }

    public void setLotteryName(String lotteryName) {
        this.lotteryName = lotteryName;
    }

    public String getDrawNo() {
        return drawNo;
    }

    public void setDrawNo(String drawNo) {
        this.drawNo = drawNo;
    }
}
