package com.neueda.blocking.chassis.service;

import com.neueda.blocking.chassis.model.ChassisDTO;
import com.neueda.blocking.chassis.entity.ChassisEntity;
import com.neueda.blocking.chassis.repository.ChassisRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringJUnitConfig
class ChassisServiceTests {

    @Mock
    private ChassisRepository chassisRepository;

    @InjectMocks
    private ChassisService chassisService;

    @Test
    @DisplayName("Testing to retrive all the chassis")
    void testRetrieveAllChassis()
    {
        //Given
        List<ChassisEntity> chassisEntity = List.of(
                            new ChassisEntity(1L,"name","description 1"),
                            new ChassisEntity(2L,"name","description 2"));
        when(chassisRepository.findAll())
                .thenReturn(chassisEntity);

        //when
        BDDAssertions.assertThat(chassisService.retrieveAllChassis())

                //then
                .isEqualTo(chassisEntity);
    }

    @Test
    @DisplayName("Testing search method by ID")
    void testRetrieveChassisById() throws Exception
    {
        //Given
        ChassisEntity chassis = new ChassisEntity(5L,"name","description 1");
        Mockito.when(chassisRepository.findById(5L)).thenReturn(java.util.Optional.of(chassis));

        //when
        BDDAssertions.assertThat(chassisService.retriveChassisById(5L))

                //then
                .isEqualTo(chassis);
    }

    @Test
    @DisplayName("Testing serch method by name")
    void testSearchChassisByName() throws Exception
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
        BDDAssertions.assertThat(chassisService.searchChassisByName(name)).usingElementComparatorIgnoringFields("id")

                //then
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("Testing for add method")
    void testAddChassis()
    {
        //Given
        ChassisEntity chassis = new ChassisEntity();
        chassis.setName("name");
        chassis.setDescription("description");
        ChassisDTO chassis_dto = new ChassisDTO("name","description");

        when(chassisRepository.save(chassis)).thenReturn(chassis);

        //when
        BDDAssertions.assertThat(chassisService.addChassis(chassis_dto))

                //then
                .isEqualTo(chassis);
    }

    @Test
    @DisplayName("Testing for delete method")
    void testDeleteChassis() throws Exception
    {
        //Given
        ChassisEntity chassis = new ChassisEntity(1L,"name","description ");
        when(chassisRepository.existsById(1L)).thenReturn(false);

        //then
        assertFalse(chassisRepository.existsById(1L));
    }
}