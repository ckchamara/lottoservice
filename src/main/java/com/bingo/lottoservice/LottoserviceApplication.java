package com.bingo.lottoservice;

import com.bingo.lottoservice.services.CombinationCheck;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.URISyntaxException;

@SpringBootApplication
public class LottoserviceApplication {

	public static void main(String[] args) throws IOException, URISyntaxException {
		SpringApplication.run(LottoserviceApplication.class, args);

		CombinationCheck combinationCheck = new CombinationCheck();
		try {
//			combinationCheck.checkReward();
			combinationCheck.print();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
