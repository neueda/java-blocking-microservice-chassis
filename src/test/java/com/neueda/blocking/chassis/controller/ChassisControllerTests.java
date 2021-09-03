package com.neueda.blocking.chassis.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.neueda.blocking.chassis.client.GithubClient;
import com.neueda.blocking.chassis.entity.ChassisEntity;
import com.neueda.blocking.chassis.model.Chassis;
import com.neueda.blocking.chassis.repository.ChassisRepository;
import com.neueda.blocking.chassis.service.ChassisService;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@WebMvcTest(ChassisController.class)
@AutoConfigureRestDocs
class ChassisControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ChassisService chassisService;

    @MockBean
    private GithubClient gitHubClient;


    @MockBean
    private ChassisRepository chassisRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("testing get method to get all values")
    void testGetAllChassis() throws Exception {
        //Given
        final String name = RandomStringUtils.randomAlphabetic(5);
        final String description1 = RandomStringUtils.randomAlphabetic(6);
        final String description2 = RandomStringUtils.randomAlphabetic(7);
        when(chassisService.retrieveAllChassis())
                .thenReturn(List.of(
                        new ChassisEntity(1L,name,description1),
                        new ChassisEntity(2L,name,description2)));
        final Chassis chassis1 = new Chassis(name, description1);
        final Chassis chassis2 = new Chassis(name, description2);
        final List<Chassis> expected = List.of(chassis1, chassis2);

        //when
        mockMvc.perform(get("/api/v1/chassis"))
                //then
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(expected))).andDo(print())
                .andDo(document("{testGetAllChassis}",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())));

    }

    @Test
    @DisplayName("Testing get values by Id method")
    void testGetAllChassis_EntityNotFound() throws Exception {
        //Given
        when(chassisService.retrieveAllChassis()).thenReturn(null);

        //when
        mockMvc.perform(get("/api/v1/chassis"))
                //then
                .andExpect(status().isOk()).andDo(print())
                .andDo(document("{testGetAllChassis_EntityNotFound}",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())));
    }

    @Test
    @DisplayName("Testing get values by Id method")
    void testGetChassisById() throws Exception {
        //Given
        final String name = RandomStringUtils.randomAlphabetic(5);
        final String description = RandomStringUtils.randomAlphabetic(6);
        when(chassisService.retrieveChassisById(5L)).thenReturn(new ChassisEntity(5L,name,description));
        final Chassis expected = new Chassis(name, description);

        //when
        mockMvc.perform(get("/api/v1/chassis/5"))
                //then
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(expected))).andDo(print())
                .andDo(document("{testGetChassisById}",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())));
    }

    @Test
    @DisplayName("Testing get value by id not found method")
    void testGetChassisById_EntityNotFound() throws Exception {
        //Given
        when(chassisService.retrieveChassisById(5L)).thenReturn(null);

        //when
        mockMvc.perform(get("/api/v1/chassis/5"))
                //then
                .andExpect(status().isOk()).andDo(print())
                .andDo(document("{testGetChassisById_EntityNotFound}",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())));
    }

    @Test
    @DisplayName("Testing get values by name method")
    void testGetChassisByName() throws Exception {
        //Given
        final String name = RandomStringUtils.randomAlphabetic(5);
        final String description1 = RandomStringUtils.randomAlphabetic(6);
        final String description2 = RandomStringUtils.randomAlphabetic(7);

        when(chassisService.searchChassisByName(name))
                .thenReturn(List.of(
                        new ChassisEntity(1L, name, description1),
                        new ChassisEntity(2L, name, description2)
                ));

        final Chassis chassis1 = new Chassis(name, description1);
        final Chassis chassis2 = new Chassis(name, description2);
        final List<Chassis> expected = List.of(chassis1, chassis2);

        //when
        mockMvc.perform(get("/api/v1/chassisSearch/" + name))
                //then
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(expected))).andDo(print())
                .andDo(document("{testGetChassisByName}",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())));
    }

    @Test
    @DisplayName("Testing get by name not found ")
    void testGetChassisByName_EntityNotFound() throws Exception {
        //Given
        String name = "name";
        when(chassisService.searchChassisByName(name)).thenReturn(null);

        //when
        mockMvc.perform(get("/api/v1/chassisSearch/name"))
                //then
                .andExpect(status().isOk()).andDo(print())
                .andDo(document("{testGetChassisByName_EntityNotFound}",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())));
    }

    @Test
    @DisplayName("Testing create method")
    void testCreate() throws Exception {
        //Given
        Chassis chassis = new Chassis("name 6","description 6");
        when(chassisService.addChassis(new Chassis("name 6","description 6"))).thenReturn( new ChassisEntity(6L,"name 6","description 6"));

        //when
        mockMvc.perform(post("/api/v1/chassis")
                        //then
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(chassis)))
                .andExpect(status().isCreated()).andDo(print())
                .andDo(document("{testCreate}",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())));
    }

    @Test
    @DisplayName("Testing Delete method")
    void testDeleteChassis() throws Exception {
        //Given
        ChassisEntity chassisEntity = new ChassisEntity(6L, "name 6", "description 6");
        doNothing().when(chassisService).deleteChassis(chassisEntity.getId());

        //when
        mockMvc.perform(delete("/api/v1/chassis/6").contentType(MediaType.APPLICATION_JSON))
                //then
                .andExpect(status().isOk()).andDo(print())
                .andDo(document("{testDeleteChassis}",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())));
    }

}