package org.ucad.metier;

import java.util.List;

import org.ucad.entities.Message;
import org.ucad.entities.Utilisateur;

public interface IUtilisateurMetier {
	
	public Message lireMessage();
	public List<Message> listeDesMessages();
	public List<Message> listeMessagesUser(Utilisateur utilisateur);
	public List<Message> listeMessagesNonlus(Utilisateur utilisateur);		
	public void envoyerMessage(Message message);	
}
