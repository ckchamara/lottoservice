package com.bingo.lottoservice;

import com.bingo.lottoservice.services.CombinationCheck;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class LottoserviceApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(LottoserviceApplication.class, args);

		CombinationCheck combinationCheck = new CombinationCheck();
		combinationCheck.print();
		combinationCheck.checkReward();
	}
}
