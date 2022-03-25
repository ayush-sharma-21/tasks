package com.lp.creditscore.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;

import com.lp.creditscore.dto.ScoreFactorDto;
import com.lp.creditscore.model.FicoDetails;

import reactor.core.publisher.Mono;

@Repository
public interface FicoDetailsRepository extends ReactiveSortingRepository<FicoDetails, Long>{
	
	@Query("SELECT "
			+ "f.id, "
			+ "f.ficoScore, "
			+ "f.creditPullDate, "
			+ "f.scoreVersion, "
			+ "f.cra, "
			+ "s.ficoId, "
			+ "s.id, "
			+ "s.exCode, "
			+ "s.tuCode, "
			+ "s.KeepInMind, "
			+ "s.reasonStatement, "
			+ "s.fullDescription "
			+ "FROM fico_details as f "
			+ "JOIN score_factor_dto as s "
			+ "ON f.id = s.ficoId where f.id = :id")
	Mono<ScoreFactorDto> getFicoWithScore(@Param("id") Long id);
}
