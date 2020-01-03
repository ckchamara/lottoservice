package com.bingo.lottoservice.services;

import com.bingo.lottoservice.model.Configuration;
import com.bingo.lottoservice.model.Lottery;
import com.bingo.lottoservice.model.Result;
import com.bingo.lottoservice.model.Rule;
import com.bingo.lottoservice.utils.LoadYAML;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class CombinationCheck {

    public CombinationCheck() throws IOException {
    }

    Configuration configuration = LoadYAML.load(Configuration.class,"test.yml");
    Result result = LoadYAML.load(Result.class,"result.yml");
    Lottery lottery = LoadYAML.load(Lottery.class,"lottery.yml");

    public void print() {
        System.out.println(configuration.getId());
        System.out.println(result.getName());
        System.out.println(lottery.getName());
    }

    public void checkReward(){
       ArrayList<LinkedHashMap<Integer,Object>> resultpositions = result.getPositions();
        for (LinkedHashMap<Integer,Object> position: resultpositions) {
            System.out.println(position.values()+" "+" "+position.keySet());
            int position1 = 5;
            if (compareLottreryResultMatch(position.get(position1),position1)){
                System.out.println("matched successfully");
            }
            else System.out.println("not matched");
        }
    }

    public boolean compareLottreryResultMatch(Object resultValue,int resultPosition){
        ArrayList<LinkedHashMap<Integer,Object>> lotteryPositions = lottery.getPositions();
        for (LinkedHashMap<Integer,Object> lotteryPosition: lotteryPositions) {
            if (lotteryPosition.get(resultPosition) == resultValue){
                return true;
            }
        }
        return false;
    }

}
