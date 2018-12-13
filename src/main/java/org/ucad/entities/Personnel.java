package org.ucad.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("PAT")
public class Personnel extends Utilisateur {

	public Personnel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Personnel(Long code, String prenom, String nom, String telephone, String faculte, String username,
			String password) {
		super(code, prenom, nom, telephone, faculte, username, password);
		// TODO Auto-generated constructor stub
	}

	public Personnel(String prenom, String nom, String telephone, String faculte, String username, String password) {
		super(prenom, nom, telephone, faculte, username, password);
		// TODO Auto-generated constructor stub
	}

}
