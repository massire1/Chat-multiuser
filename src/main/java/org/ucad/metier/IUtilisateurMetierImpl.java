package org.ucad.metier;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ucad.dao.MessageRepository;
import org.ucad.dao.UtilisateurRepository;
import org.ucad.entities.Message;
import org.ucad.entities.Utilisateur;

@Service
@Transactional
public class IUtilisateurMetierImpl implements IUtilisateurMetier{

	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	@Autowired
	private MessageRepository messageRepository;
	
	@Override
	public List<Message> listeMessagesUser(Utilisateur utilisateur) {
		// TODO Auto-generated method stub
				
		return messageRepository.findAll();
	}

	@Override
	public List<Message> listeMessagesNonlus(Utilisateur utilisateur) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void envoyerMessage(Message message) {
		// TODO Auto-generated method stub
		
		messageRepository.save(message);
	}

	@Override
	public Message lireMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Message> listeDesMessages() {
		// TODO Auto-generated method stub
						
		return messageRepository.findAll();
	}

}
