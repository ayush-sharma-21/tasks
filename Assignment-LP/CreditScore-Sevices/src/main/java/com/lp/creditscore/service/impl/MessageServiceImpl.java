package com.lp.creditscore.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lp.creditscore.model.FicoDetails;
import com.lp.creditscore.model.Message;
import com.lp.creditscore.repository.FicoDetailsRepository;
import com.lp.creditscore.repository.MessageRepository;
import com.lp.creditscore.service.MessageService;

@Component
public class MessageServiceImpl implements MessageService{

	@Autowired
	private FicoDetailsRepository ficoDetailsRepository;
	@Autowired
	private MessageRepository messageRepository;
	private List<Message> msgList;
	
	@Override
	public List<Message> getMessage() {
		return msgList;
	}
	@PostConstruct
	public List<Message> getMsgFromDB() {
		return msgList = messageRepository.findAll().collectList().block();
	}
	
	@PostConstruct
	public List<Long> getFicoScoreListDB() {
		List<FicoDetails> ficoDetailsList = ficoDetailsRepository.findAll().collectList().block();
		List<Long> ficoScoreList = ficoDetailsList.stream().map(f -> f.getFicoScore()).collect(Collectors.toList());
		List<Long> maxFscore = new ArrayList<Long>();
		List<Long> minFscore = new ArrayList<Long>();
		for (int i = 0; i < ficoScoreList.size(); i++) {
			if(ficoScoreList.get(i) <= 500) {
				minFscore.add(ficoScoreList.get(i));
			}
			else if(ficoScoreList.get(i) > 500){
				maxFscore.add(ficoScoreList.get(i));
			}
		}
		HashMap<String, List<Long>> maxMin = new HashMap<String, List<Long>>();
		maxMin.put("Fico Score List greater than 500", minFscore);
		maxMin.put("Fico Score List greater than 500", maxFscore);
		return ficoScoreList;
	}
	
}
