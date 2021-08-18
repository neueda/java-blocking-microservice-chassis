package com.neueda.blocking.chassis.contractsBase;

import com.neueda.blocking.chassis.controller.ChassisController;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.config.RestDocumentationConfigurer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;

@Tag("BaseContractTest")
@AutoConfigureRestDocs
abstract class BaseContractTest {

    @Autowired
    private ChassisController chassisController;

    @Autowired
    private RestDocumentationConfigurer configurer;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RestDocumentationContextProvider restDocumentation;

    private WebApplicationContext context;

    void Setup() {
        RestAssuredMockMvc.mockMvc(mockMvc);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
                .apply(documentationConfiguration(this.restDocumentation))
                .alwaysDo(document(("{retrieveAllChassis}/{ChassisEntity}/")))
                .build();

    }
}