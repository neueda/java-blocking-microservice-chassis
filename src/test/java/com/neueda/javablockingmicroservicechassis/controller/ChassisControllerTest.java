package com.neueda.javablockingmicroservicechassis.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.neueda.javablockingmicroservicechassis.dto.ChassisDTO;
import com.neueda.javablockingmicroservicechassis.entity.ChassisEntity;
import com.neueda.javablockingmicroservicechassis.repository.ChassisRepository;
import com.neueda.javablockingmicroservicechassis.service.ChassisService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ChassisController.class)
public class ChassisControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ChassisService chassisService;

    @MockBean
    private ChassisRepository chassisRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAllChassis() throws Exception {
        List<ChassisEntity> chassisEntityList = new ArrayList<>();
        chassisEntityList.add(new ChassisEntity(1L,"name 1","description 1"));
        chassisEntityList.add(new ChassisEntity(2L,"name 2","description 2"));
        Mockito.when(chassisService.retrieveAllChassis()).thenReturn(chassisEntityList);
        String url = "/v1/chassis";
        mockMvc.perform(get(url)).andExpect(status().isOk());
    }

    @Test
    public void testGetChassisById() throws Exception {
        ChassisEntity chassisEntity = new ChassisEntity(5L,"name 5","description 5");
        Mockito.when(chassisService.retriveChassisById(5L)).thenReturn(chassisEntity);
        String url = "/v1/chassis/5";
        mockMvc.perform(get(url)).andExpect(status().isOk());
    }

    @Test
    public void getChassisByName() throws Exception {
        List<ChassisEntity> chassisEntityList = new ArrayList<>();
        chassisEntityList.add(new ChassisEntity(1L,"name","description 1"));
        chassisEntityList.add(new ChassisEntity(2L,"name","description 2"));
        String name = "name";
        Mockito.when(chassisService.searchChassisByName(name)).thenReturn(chassisEntityList);
        String url = "/v1/chassisSearch/name";
        mockMvc.perform(get(url)).andExpect(status().isOk());
    }

    @Test
    public void testCreate() throws Exception {
        ChassisEntity chassisEntity = new ChassisEntity(6L,"name 6","description 6");
        ChassisDTO chassisDTO = new ChassisDTO("name 6","description 6");
        Mockito.when(chassisService.addChassis(chassisDTO)).thenReturn(chassisEntity);
        String url = "/v1/chassis";
        mockMvc.perform(post(url).contentType("application/json").content(objectMapper.writeValueAsString(chassisDTO))).andExpect(status().isCreated());
    }

    @Test
    public void testDeleteChassis() {
    }
}