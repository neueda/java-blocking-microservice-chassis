package com.neueda.blocking.chassis.contractsBase;

import com.neueda.blocking.chassis.controller.ChassisController;
import com.neueda.blocking.chassis.entity.ChassisEntity;
import com.neueda.blocking.chassis.service.ChassisService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;

@WebMvcTest(ChassisController.class)
public class ChassisBase {
    @Autowired
    private ChassisController chassisController;

    @MockBean
    private ChassisService chassisService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {

        RestAssuredMockMvc.mockMvc(mockMvc);

        //Given
        final String name = RandomStringUtils.randomAlphabetic(5);
        final String description1 = RandomStringUtils.randomAlphabetic(6);
        final String description2 = RandomStringUtils.randomAlphabetic(7);
        when(chassisService.retrieveAllChassis())
                .thenReturn(List.of(
                        new ChassisEntity(1L,name,description1),
                        new ChassisEntity(2L,name,description2)));
    }

}
