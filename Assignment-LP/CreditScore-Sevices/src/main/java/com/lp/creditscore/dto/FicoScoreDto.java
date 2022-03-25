package com.lp.creditscore.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import reactor.core.publisher.Mono;

@Data
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class FicoScoreDto {

	//combine
	private Long minFscore;
	private Long maxFscore;
	
	//test2
	private Mono<List<Long>> minFicoScore;
	private Mono<List<Long>> maxFicoScore;

//	private List<Long> minFicoScore;
//	private List<Long> maxFicoScore;

	
}

