package com.neueda.javablockingmicroservicechassis.service;

import com.neueda.javablockingmicroservicechassis.dto.ChassisDTO;
import com.neueda.javablockingmicroservicechassis.entity.ChassisEntity;
import com.neueda.javablockingmicroservicechassis.repository.ChassisRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ChassisServiceTest {

    @Mock
    private ChassisRepository chassisRepository;

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private ChassisService chassisService;

    @Test
    void retrieveAllChassis()
    {
        //Given
        List<ChassisEntity> chassisEntity = List.of(
                            new ChassisEntity(1L,"name","description 1"),
                            new ChassisEntity(2L,"name","description 2"));
        when(chassisRepository.findAll())
                .thenReturn(chassisEntity);
        //when
        assertThat(chassisService.retrieveAllChassis())
                //then
                .isEqualTo(chassisEntity);
    }

    @Test
    void retreiveChassisById() throws Exception
    {
        //Given
        ChassisEntity chassis = new ChassisEntity(5L,"name","description 1");
        Mockito.when(chassisRepository.findById(5L)).thenReturn(java.util.Optional.of(chassis));

        //when
        assertThat(chassisService.retriveChassisById(5L))
                //then
                .isEqualTo(chassis);
    }

    @Test
    void searchChassisByName() throws Exception
    {
        //Given
        final String name = RandomStringUtils.randomAlphabetic(5);
        final String description1 = RandomStringUtils.randomAlphabetic(6);
        final String description2 = RandomStringUtils.randomAlphabetic(7);

        when(chassisRepository.findByName(name))
                .thenReturn(List.of(
                        new ChassisEntity(1L, name, description1),
                        new ChassisEntity(2L, name, description2)
                ));

        final ChassisDTO chassisDto1 = new ChassisDTO(name, description1);
        final ChassisDTO chassisDto2 = new ChassisDTO(name, description2);
        final List<ChassisDTO> expected = List.of(chassisDto1, chassisDto2);

        //when
        assertThat(chassisService.searchChassisByName(name)).usingElementComparatorIgnoringFields("id")
                //then
                .isEqualTo(expected);
    }

    @Test
    void addChassis()
    {
        //Given
        ChassisEntity chassis = new ChassisEntity();
        chassis.setName("name");
        chassis.setDescription("description");
        ChassisDTO chassis_dto = new ChassisDTO("name","description");

        when(chassisRepository.save(chassis)).thenReturn(chassis);

        //when
        assertThat(chassisService.addChassis(chassis_dto))
                //then
                .isEqualTo(chassis);
    }

    @Test
    void deleteChassis() throws Exception
    {
        //Given
        ChassisEntity chassis = new ChassisEntity(1L,"name","description ");
        when(chassisRepository.existsById(1L)).thenReturn(false);

        //then
        assertFalse(chassisRepository.existsById(1L));
    }
}