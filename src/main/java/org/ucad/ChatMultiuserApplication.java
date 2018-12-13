package org.ucad;

import java.util.Date;
import java.util.EnumSet;

import javax.faces.webapp.FacesServlet;
import javax.servlet.DispatcherType;

import org.ocpsoft.rewrite.servlet.RewriteFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.ucad.dao.EnseignantRepository;
import org.ucad.dao.EtudiantRepository;
import org.ucad.dao.MessageRepository;
import org.ucad.entities.Enseignant;
import org.ucad.entities.Etudiant;
import org.ucad.entities.Message;

@SpringBootApplication
public class ChatMultiuserApplication extends SpringBootServletInitializer implements CommandLineRunner {

	@Autowired
	private EtudiantRepository etudiantRepository;

	@Autowired
	private EnseignantRepository enseignantRepository;

	@Autowired
	private MessageRepository messageRepository;

	public static void main(String[] args) {
		SpringApplication.run(ChatMultiuserApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub

		Etudiant etudiant = new Etudiant("Masta", "Flex", "771799011", "FST", "masta", "flex");
		Etudiant etudiant2 = new Etudiant("Bineta", "Soumare", "771799011", "FST", "bineta", "soumare");
		Etudiant etudiant3 = new Etudiant("Amadaou", "DIOP", "771799011", "FST", "a", "a");
		Etudiant etudiant4 = new Etudiant("John", "DOE", "771799011", "FST", "user1", "user1");		
//		etudiantRepository.save(etudiant);
//		etudiantRepository.save(etudiant2);
//		etudiantRepository.save(etudiant3);
//		etudiantRepository.save(etudiant4);

		Enseignant enseignant = new Enseignant("Joseph", "Ndong", "784545214", "LEA", "joseph", "ndong");
		Enseignant enseignant2 = new Enseignant("Modou", "GUEYE", "784545214", "FST", "user2", "user2");
//		enseignantRepository.save(enseignant);
//		enseignantRepository.save(enseignant2);

		// messageRepository.save(new Message("salut", new Date(), null, null, null,
		// etudiant, enseignant));
		// messageRepository.save(new Message("tu vas bien ", new Date(), null, null,
		// null, etudiant, enseignant));
		// messageRepository.save(new Message("quoi de neuf", new Date(), null, null,
		// null, etudiant, enseignant));

		// creation du compte adminstrateur
		Enseignant profAdmin = new Enseignant("ALiou", "BOLI", "784545214", "MATHS-INF", "aliou", "boly");
//		enseignantRepository.save(profAdmin);
	}

	// JSF Bean

	@Bean
	public ServletRegistrationBean<FacesServlet> servletRegistrationBean() {
		FacesServlet servlet = new FacesServlet();
		return new ServletRegistrationBean<FacesServlet>(servlet, "*.jsf");
	}

	@Bean
	public FilterRegistrationBean<RewriteFilter> rewriteFilter() {
		FilterRegistrationBean<RewriteFilter> rwFilter = new FilterRegistrationBean<RewriteFilter>(new RewriteFilter());
		rwFilter.setDispatcherTypes(
				EnumSet.of(DispatcherType.FORWARD, DispatcherType.REQUEST, DispatcherType.ASYNC, DispatcherType.ERROR));
		rwFilter.addUrlPatterns("/*");
		return rwFilter;
	}
}
