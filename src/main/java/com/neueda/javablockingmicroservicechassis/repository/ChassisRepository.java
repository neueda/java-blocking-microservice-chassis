package com.neueda.javablockingmicroservicechassis.repository;

import com.neueda.javablockingmicroservicechassis.entity.ChassisEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChassisRepository extends JpaRepository<ChassisEntity, Long> {

}
