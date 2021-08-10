package com.neueda.blocking.chassis.repository;

import com.neueda.blocking.chassis.entity.ChassisEntity;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.assertj.core.api.BDDAssertions.then;

@DataJpaTest
class ChassisRepositoryTest {

    @Autowired
    ChassisRepository underTest;

    @Test
    @DisplayName("Testing method search by name")
    void shouldFindByName() {
        //given
        final String name = RandomStringUtils.randomAlphabetic(5);
        final String description1 = RandomStringUtils.randomAlphabetic(6);
        final String description2 = RandomStringUtils.randomAlphabetic(7);

        List<ChassisEntity> expected = (List.of(
                new ChassisEntity(2L,name,description1),
                new ChassisEntity(3L,name,description2)));

        underTest.saveAll(expected);

        //when
        List<ChassisEntity> result = underTest.findByName(name);

        //then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("Testing method search not found by name")
    void shouldNotFindByName() {
        //given
        final String name = RandomStringUtils.randomAlphabetic(5);
        final String description1 = RandomStringUtils.randomAlphabetic(6);
        final String description2 = RandomStringUtils.randomAlphabetic(7);

        List<ChassisEntity> expected = (List.of(
                new ChassisEntity(2L,name,description1),
                new ChassisEntity(3L,name,description2)));

        underTest.saveAll(expected);

        //when
        List<ChassisEntity> result = underTest.findByName("name");

        //then
        assertThat(result).isNotEqualTo(expected);
    }

    @Test
    @DisplayName("Test entity annotated with Lombok @Data")
    void shouldSetContainsEntity() {
        // given
        ChassisEntity chassisEntity = new ChassisEntity();
        Set<ChassisEntity> chassisSet = new HashSet<>();

        // when
        chassisSet.add(chassisEntity);
        underTest.save(chassisEntity);

        //then
        then(chassisSet.contains(chassisEntity)).isTrue();
    }
}