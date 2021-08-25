package com.neueda.blocking.chassis.repository;

import com.neueda.blocking.chassis.entity.ChassisEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChassisRepository extends JpaRepository<ChassisEntity, Long> {

       List<ChassisEntity> findByName(@Param("name") String name);

}
