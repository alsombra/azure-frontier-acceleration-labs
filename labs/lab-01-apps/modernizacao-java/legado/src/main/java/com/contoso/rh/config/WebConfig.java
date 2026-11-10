package com.contoso.rh.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    // Estilo Spring Boot 1.5: estendendo WebMvcConfigurerAdapter (deprecated em 5.x).
}
