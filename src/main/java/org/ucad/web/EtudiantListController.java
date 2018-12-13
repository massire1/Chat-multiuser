package org.ucad.web;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import org.ucad.dao.EtudiantRepository;
import org.ucad.entities.Etudiant;

//@Scope(value = "session")
@SessionScope
@Component(value = "etudiantList")
// @ELBeanName(value = "etudiantList")
// @Join(path = "/", to = "/etudiant-list.jsf")
public class EtudiantListController {

	@Autowired
	private EtudiantRepository etudiantRepository;

	private List<Etudiant> etudiants;

	public List<Etudiant> getEtudiants() {
		return etudiantRepository.findAll();
	}

	public void onSelect(SelectEvent event) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Item Selected", event.getObject().toString()));
		System.out.println("item selected : " + event.getObject().toString());
	}
}
