package com.neueda.javablockingmicroservicechassis.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.neueda.javablockingmicroservicechassis.dto.ChassisDTO;
import com.neueda.javablockingmicroservicechassis.entity.ChassisEntity;
import com.neueda.javablockingmicroservicechassis.repository.ChassisRepository;
import com.neueda.javablockingmicroservicechassis.service.ChassisService;
import org.assertj.core.api.BDDAssertions;
import org.assertj.core.api.ThrowableAssert;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ChassisController.class)
class ChassisControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ChassisService chassisService;

    @MockBean
    private ChassisRepository chassisRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllChassis() throws Exception {
        // given
        when(chassisService.retrieveAllChassis())
                .thenReturn(List.of(
                        new ChassisEntity(1L,"name 1","description 1"),
                        new ChassisEntity(2L,"name 2","description 2")));

        // when
        mockMvc.perform(get("/v1/chassis"))
                // then
                .andExpect(status().isOk());
    }

    @Test
    void testGetChassisById() throws Exception {
        ChassisEntity chassisEntity = new ChassisEntity(5L,"name 5","description 5");
        when(chassisService.retriveChassisById(5L)).thenReturn(chassisEntity);
        String url = "/v1/chassis/5";
        mockMvc.perform(get(url)).andExpect(status().isOk());
    }

    @Test
    void getChassisByName() throws Exception {
        List<ChassisEntity> chassisEntityList = new ArrayList<>();
        chassisEntityList.add(new ChassisEntity(1L,"name","description 1"));
        chassisEntityList.add(new ChassisEntity(2L,"name","description 2"));
        String name = "name";
        when(chassisService.searchChassisByName(name)).thenReturn(chassisEntityList);
        String url = "/v1/chassisSearch/name";
        mockMvc.perform(get(url)).andExpect(status().isOk());
    }

    @Test
    void testCreate() throws Exception {
        ChassisEntity chassisEntity = new ChassisEntity(6L,"name 6","description 6");
        ChassisDTO chassisDTO = new ChassisDTO("name 6","description 6");
        when(chassisService.addChassis(chassisDTO)).thenReturn(chassisEntity);
        String url = "/v1/chassis";
        mockMvc.perform(post(url).contentType("application/json").content(objectMapper.writeValueAsString(chassisDTO))).andExpect(status().isCreated());
    }

    @Test
    void testDeleteChassis() {
        // given
        // ToDo: This is just a exception test example it have to be replaced by a real method test
        when(chassisService.retrieveAllChassis())
                .thenThrow(IllegalArgumentException.class);

        // when
        ThrowingCallable methodUnderTest = () -> chassisService.retrieveAllChassis();

        // then
        BDDAssertions.thenThrownBy(methodUnderTest)
                .isInstanceOf(IllegalArgumentException.class);
    }
}