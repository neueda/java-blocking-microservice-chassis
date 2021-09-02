package com.neueda.blocking.chassis.configuration;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;

@TestConfiguration(proxyBeanMethods = false)
public class RestDocsConfig {

    @Bean
    RestDocumentationResultHandler restDocumentationResultHandler() {
        return MockMvcRestDocumentation.document("{class-name}/{method-name}");
    }
}