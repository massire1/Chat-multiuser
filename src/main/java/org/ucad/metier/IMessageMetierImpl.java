package org.ucad.metier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ucad.dao.MessageRepository;
import org.ucad.dao.UtilisateurRepository;

@Service
@Transactional
public class IMessageMetierImpl implements IMessageMetier{

	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	@Autowired
	private MessageRepository messageRepository;
	
}
