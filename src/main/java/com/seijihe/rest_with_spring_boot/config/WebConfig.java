package com.seijihe.rest_with_spring_boot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration 
public class WebConfig implements WebMvcConfigurer{
    
    @Override 
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        
        // http://localhost:8080/api/v1/person/2?mediaType=xml (via query param) - options xml, json, yml
        // configurer.favorParameter(true)
        //     .parameterName("mediaType")
        //     .ignoreAcceptHeader(false) // Will Ignore Header Accept (key) application/json (value)
        //     .useRegisteredExtensionsOnly(false)
        //     .defaultContentType(MediaType.APPLICATION_JSON)
        //         .mediaType("json", MediaType.APPLICATION_JSON)
        //         .mediaType("xml", MediaType.APPLICATION_XML)
        //         .mediaType("yaml", MediaType.APPLICATION_YAML)
        //         .mediaType("yml", MediaType.APPLICATION_YAML);
        
        // Via Header Param http://localhost:8080/api/v1/person/
        // Header: Accept (key) | application/yaml (value)
        configurer.favorParameter(true)
            .ignoreAcceptHeader(false) // Will Ignore Header Accept (key) application/json (value)
            .useRegisteredExtensionsOnly(false)
            .defaultContentType(MediaType.APPLICATION_JSON)
                .mediaType("json", MediaType.APPLICATION_JSON)
                .mediaType("xml", MediaType.APPLICATION_XML)
                .mediaType("yaml", MediaType.APPLICATION_YAML)
                .mediaType("yml", MediaType.APPLICATION_YAML);

    }
}
