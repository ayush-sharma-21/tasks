package com.lp.creditscore.service;

import org.mapstruct.Mapper;

import com.lp.creditscore.dto.FicoDetailsDto;
import com.lp.creditscore.dto.FicoScoreDto;
import com.lp.creditscore.model.FicoDetails;

@Mapper(componentModel = "spring")
public interface MapStructMapper {
	
     FicoDetails ficoDetailsDtoToFicoDetails(FicoDetailsDto ficoDetailsDto);
     FicoDetailsDto ficoDetailsToFicoDetailsDto(FicoDetails ficoDetails);
     FicoScoreDto ficoDetailsToFicoScoreDto(FicoDetails details);
    
}
