package org.ucad.web;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import org.ucad.dao.MessageRepository;
import org.ucad.entities.Message;

@SessionScope
@Component(value = "messageController")
public class MessageController {

	@Autowired
	private MessageRepository messageRepository;

	private Message message;
	private List<Message> listeMessages;

	@PostConstruct
	private void init() {
		// TODO Auto-generated method stub

	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public List<Message> getListeMessages() {
		return messageRepository.findAll();
	}

	public void setListeMessages(List<Message> listeMessages) {
		this.listeMessages = listeMessages;
	}
}
