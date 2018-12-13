package org.ucad;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	public void configure(AuthenticationManagerBuilder auth, DataSource dataSource) throws Exception {
		/*
		 * auth.inMemoryAuthentication().withUser("admin").password("{noop}1234").roles(
		 * "ADMIN", "USER");
		 * auth.inMemoryAuthentication().withUser("a").password("{noop}a").roles(
		 * "ADMIN", "USER");
		 */

		auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery(
				"select username as principal, password as credentials, true from utilisateur where username = ?")
				.authoritiesByUsernameQuery(
						"select username as principal, type_user as role from utilisateur where username = ?")
				.rolePrefix("ROLE_");
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/css/**", "/js/**", "/img/**").permitAll();
		
		http.authorizeRequests().anyRequest().authenticated();

		http.formLogin().loginPage("/login.jsf").permitAll().failureUrl("/login.jsf?error=true")
				.successHandler(new AuthenticationSuccessHandler() {
					
					@Override
					public void onAuthenticationSuccess(HttpServletRequest arg0, HttpServletResponse arg1, Authentication arg2)
							throws IOException, ServletException {
						// TODO Auto-generated method stub
						new DefaultRedirectStrategy().sendRedirect(arg0, arg1, "/index.jsf");
					}
				});

		http.logout().permitAll().logoutSuccessUrl("/login.jsf?logout=true");

		http.csrf().disable();
	}

	// permet de désactiver l'utilisation de l'encryption par défaut de spring
	@Bean
	public static NoOpPasswordEncoder passwordEncoder() {
		return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
	}
}
