package com.lp.creditscore.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FicoDetailsDto {

	private Long id;
	private Long ficoScore;
    private String creditPullDate;
    private String scoreVersion;
    private String cra;
    private String congratulatoryMessage;
	
}
