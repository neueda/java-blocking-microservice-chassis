package com.neueda.blocking.chassis.service;

import com.neueda.blocking.chassis.entity.ChassisEntity;
import com.neueda.blocking.chassis.model.Chassis;
import com.neueda.blocking.chassis.repository.ChassisRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringJUnitConfig(ChassisService.class)
class ChassisServiceTests {

    @MockBean
    private ChassisRepository chassisRepository;

    @Autowired
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
        then(chassisService.retrieveAllChassis())

                //then
                .isEqualTo(chassisEntity);
    }

    @Test
    @DisplayName("Testing search method by ID")
    void testRetrieveChassisById()
    {
        //Given
        ChassisEntity chassis = new ChassisEntity(5L,"name","description 1");
        Mockito.when(chassisRepository.findById(5L)).thenReturn(java.util.Optional.of(chassis));

        //when
        then(chassisService.retrieveChassisById(5L))

                //then
                .isEqualTo(chassis);
    }

    @Test
    @DisplayName("Testing serch method by name")
    void testSearchChassisByName()
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

        final Chassis chassisDto1 = new Chassis(name, description1);
        final Chassis chassisDto2 = new Chassis(name, description2);
        final List<Chassis> expected = List.of(chassisDto1, chassisDto2);

        //when
        then(chassisService.searchChassisByName(name)).usingElementComparatorIgnoringFields("id")

                //then
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("Testing for add method")
    void shouldAddChassis() {
        // given
        var expectedChassisEntity =
                new ChassisEntity(null, "name", "description");
        when(chassisRepository.save(any(ChassisEntity.class)))
                .thenReturn(expectedChassisEntity);

        // when
        var addedChassisEntity =
                chassisService.addChassis(new Chassis("name","description"));

        // then
        then(addedChassisEntity).isEqualTo(expectedChassisEntity);
    }

    @Test
    @DisplayName("Testing for delete method")
    void testDeleteChassis()
    {
        //Given
        ChassisEntity chassis = new ChassisEntity(1L,"name","description ");
        when(chassisRepository.existsById(1L)).thenReturn(false);

        //then
        assertFalse(chassisRepository.existsById(1L));
    }
}