package com.neueda.blocking.chassis.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.neueda.blocking.chassis.model.ChassisDTO;
import com.neueda.blocking.chassis.entity.ChassisEntity;
import com.neueda.blocking.chassis.exception.ChassisEntityNotFoundException;
import com.neueda.blocking.chassis.repository.ChassisRepository;
import com.neueda.blocking.chassis.service.ChassisService;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.AfterEach;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ChassisController.class)
class ChassisControllerTests {

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
        final String name = RandomStringUtils.randomAlphabetic(5);
        final String description1 = RandomStringUtils.randomAlphabetic(6);
        final String description2 = RandomStringUtils.randomAlphabetic(7);
        when(chassisService.retrieveAllChassis())
                .thenReturn(List.of(
                        new ChassisEntity(1L,name,description1),
                        new ChassisEntity(2L,name,description2)));
        final ChassisDTO chassisDto1 = new ChassisDTO(name, description1);
        final ChassisDTO chassisDto2 = new ChassisDTO(name, description2);
        final List<ChassisDTO> expected = List.of(chassisDto1, chassisDto2);

        // when
        mockMvc.perform(get("/v1/chassis"))

                // then
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(expected)));
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
        final String name = RandomStringUtils.randomAlphabetic(5);
        final String description = RandomStringUtils.randomAlphabetic(6);
        when(chassisService.retriveChassisById(5L)).thenReturn(new ChassisEntity(5L,name,description));
        final ChassisDTO expected = new ChassisDTO(name, description);

        //when
        mockMvc.perform(get("/v1/chassis/5"))

                //then
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(expected)));
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
        final String name = RandomStringUtils.randomAlphabetic(5);
        final String description1 = RandomStringUtils.randomAlphabetic(6);
        final String description2 = RandomStringUtils.randomAlphabetic(7);

        when(chassisService.searchChassisByName(name))
            .thenReturn(List.of(
                new ChassisEntity(1L, name, description1),
                new ChassisEntity(2L, name, description2)
            ));

        final ChassisDTO chassisDto1 = new ChassisDTO(name, description1);
        final ChassisDTO chassisDto2 = new ChassisDTO(name, description2);
        final List<ChassisDTO> expected = List.of(chassisDto1, chassisDto2);

        //when
        mockMvc.perform(get("/v1/chassisSearch/" + name))

            //then
            .andExpect(status().isOk())
            .andExpect(content().json(objectMapper.writeValueAsString(expected)));
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
