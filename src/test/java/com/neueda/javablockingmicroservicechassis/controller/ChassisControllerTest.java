package com.neueda.javablockingmicroservicechassis.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.neueda.javablockingmicroservicechassis.dto.ChassisDTO;
import com.neueda.javablockingmicroservicechassis.entity.ChassisEntity;
import com.neueda.javablockingmicroservicechassis.exception.ChassisEntityNotFoundException;
import com.neueda.javablockingmicroservicechassis.repository.ChassisRepository;
import com.neueda.javablockingmicroservicechassis.service.ChassisService;
import org.assertj.core.api.BDDAssertions;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
    void testGetAllChassis_EntityNotFound() throws Exception {
        //given
        when(chassisService.retrieveAllChassis()).thenThrow(new ChassisEntityNotFoundException(""));
        //when
        mockMvc.perform(get("/v1/chassis"))
                //then
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetChassisById() throws Exception {
        //given
        when(chassisService.retriveChassisById(5L)).thenReturn(new ChassisEntity(5L,"name 5","description 5"));
        //when
        mockMvc.perform(get("/v1/chassis/5"))
                //then
                .andExpect(status().isOk());
    }

    @Test
    void testGetChassisById_EntityNotFound() throws Exception {
        //given
        when(chassisService.retriveChassisById(5L)).thenThrow(new ChassisEntityNotFoundException(""));
        //when
        mockMvc.perform(get("/v1/chassis/5"))
                //then
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetChassisByName() throws Exception {
        //given
        String name = "name";
        when(chassisService.searchChassisByName(name)).thenReturn(List.of(
                new ChassisEntity(1L,"name","description 1"),
                new ChassisEntity(2L,"name","description 2")));
        //when
        mockMvc.perform(get("/v1/chassisSearch/name"))
                //then
                .andExpect(status().isOk());
    }

    @Test
    void testGetChassisByName_EntityNotFound() throws Exception {
        //given
        String name = "name";
        when(chassisService.searchChassisByName(name)).thenThrow(new ChassisEntityNotFoundException(""));
        //when
        mockMvc.perform(get("/v1/chassisSearch/name"))
                //then
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreate() throws Exception {
        //given
        ChassisDTO chassisDTO = new ChassisDTO("name 6","description 6");
        when(chassisService.addChassis(new ChassisDTO("name 6","description 6"))).thenReturn( new ChassisEntity(6L,"name 6","description 6"));
        //when
        mockMvc.perform(post("/v1/chassis")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(chassisDTO)))
                //then
                .andExpect(status().isCreated());
    }

    @Test
    void testDeleteChassis() throws Exception {
        //given
        ChassisEntity chassisEntity = new ChassisEntity(6L, "name 6", "description 6");
        doNothing().when(chassisService).deleteChassis(chassisEntity.getId());
        //when
        mockMvc.perform(delete("/v1/chassis/6").contentType(MediaType.APPLICATION_JSON))
                //then
                .andExpect(status().isNoContent());
    }


        // given
        // ToDo: This is just a exception test example it have to be replaced by a real method test
        //when(chassisService.retrieveAllChassis())
         //       .thenThrow(IllegalArgumentException.class);

        // when
        //ThrowingCallable methodUnderTest = () -> chassisService.retrieveAllChassis();

        // then
        //BDDAssertions.thenThrownBy(methodUnderTest)
          //      .isInstanceOf(IllegalArgumentException.class);

}