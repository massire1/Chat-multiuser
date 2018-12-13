package org.ucad.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ucad.entities.Etudiant;
import java.lang.Long;
import java.util.List;

public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {

	Etudiant findByCode(Long code);
}
