package com.lp.creditscore.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lp.creditscore.dto.FicoDetailsDto;
import com.lp.creditscore.dto.ScoreFactorDto;
import com.lp.creditscore.model.FicoDetails;
import com.lp.creditscore.model.ScoreFactor;
import com.lp.creditscore.service.impl.FicoDetailsServiceImpl;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/creditScore")
public class CreditScoreController {
	
	@Autowired
	private FicoDetailsServiceImpl ficoDetailsServiceImpl;
	
	@GetMapping("/{id}")
	public Mono<FicoDetailsDto> getFicoWithMessage(@PathVariable Long id) {
		return ficoDetailsServiceImpl.getFicoWithMessage(id);
	}
	
	@GetMapping("/getFicoScore")
	public Mono<Map<Long, List<FicoDetails>>> getFicoScore() {
		return ficoDetailsServiceImpl.getFicoScore();		
	}
	
	@GetMapping("/groupBy")
	public Mono<Map<Object, List<Long>>> groupBy() {
		return ficoDetailsServiceImpl.groupBy();
	}
	
	@GetMapping("/sf/{ficoId}")
	public Flux<ScoreFactor> findByficoId(@PathVariable Long ficoId) {
		return ficoDetailsServiceImpl.findByFicoId(ficoId);
	}
	
	@GetMapping("/getFicoWithScore/{id}")
	public Mono<ScoreFactorDto> getFicoWithScore(@PathVariable Long id) {
		return ficoDetailsServiceImpl.getFicoWithScore(id);
	}
	
	@GetMapping("/i/{id}")
	public Flux<List<ScoreFactor>> findByFicoI() {
		return ficoDetailsServiceImpl.findByFicoI();
	}
	
//	@GetMapping("sf/{ficoId}")
//	public Flux<ResponseEntity<ScoreFactor>> findByFicoId(@PathVariable Long ficoId) {
//		Flux<ScoreFactor> sf = ficoDetailsServiceImpl.findByFicoId(ficoId);
//		return sf.map(r -> ResponseEntity.ok(r))
//				.defaultIfEmpty(ResponseEntity.notFound().build());
//	}
	
//	@GetMapping("/ficoWithScore/{id}")
//	public Mono<ScoreFactorDto> getData(@PathVariable Long id) {
//		return ficoDetailsServiceImpl.getData(id);
//	}
	
//	@GetMapping("/test3")
//	public Mono<Map<Object, List<List<Long>>>> test3(){
//		return ficoDetailsServiceImpl.test3();
//	}
	
//	@GetMapping("/combine")
//	public Flux<List<FicoScoreDto>> combine() {	
//		return ficoDetailsServiceImpl.combine();
//	}
	
//	@GetMapping("/test")
//	public Flux<List<Long>> test(){
//		return ficoDetailsServiceImpl.test();
//	}
	
//	@GetMapping("/test2")
//	public Mono<List<FicoScoreDto>> test2() {
//		return ficoDetailsServiceImpl.test2();
//	}
	
//	@GetMapping("/min")
//	public Mono<List<Long>> findMaxFico() {
//		return ficoDetailsServiceImpl.findMinFico();
//	}
	
}
