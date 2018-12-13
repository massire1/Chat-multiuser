package org.ucad.web;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.ucad.dao.MessageRepository;
import org.ucad.entities.ChatMessage;
import org.ucad.entities.Message;

@Component
public class WebSocketEventListener {

	private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);

	@Autowired
	private SimpMessageSendingOperations messagingTemplate;

	@Autowired
	private MessageRepository messageRepository;

	@EventListener
	public void handleWebSocketConnectListener(SessionConnectedEvent event) {
		logger.info("Received a new web socket connection");
	}

	// @PostConstruct
	private void init() {
		// TODO Auto-generated method stub

		// StompHeaderAccessor headerAccessor =
		// StompHeaderAccessor.wrap(event.getMessage());
		// String username = (String)
		// headerAccessor.getSessionAttributes().get("username");

		// for (Message message : messageRepository.findAll()) {
		// ChatMessage chatMessage = new ChatMessage();
		// chatMessage.setType(ChatMessage.MessageType.CHAT);
		// chatMessage.setSender("from database");
		// chatMessage.setContent(message.getMessage());
		// messagingTemplate.convertAndSend("/topic/public", chatMessage);
		// }
	}

	@EventListener
	public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
		StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

		String username = (String) headerAccessor.getSessionAttributes().get("username");
		if (username != null) {
			logger.info("User Disconnected : " + username);

			ChatMessage chatMessage = new ChatMessage();
			chatMessage.setType(ChatMessage.MessageType.LEAVE);
			chatMessage.setSender(username);

			messagingTemplate.convertAndSend("/topic/public", chatMessage);

		}
	}
}
