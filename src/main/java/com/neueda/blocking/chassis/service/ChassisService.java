package com.neueda.blocking.chassis.service;

import com.neueda.blocking.chassis.entity.ChassisEntity;
import com.neueda.blocking.chassis.exception.ChassisEntityNotFoundException;
import com.neueda.blocking.chassis.model.Chassis;
import com.neueda.blocking.chassis.repository.ChassisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ChassisService {
    private final ChassisRepository chassisRepository;

    public List<ChassisEntity> retrieveAllChassis() {
        return chassisRepository.findAll();
    }

    public ChassisEntity retrieveChassisById(Long id) {
        return chassisRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Chassis not found with id : "+id));
    }


    public List<ChassisEntity> searchChassisByName(String name) {
//        if (chassisRepository.findByName(name).isEmpty()) {
//            throw new EntityNotFoundException("Chassis not found with name : "+name);
//        }
//        return chassisRepository.findByName(name);
        if (name.isBlank() || name.isEmpty()) {
            throw new EntityNotFoundException("No Chassis with Blank value or Empty value ");
        }
        else{
            List<ChassisEntity> chassis = chassisRepository.findByName(name);
            if (chassis.size() == 0) {
                throw new EntityNotFoundException("Chassis not found with name : " + name);
            }
            return chassis;
        }
    }

    public ChassisEntity addChassis(Chassis chassis) {
        ChassisEntity chassisEntity = new ChassisEntity();
        chassisEntity.setName(chassis.name());
        chassisEntity.setDescription(chassis.description());

        return chassisRepository.save(chassisEntity);
    }

    public void deleteChassis(Long id) {
//        if (chassisRepository.findById(id).isEmpty()) {
//            throw new ChassisEntityNotFoundException("/v1/chassis/{id}", "Chassis Not Found");
//        }
//        chassisRepository.deleteById(id);

          chassisRepository.deleteById(id);
    }
}