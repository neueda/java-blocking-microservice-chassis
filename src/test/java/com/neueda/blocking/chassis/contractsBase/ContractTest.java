package com.neueda.blocking.chassis.contractsBase;

import com.neueda.blocking.chassis.service.ChassisService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.restdocs.config.RestDocumentationConfigurer;
import org.springframework.test.web.servlet.MockMvc;


@Tag("ContractTest")
@AutoConfigureRestDocs
abstract class ContractTest {

}