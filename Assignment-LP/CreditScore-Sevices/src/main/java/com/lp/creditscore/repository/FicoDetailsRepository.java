package com.lp.creditscore.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;

import com.lp.creditscore.dto.FicoDetailsDto;
import com.lp.creditscore.model.FicoDetails;

import reactor.core.publisher.Mono;

@Repository
public interface FicoDetailsRepository extends ReactiveSortingRepository<FicoDetails, Long>{

	@Query("select f.id, "
			+ "f.ficoScore, "
			+ "f.creditPullDate, "
			+ "f.scoreVersion, "
			+ "f.cra, "
			+ "m.message "
			+ "from fico_details as f, message as m "
			+ "where f.id = :id")
	Mono<FicoDetailsDto> getResult(@Param("id") Long id); 
	
}
