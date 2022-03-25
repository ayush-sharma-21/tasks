package com.lp.creditscore.repository;

import java.util.List;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;

import com.lp.creditscore.model.ScoreFactor;

import reactor.core.publisher.Flux;

@Repository
public interface ScoreFactorRepository extends ReactiveSortingRepository<ScoreFactor, Long> {

	@Query("select * from score_factor_dto where ficoId = :ficoID")
	Flux<ScoreFactor> getFicoId(@Param("ficoID") Long ficoId);
	
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
			+ "JOIN score_factor_dto as s ON f.id = s.ficoId "
			+ "where f.id = :id")
	List<ScoreFactor> getScoreBasedOnFico(@Param("id") Long id);

}



//"SELECT" + 
//"f.id," + 
//"f.ficoScore," + 
//"f.creditPullDate," + 
//"f.scoreVersion," + 
//"f.cra," + 
//"s.ficoId," + 
//"s.id," + 
//"s.exCode," + 
//"s.tuCode," + 
//"s.KeepInMind," + 
//"s.reasonStatement," + 
//"s.fullDescription" + 
//"FROM fico_details as f" + 
//"JOIN score_factor_dto as s" + 
//"ON f.id = s.ficoId" +
//"WHERE f.id = :id"
