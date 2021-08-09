package com.neueda.blocking.chassis.service;

import com.neueda.blocking.chassis.exception.ChassisEntityNotFoundException;
import com.neueda.blocking.chassis.repository.ChassisRepository;
import com.neueda.blocking.chassis.model.Chassis;
import com.neueda.blocking.chassis.entity.ChassisEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ChassisService {

    private final ChassisRepository chassisRepository;

    public List<ChassisEntity> retrieveAllChassis() {
        return chassisRepository.findAll();
    }

    public ChassisEntity retriveChassisById(Long id) throws ChassisEntityNotFoundException {
        return chassisRepository.findById(id).get();
    }

    public List<ChassisEntity> searchChassisByName(String name) throws ChassisEntityNotFoundException{
        return chassisRepository.findByName(name);
    }

    public ChassisEntity addChassis(Chassis chassis){
        ChassisEntity chassisEntity = new ChassisEntity();
        chassisEntity.setName(chassis.getName());
        chassisEntity.setDescription(chassis.getDescription());
        return chassisRepository.save(chassisEntity);
    }

    public void deleteChassis(Long id) throws ChassisEntityNotFoundException{
        chassisRepository.deleteById(id);
    }


}
