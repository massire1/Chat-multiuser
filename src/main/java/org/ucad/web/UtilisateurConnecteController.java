package org.ucad.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import org.ucad.dao.MessageRepository;
import org.ucad.dao.UtilisateurRepository;
import org.ucad.entities.Message;
import org.ucad.entities.Utilisateur;

@SessionScope
@Component(value = "utilisateurConnecte")
public class UtilisateurConnecteController {

	@Autowired
	private UtilisateurRepository utilisateurRepository;
	@Autowired
	private MessageRepository messageRepository;

	private String username;
	private String roles;
	private List<Utilisateur> listeUtilisateurs;
	private Utilisateur destinataire2;
	private Long destinataireCode;
	private Map<String, String> listeDestinatairesMap = new HashMap<String, String>();
	private String messageAEnvoyer;
	private Utilisateur user;

	@PostConstruct
	private void init() {
		// TODO Auto-generated method stub

		this.setUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		this.setUser(utilisateurRepository.findByUsername(username));

		// //this.setListeUtilisateurs(utilisateurRepository.findAll());
		//
		// //
		// listeDestinatairesMap = new HashMap<String, String>();
		// for (Utilisateur utilisateur : this.getListeUtilisateurs()) {
		// listeDestinatairesMap.put(utilisateur.getPrenom() + " " +
		// utilisateur.getNom(), utilisateur.getCode() + "");
		// }
	}

	public String envoyerMessage() {

		Utilisateur destinataire = utilisateurRepository.findByCode(destinataireCode);
		Utilisateur emetteur = utilisateurRepository.findByUsername(username);

		if (destinataire == null || emetteur == null) {

			return "?error=true";
		}

		Message message = new Message(messageAEnvoyer, new Date(), null, null, null, emetteur, destinataire);

		messageRepository.save(message);

		return "&messageSent=true";
	}

	public String getMessageAEnvoyer() {
		return messageAEnvoyer;
	}

	public void setMessageAEnvoyer(String messageAEnvoyer) {
		this.messageAEnvoyer = messageAEnvoyer;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public Long getDestinataireCode() {
		return destinataireCode;
	}

	public void setDestinataireCode(Long destinataireCode) {
		this.destinataireCode = destinataireCode;
	}

	public List<Utilisateur> getListeUtilisateurs() {
		return this.listeUtilisateurs;
	}

	public void setListeUtilisateurs(List<Utilisateur> listeUtilisateurs) {
		this.listeUtilisateurs = listeUtilisateurs;
	}

	public Map<String, String> getListeDestinatairesMap() {
		return listeDestinatairesMap;
	}

	public void setListeDestinatairesMap(Map<String, String> listeDestinatairesMap) {
		this.listeDestinatairesMap = listeDestinatairesMap;
	}

	public Utilisateur getUser() {
		return user;
	}

	public void setUser(Utilisateur user) {
		this.user = user;
	}

}
