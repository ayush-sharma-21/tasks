package com.lp.creditscore.service;

import com.lp.creditscore.dto.FicoDetailsDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FicoDetailsService {

	public Mono<FicoDetailsDto> getFicoWithMessage(Long id);
//	public Flux<Long> getFicoScore();
}
