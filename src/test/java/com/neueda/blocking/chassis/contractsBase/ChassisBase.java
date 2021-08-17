package com.neueda.blocking.chassis.contractsBase;

import com.neueda.blocking.chassis.controller.ChassisController;
import com.neueda.blocking.chassis.entity.ChassisEntity;
import com.neueda.blocking.chassis.service.ChassisService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;
import static org.mockito.Mockito.when;

@WebMvcTest(ChassisController.class)
public class ChassisBase extends BaseContractTest {
    @Autowired
    private ChassisController chassisController;

    @MockBean
    private ChassisService chassisService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {

        RestAssuredMockMvc.standaloneSetup(mockMvc);

        List<ChassisEntity> response = List.of(
                new ChassisEntity(1L,"test name","test description"));
        when(chassisService.retrieveAllChassis())
                .thenReturn(response);

    }

}
