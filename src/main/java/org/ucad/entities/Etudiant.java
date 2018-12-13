package org.ucad.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("ETU")
public class Etudiant extends Utilisateur {

	public Etudiant() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Etudiant(Long code, String prenom, String nom, String telephone, String faculte, String username,
			String password) {
		super(code, prenom, nom, telephone, faculte, username, password);
		// TODO Auto-generated constructor stub
	}

	public Etudiant(String prenom, String nom, String telephone, String faculte, String username, String password) {
		super(prenom, nom, telephone, faculte, username, password);
		// TODO Auto-generated constructor stub
	}

}
