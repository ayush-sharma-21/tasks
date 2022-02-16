package com.lp.creditscore.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScoreFactorDto {
	
	private Long id;
    private String tuCode;
    private String exCode;
    private String reasonStatement;
    private String keepInMind;
    private String fullDescription;
    private Long ficoId;

}
