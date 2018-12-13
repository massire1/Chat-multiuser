package org.ucad.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Message implements Serializable {

	@Id
	@GeneratedValue
	private Long id;
	private String message;
	private Date dateEnvoie;
	private Date dateReception;
	private Date dateLecture;
	private String status;

	@ManyToOne
	@JoinColumn(name = "code_emetteur")
	private Utilisateur emetteur;

	@OneToOne
	private Utilisateur destinataire;

	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Message(String message, Date dateEnvoie, Date dateReception, Date dateLecture, String status,
			Utilisateur emetteur, Utilisateur destinataire) {
		super();		
		this.message = message;
		this.dateEnvoie = dateEnvoie;
		this.dateReception = dateReception;
		this.dateLecture = dateLecture;
		this.status = status;
		this.emetteur = emetteur;
		this.destinataire = destinataire;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDateEnvoie() {
		return dateEnvoie;
	}

	public void setDateEnvoie(Date dateEnvoie) {
		this.dateEnvoie = dateEnvoie;
	}

	public Date getDateReception() {
		return dateReception;
	}

	public void setDateReception(Date dateReception) {
		this.dateReception = dateReception;
	}

	public Date getDateLecture() {
		return dateLecture;
	}

	public void setDateLecture(Date dateLecture) {
		this.dateLecture = dateLecture;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Utilisateur getEmetteur() {
		return emetteur;
	}

	public void setEmetteur(Utilisateur emetteur) {
		this.emetteur = emetteur;
	}

	public Utilisateur getDestinataire() {
		return destinataire;
	}

	public void setDestinataire(Utilisateur destinataire) {
		this.destinataire = destinataire;
	}

}
