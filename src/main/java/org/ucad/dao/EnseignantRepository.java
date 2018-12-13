package org.ucad.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ucad.entities.Enseignant;

public interface EnseignantRepository extends JpaRepository<Enseignant, Long> {

}
