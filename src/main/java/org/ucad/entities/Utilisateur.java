package org.ucad.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE_USER", discriminatorType = DiscriminatorType.STRING, length = 5)
public class Utilisateur implements Serializable {

	@Id
	@GeneratedValue
	private Long code;
	private String prenom;
	private String nom;
	private String telephone;
	private String faculte;
	private String username;
	private String password;

	public Utilisateur() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Utilisateur(Long code, String prenom, String nom, String telephone, String faculte, String username,
			String password) {
		super();
		this.code = code;
		this.prenom = prenom;
		this.nom = nom;
		this.telephone = telephone;
		this.faculte = faculte;
		this.username = username;
		this.password = password;
	}

	public Utilisateur(String prenom, String nom, String telephone, String faculte, String username, String password) {
		super();
		this.prenom = prenom;
		this.nom = nom;
		this.telephone = telephone;
		this.faculte = faculte;
		this.username = username;
		this.password = password;
	}

	@Override
	public String toString() {
		return "Utilisateur [code=" + code + ", prenom=" + prenom + ", nom=" + nom + ", telephone=" + telephone
				+ ", faculte=" + faculte + ", username=" + username + ", password=" + password + "]";
	}

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getFaculte() {
		return faculte;
	}

	public void setFaculte(String faculte) {
		this.faculte = faculte;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
