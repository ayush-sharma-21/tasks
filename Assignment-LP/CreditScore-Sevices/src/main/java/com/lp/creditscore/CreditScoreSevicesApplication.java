package com.lp.creditscore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import reactor.blockhound.BlockHound;

@SpringBootApplication
public class CreditScoreSevicesApplication {

	public static void main(String[] args) {
		BlockHound.install();
		SpringApplication.run(CreditScoreSevicesApplication.class, args);
	}
	
//	public static void main(String[] args) {
//		Flux.just("400", "400", "400", "500", "500", "500")
//		        .groupBy(s -> s.charAt(0))
//		        .concatMap(groupedFlux -> groupedFlux
//		                .startWith("Group " + groupedFlux.key()))
//		        .subscribe(System.out::println);
//
//	}

}
