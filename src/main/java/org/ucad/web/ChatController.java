package org.ucad.web;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.ucad.dao.MessageRepository;
import org.ucad.dao.UtilisateurRepository;
import org.ucad.entities.ChatMessage;
import org.ucad.entities.Message;
import org.ucad.entities.Utilisateur;

@Controller
public class ChatController {

	@Autowired
	private MessageRepository messageRepository;
	
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
    	
    	Utilisateur sender = utilisateurRepository.findByUsername(chatMessage.getContent());
    	
    	Message message = new Message(chatMessage.getContent(), new Date(), new Date(), new Date(), null, sender, null);
    	messageRepository.save(message);
    	
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage,
                               SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }

}
