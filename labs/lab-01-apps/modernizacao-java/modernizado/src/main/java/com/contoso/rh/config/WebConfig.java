package com.contoso.rh.config;

import org.springframework.boot.jackson.autoconfigure.JsonMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import tools.jackson.databind.cfg.DateTimeFeature;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	@Bean
	public JsonMapperBuilderCustomizer legacyDateTimestamps() {
		return builder -> builder.enable(DateTimeFeature.WRITE_DATES_AS_TIMESTAMPS);
	}
}
