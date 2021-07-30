package com.neueda.javablockingmicroservicechassis.service;

import com.neueda.javablockingmicroservicechassis.entity.ChassisEntity;
import com.neueda.javablockingmicroservicechassis.repository.ChassisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ChassisService {

    private final ChassisRepository chassisRepository;

    public List<ChassisEntity> retrieveAllChassis(){
        return chassisRepository.findAll();
    }

    public ChassisEntity retriveChassisById(Long id){
        return chassisRepository.findById(id).get();
    }

    public List<ChassisEntity> searchChassisByName(String name){
        return chassisRepository.findByName(name);
    }

    public ChassisEntity addChassis(ChassisEntity chassis){
        ChassisEntity chassisEntity = new ChassisEntity();
        chassisEntity.setName(chassis.getName());
        chassisEntity.setDescription(chassis.getDescription());
        return chassisRepository.save(chassisEntity);
    }

    public void deleteChassis(Long id) {
        chassisRepository.deleteById(id);
    }


}
