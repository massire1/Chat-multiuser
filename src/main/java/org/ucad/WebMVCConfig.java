package org.ucad;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SuppressWarnings("deprecation")
@Configuration
public class WebMVCConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		// TODO Auto-generated method stub

		// registry.addViewController("/login").setViewName("login");
		//
		// registry.addViewController("/").setViewName("index");
		registry.addViewController("/").setViewName("forward:/index.jsf");
		registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
	}
}
