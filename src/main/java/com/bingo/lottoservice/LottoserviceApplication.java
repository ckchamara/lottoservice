package com.bingo.lottoservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;

@SpringBootApplication
public class LottoserviceApplication {

	public static void main(String[] args)  {
		SpringApplication.run(LottoserviceApplication.class, args);
//		CombinationCheck combinationCheck = new CombinationCheck();
		try {
//			combinationCheck.checkReward();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
