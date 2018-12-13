package org.ucad.web;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.ucad.dao.EtudiantRepository;
import org.ucad.entities.Etudiant;

@Scope(value = "session")
@Controller(value = "etudiantController")
// @ELBeanName(value = "etudiantController")
// @Join(path = "/etudiant", to = "/etudiant-form.jsf")
public class EtudiantController {

	@Autowired
	private EtudiantRepository etudiantRepository;

	private String city;
	private Map<String, String> cities = new HashMap<String, String>();

	Etudiant etudiant = new Etudiant();

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Map<String, String> getCities() {
		return cities;
	}

	public void setCities(Map<String, String> cities) {
		this.cities = cities;
	}

	@PostConstruct
	private void init() {
		// TODO Auto-generated method stub

		// cities
		cities = new HashMap<String, String>();
		cities.put("New York", "New York");
		cities.put("London", "London");
		cities.put("Paris", "Paris");
		cities.put("Barcelona", "Barcelona");
		cities.put("Istanbul", "Istanbul");
		cities.put("Berlin", "Berlin");
	}

	public String save() {
		etudiantRepository.save(etudiant);
		etudiant = new Etudiant();
		return "/etudiant-list.xhtml?faces-redirect=true";
	}

	public Etudiant getEtudiant() {
		return etudiant;
	}
}
