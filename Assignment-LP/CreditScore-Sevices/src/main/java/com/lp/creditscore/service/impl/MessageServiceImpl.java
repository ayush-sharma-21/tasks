package com.lp.creditscore.service.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lp.creditscore.model.Message;
import com.lp.creditscore.repository.MessageRepository;
import com.lp.creditscore.service.MessageService;

@Component
public class MessageServiceImpl implements MessageService{

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
//		System.out.println(msgList);
	}
	
//	@PostConstruct
//	public void findMsgFromDB() {
//		msgList = messageRepository.findAll().collectList().block();
//		System.out.println(msgList);
//	}
	
}
