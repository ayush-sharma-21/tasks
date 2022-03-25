package com.lp.creditscore.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.lp.creditscore.dto.FicoDetailsDto;
import com.lp.creditscore.dto.MessageDto;
import com.lp.creditscore.dto.ScoreFactorDto;
import com.lp.creditscore.model.FicoDetails;
import com.lp.creditscore.model.Message;
import com.lp.creditscore.model.ScoreFactor;
import com.lp.creditscore.repository.FicoDetailsRepository;
import com.lp.creditscore.repository.ScoreFactorRepository;
import com.lp.creditscore.service.FicoDetailsService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class FicoDetailsServiceImpl implements FicoDetailsService {

	@Autowired
	private FicoDetailsRepository ficoDetailsRepository;
	private final MessageServiceImpl messageServiceImpl;
	private final ScoreFactorRepository scoreFactorRepository;

	@Override
	public Mono<FicoDetailsDto> getFicoWithMessage(Long id) {
		List<Message> msgList = messageServiceImpl.getMessage();
		return ficoDetailsRepository.findById(id)
//				.map(structMapper::ficoDetailsToFicoDetailsDto)
//				.switchIfEmpty( Mono.defer( () -> logAndReturn("", "Ex"))
				.map((ficoDetailsDto) -> {
					return FicoDetailsDto.builder().cra(ficoDetailsDto.getCra())
							.ficoScore(ficoDetailsDto.getFicoScore()).scoreVersion(ficoDetailsDto.getScoreVersion())
							.creditPullDate(ficoDetailsDto.getCreditPullDate())
							.congratulatoryMessage(
									getMessageDto(ficoDetailsDto.getFicoScore(), msgList).getCongratulatoryMessage())
							.id(ficoDetailsDto.getId()).build();
				});
	}
	private MessageDto getMessageDto(Long fScore, List<Message> msgList) {
		return msgList.stream().filter(msgRule -> msgRule.getMin() <= fScore && msgRule.getMax() >= fScore).findFirst()
				.map(msgRule -> MessageDto.builder().congratulatoryMessage(msgRule.getMessage()).build())
				.orElse(MessageDto.builder().congratulatoryMessage(null).build());
	}
		
	public Mono<Map<Long, List<FicoDetails>>> getFicoScore() {
		return ficoDetailsRepository.findAll()
				.collect(Collectors.groupingBy(f -> f.getFicoScore()));
	}
	public Mono<Map<Object, List<Long>>> groupBy() {
		return ficoDetailsRepository.findAll()
				.collect(Collectors.groupingBy(f -> f.getFicoScore(), 
						Collectors.mapping(FicoDetails::getFicoScore, Collectors.toList())));
	}	
	
	public Flux<ScoreFactor> findByFicoId(Long ficoId) {
		return scoreFactorRepository.getFicoId(ficoId);
	}
	
	public Flux<List<ScoreFactor>> findByFicoI() {
		return scoreFactorRepository.findAll().collectList().flatMapMany(Flux::just).log();
	}
	
	public Mono<ScoreFactorDto> getFicoWithScore(Long id) {
		return ficoDetailsRepository.getFicoWithScore(id)
				.map( (ficoDetails) -> {
					return ScoreFactorDto.builder()
							.id(ficoDetails.getId())
							.ficoScore(ficoDetails.getFicoScore())
							.creditPullDate(ficoDetails.getCreditPullDate())
							.scoreVersion(ficoDetails.getScoreVersion())
							.cra(ficoDetails.getCra())
							.scoreFactor(getScoreBasedOnFico(id))
							.build();
				}).log();
	}
	
	private List<ScoreFactor> getScoreBasedOnFico( Long id) {
		return scoreFactorRepository.getScoreBasedOnFico(id);
	}
	
	
	public Mono<List<ScoreFactor>> getScore(Long fid) {
		return scoreFactorRepository.getFicoId(fid).collectList().flatMap(Mono::just).log();
	}
	
	
	

	
//	private Function<List<ScoreFactor>, ScoreFactorDto> getListFicoDetailsFunction() {
//        return scoreFactorDto -> ScoreFactorDto.builder().scoreFactor(scoreFactorDto).build();
//    }
	
	
//	private Function<List<ScoreFactorDto>, FicoDetails> getListFicoDetailsFunction(CreditReportData creditReportData) {
//        return scoreFactorDtos -> FicoDetails.builder().ficoScore(creditReportData.getValue()).scoreVersion(scoreVersion).cra(creditReportData.getCreditReportType()).creditPullDate(creditReportData.getCreatedDate()).scoreFactors(scoreFactorDtos).build();
//    }
	
	
//	public Mono<ScoreFactorDto> getData(Long id) {
//		Mono<FicoDetails> fd = ficoDetailsRepository.findById(id);
//		Mono<ScoreFactor> sf = scoreFactorRepository.getFicoId(id);
//		return fd.map((scoreFactorDto) -> {
//			return ScoreFactorDto.builder()
//					.id(scoreFactorDto.getId())
//					.ficoScore(scoreFactorDto.getFicoScore())
//					.creditPullDate(scoreFactorDto.getCreditPullDate())
//					.scoreVersion(scoreFactorDto.getScoreVersion())
//					.cra(scoreFactorDto.getCra())
//					.scoreFactor(sf)
//					.build();
//		});
//	}
	
	
//public Mono<Map<Object, List<List<Long>>>> test3(){
//		Flux<FicoDetails> details = ficoDetailsRepository.findAll();
//		Flux<FicoDetails> MaxFico = details.filter(f -> f.getFicoScore() > 500);
//		Mono<List<Long>> mapMax = MaxFico.map(fMax -> fMax.getFicoScore())
//				.collect(Collectors.toList());
//		Flux<FicoDetails> MinFico = details.filter(f -> f.getFicoScore() <= 500);
//		Mono<List<Long>> mapMin = MinFico.map(fMin -> fMin.getFicoScore())
//				.collect(Collectors.toList());
//		Flux<List<Long>> combine = mapMin.concatWith(mapMax);
//			
////		HashMap<String,  Mono<List<Long>>> hashMap = new HashMap<String,  Mono<List<Long>>>();
////		
////		hashMap.put("Min", mapMax);
////		hashMap.put("Max", mapMin);
////		
////		System.out.println(hashMap);
//		
////		return hashMap.values()
////				.stream()
////				.map(Collection::stream)
////				.collect(Collectors.toList());;
////		
////		return hashMap.values().stream().collect(Collectors.groupingBy(Fico -> Fico.));
////		
//		
////		return combine.collect(Collectors.groupingBy(fico -> fico.toArray()));
//		return combine.collect(Collectors.groupingBy(fico -> fico));
////		return combine.collect(Collectors.groupingBy(fico -> fico.stream()));
////		return hashmap.collect(Collectors.groupingBy(fico -> fico));
//
//
//	}
	
//public Flux<HashMap<String, Mono<List<Long>>>> test(){
//	public HashMap<String, Mono<List<FicoDetails>>> test(){
		
//		Flux<FicoDetails> details = 
//		ficoDetailsRepository.findAll().subscribe(details -> System.out.println(details.getFicoScore()));		
//		Mono<List<FicoDetails>> MaxFico = details.filter(f -> f.getFicoScore() > 500).collectList();
//		Mono<List<Long>> mapMax = MaxFico.map(fMax -> fMax.getFicoScore())
//				.collect(Collectors.toList()).log();
//		Mono<List<FicoDetails>> MinFico = details.filter(f -> f.getFicoScore() <= 500).collectList();
//		Mono<List<Long>> mapMin = MinFico.map(fMin -> fMin.getFicoScore())
//				.collect(Collectors.toList()).log();
		
//		Flux<List<Long>> combine = mapMin.concatWith(mapMax);
//	    try {
//			Thread.sleep(10000);
//			System.out.println("Out of sleep");
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		HashMap<String, Mono<List<FicoDetails>>> hashMap = new HashMap<String,  Mono<List<FicoDetails>>>();
//		hashMap.put("Min", MaxFico);
//		hashMap.put("Max", MinFico);
//		System.out.println(hashMap);
//		return hashMap;
		
//	}
	

////	-->  working
//	public Flux<List<Long>> test() {
//		Flux<FicoDetails> details = ficoDetailsRepository.findAll();
//		Flux<FicoDetails> MaxFico = details.filter(f -> f.getFicoScore() > 500);
//		Mono<List<Long>> mapMax = MaxFico.map(fMax -> fMax.getFicoScore()).collect(Collectors.toList());
//		Flux<FicoDetails> MinFico = details.filter(f -> f.getFicoScore() <= 500);
//		Mono<List<Long>> mapMin = MinFico.map(fMin -> fMin.getFicoScore()).collect(Collectors.toList());
//		Flux<List<Long>> combine = mapMin.concatWith(mapMax);
//		return combine;
//	}

//	public Mono<List<Long>> findMaxFico() {
//		Flux<FicoDetails> details = ficoDetailsRepository.findAll().filter(f -> f.getFicoScore() > 500);
//		Mono<List<Long>> mapMax = details.map(fMax -> fMax.getFicoScore()).collect(Collectors.toList());
//		return mapMax;
//	}
//	public Mono<List<Long>> findMinFico() {
//		Flux<FicoDetails> details = ficoDetailsRepository.findAll().filter(f -> f.getFicoScore() <= 500);
//		Mono<List<Long>> mapMin = details.map(fMin -> fMin.getFicoScore()).collect(Collectors.toList());	
//		return mapMin;
//	}
	
//	public Mono<List<Long>> findMaxFico() {
//		return ficoDetailsRepository.findAll().filter(f -> f.getFicoScore() > 500).log()
//				.map(fMax -> fMax.getFicoScore()).collect(Collectors.toList()).log();
//	}
//	public Mono<List<Long>> findMinFico() {
//		return ficoDetailsRepository.findAll().filter(f -> f.getFicoScore() <= 500)
//				.map(fMin -> fMin.getFicoScore()).collect(Collectors.toList()).log();		
//	}
//	public Mono<List<FicoScoreDto>> test2() {
//		Flux<FicoDetails> details = ficoDetailsRepository.findAll();
//		return details.map((ficoScoreDto) -> {
//			return FicoScoreDto.builder()
//					.maxFicoScore(findMaxFico())
//					.minFicoScore(findMinFico())
//					.build();
//		}).collect(Collectors.toList());	
//	}
	
////	--> Using FicoScoreDto
//	public Mono<List<FicoScoreDto>> getMaxFicoScoreList() {
//		return ficoDetailsRepository.findAll()
//				.filter(fdMax -> fdMax.getFicoScore() > 500)
//				.map(fdMax -> {
//			return FicoScoreDto.builder()
//					.maxFscore(fdMax.getFicoScore())
//					.build();
//		}).collect(Collectors.toList());
//	}
//	public Mono<List<FicoScoreDto>> getMinFicoScoreList() {
//		return ficoDetailsRepository.findAll()
//				.filter(fdMin -> fdMin.getFicoScore() <= 500)
//				.map(fdMin -> {
//			return FicoScoreDto.builder()
//					.minFscore(fdMin.getFicoScore())
//					.build();
//		}).collect(Collectors.toList());
//	}	
//	public Flux<List<FicoScoreDto>> combine() {
//		return getMinFicoScoreList().concatWith(getMaxFicoScoreList());
//	}

}
