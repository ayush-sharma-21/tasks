package com.lp.creditscore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lp.creditscore.dto.FicoDetailsDto;
import com.lp.creditscore.model.FicoDetails;
import com.lp.creditscore.service.impl.FicoDetailsServiceImpl;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/creditScore")
public class CreditScoreController {
	
	@Autowired
	private FicoDetailsServiceImpl ficoDetailsServiceImpl;
	
	@GetMapping
	public Flux<FicoDetails> findAll() {
		return ficoDetailsServiceImpl.findAll();
	}
	
	@GetMapping("/{id}")
	public Mono<FicoDetailsDto> getFicoWithMessage(@PathVariable Long id) {
		return ficoDetailsServiceImpl.getFicoWithMessage(id);
	}
	
//	@GetMapping("/{id}")
//	public Mono<FicoDetails> getFicoWithMessage(@PathVariable Long id) {
//		return ficoDetailsServiceImpl.getFicoWithMessage(id);
//	}
	
}
