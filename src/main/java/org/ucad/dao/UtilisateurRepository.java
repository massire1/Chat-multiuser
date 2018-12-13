package org.ucad.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ucad.entities.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

	Utilisateur findByUsername(String username);

	Utilisateur findByCode(Long code);

	Utilisateur findByPassword(String password);

}
