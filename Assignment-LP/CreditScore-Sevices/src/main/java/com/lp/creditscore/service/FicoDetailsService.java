package com.lp.creditscore.service;

import com.lp.creditscore.dto.FicoDetailsDto;

import reactor.core.publisher.Mono;

public interface FicoDetailsService {

	Mono<FicoDetailsDto> getResult(Long id);
}
