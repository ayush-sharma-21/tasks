package com.lp.creditscore.repository;

import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;

import com.lp.creditscore.model.Message;

@Repository
public interface MessageRepository extends ReactiveSortingRepository<Message, Long>{

}
