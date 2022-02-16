package com.lp.creditscore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lp.creditscore.dto.FicoDetailsDto;
import com.lp.creditscore.dto.MessageDto;
import com.lp.creditscore.dto.ScoreFactorDto;
import com.lp.creditscore.model.FicoDetails;
import com.lp.creditscore.model.Message;
import com.lp.creditscore.repository.FicoDetailsRepository;
import com.lp.creditscore.service.FicoDetailsService;
import com.lp.creditscore.service.MapStructMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class FicoDetailsServiceImpl implements FicoDetailsService {

	private final FicoDetailsRepository ficoDetailsRepository;
//	private final MapStructMapper structMapper;
	private final MessageServiceImpl messageServiceImpl;

	public Flux<FicoDetails> findAll() {
		return ficoDetailsRepository.findAll();
	}

	public Mono<FicoDetailsDto> getFicoWithMessage(Long id) {
		List<Message> msgList = this.messageServiceImpl.getMessage();
		return ficoDetailsRepository.findById(id)
//				.map(structMapper::ficoDetailsToFicoDetailsDto)
				// .switchIfEmpty( Mono.defer( () -> logAndReturn("", "Ex"))
				.map((ficoDetailsDto) -> {
					return FicoDetailsDto.builder()
							.cra(ficoDetailsDto.getCra())
							.ficoScore(ficoDetailsDto.getFicoScore())
							.scoreVersion(ficoDetailsDto.getScoreVersion())
							.creditPullDate(ficoDetailsDto.getCreditPullDate())
							.congratulatoryMessage(getMessageDto(ficoDetailsDto.getFicoScore(), msgList).getCongratulatoryMessage())
							.id(ficoDetailsDto.getId())
							.build();
				});

	}
	
	private MessageDto getMessageDto(Long fScore, List<Message> msgList) {
		return msgList.stream().filter(msgRule -> msgRule.getMin() <= fScore && msgRule.getMax() >= fScore).findFirst()
				.map(msgRule -> MessageDto.builder().congratulatoryMessage(msgRule.getMessage()).build())
				.orElse(MessageDto.builder().congratulatoryMessage(null).build());
	}
	
	
	
	
	
	
	
	

//	public Mono<FicoDetailsDto> getFicoWithMessage(Long id) {
//		List<Message> msgList = this.messageServiceImpl.getMessage();
//		return ficoDetailsRepository.findById(id).map(structMapper::ficoDetailsToFicoDetailsDto)
//				.flatMap(this.getMessageDto(id, msgList))
//						
//	}

//	Mono.defer( () -> logAndReturn("", "Ex"))
//	.map((ficoDetailsDto) -> {
//		return FicoDetailsDto.builder().cra(e.getReasonStatement()).build();
//	})

	@Override
	public Mono<FicoDetailsDto> getResult(Long id) {
		return null;
	}
	

//	private FicoDetailsDto getFicoDetailsDto(FicoDetails ficoDetails, List<Message> msgList) {
//		MessageDto msgDto = getMessageDto(ficoDetails.getFicoScore(), msgList);
//		return FicoDetailsDto.builder().id(ficoDetails.getId()).ficoScore(ficoDetails.getFicoScore())
//				.scoreVersion(ficoDetails.getScoreVersion()).creditPullDate(ficoDetails.getCreditPullDate())
//				.cra(ficoDetails.getCra()).congratulatoryMessage(msgDto.getCongratulatoryMessage()).build();
//	}

//	private Mono<ScoreFactorDto> logAndReturn(String scoreFactorCode, String cra) {
////        log.warn("Could not find Score Factor Descriptions for the {} code : {}", cra, scoreFactorCode);
//		System.out.println("Could not find Score Factor Descriptions for the {} code : {}");
//		return Mono.empty();
//	}

}
