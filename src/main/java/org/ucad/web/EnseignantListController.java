package org.ucad.web;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import org.ucad.dao.EnseignantRepository;
import org.ucad.dao.EtudiantRepository;
import org.ucad.entities.Enseignant;
import org.ucad.entities.Etudiant;

//@Scope(value = "session")
@SessionScope
@Component(value = "enseignantList")
// @ELBeanName(value = "etudiantList")
// @Join(path = "/", to = "/etudiant-list.jsf")
public class EnseignantListController {

	@Autowired
	private EnseignantRepository enseignantRepository;

	private List<Enseignant> enseignants;

	public List<Enseignant> getEnseignants() {
		return enseignantRepository.findAll();
	}

	public void onSelect(SelectEvent event) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Item Selected", event.getObject().toString()));
		System.out.println("item selected : " + event.getObject().toString());
	}	
	
	public void simpleMethode() {
		System.out.println("Bonjour tout le monde !");
	}
		
}
