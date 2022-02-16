package com.lp.creditscore.repository;

import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;

import com.lp.creditscore.model.ScoreFactor;

@Repository
public interface ScoreFactorRepository extends ReactiveSortingRepository<ScoreFactor, Long>{

}
