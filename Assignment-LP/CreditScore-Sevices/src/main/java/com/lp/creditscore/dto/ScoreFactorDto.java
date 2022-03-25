package com.lp.creditscore.dto;

import java.util.List;
import java.util.function.Function;

import com.lp.creditscore.model.ScoreFactor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import reactor.core.publisher.Flux;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScoreFactorDto {
	
	private Long id;
	private Long ficoScore;
    private String creditPullDate;
    private String scoreVersion;
    private String cra;
    private List<ScoreFactor> scoreFactor;
  
}
