package org.ucad.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("ENS")
public class Enseignant extends Utilisateur {

	public Enseignant() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public Enseignant(Long code, String prenom, String nom, String telephone, String faculte, String username,
			String password) {
		super(code, prenom, nom, telephone, faculte, username, password);
		// TODO Auto-generated constructor stub
	}


	public Enseignant(String prenom, String nom, String telephone, String faculte, String username, String password) {
		super(prenom, nom, telephone, faculte, username, password);
		// TODO Auto-generated constructor stub
	}

}
