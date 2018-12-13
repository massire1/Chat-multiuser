package org.ucad.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.ucad.entities.Message;

public interface MessageRepository extends JpaRepository<Message, Long>{

	// @Query("Select m from Message m where m.id = :x")
	// public List<Message> findByDestinataire(@Param("x") Long utilisateurId);
}
