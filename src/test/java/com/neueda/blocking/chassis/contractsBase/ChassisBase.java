package com.neueda.blocking.chassis.contractsBase;

import com.neueda.blocking.chassis.client.GithubClient;
import com.neueda.blocking.chassis.configuration.RestDocsConfig;
import com.neueda.blocking.chassis.controller.ChassisController;
import com.neueda.blocking.chassis.entity.ChassisEntity;
import com.neueda.blocking.chassis.service.ChassisService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;

@Tag("ContractTest")
@WebMvcTest(ChassisController.class)
@AutoConfigureRestDocs
@Import(RestDocsConfig.class)
public abstract class ChassisBase {

    @MockBean
    private ChassisService chassisService;

    @MockBean
    private GithubClient githubClient;

    @BeforeEach
    void setUp(@Autowired MockMvc mockMvc) {
        // setup
        RestAssuredMockMvc.mockMvc(mockMvc);

        // given
        List<ChassisEntity> response = List.of(
                new ChassisEntity(1L,"test name","test description"));
        given(chassisService.retrieveAllChassis())
                .willReturn(response);
    }
}