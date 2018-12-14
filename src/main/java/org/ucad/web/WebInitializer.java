package org.ucad.web;

import org.ucad.ChatMultiuserApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class WebInitializer extends SpringBootServletInitializer {

    protected SpringApplicationBuilder configure (SpringApplicationBuilder builder) {
        return builder.sources(ChatMultiuserApplication.class);
    }
}