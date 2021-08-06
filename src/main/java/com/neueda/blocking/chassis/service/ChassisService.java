package com.neueda.blocking.chassis.service;

import com.neueda.blocking.chassis.exception.ChassisEntityNotFoundException;
import com.neueda.blocking.chassis.repository.ChassisRepository;
import com.neueda.blocking.chassis.model.ChassisDTO;
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
        if(chassisRepository.findById(id).isEmpty()){
            throw new ChassisEntityNotFoundException();
        }
        return chassisRepository.findById(id).get();
    }

    public List<ChassisEntity> searchChassisByName(String name) throws ChassisEntityNotFoundException{
        if(chassisRepository.findByName(name).isEmpty()){
            throw new ChassisEntityNotFoundException();
        }
        return chassisRepository.findByName(name);
    }

    public ChassisEntity addChassis(ChassisDTO chassisDTO){
        ChassisEntity chassisEntity = new ChassisEntity();
        chassisEntity.setName(chassisDTO.getName());
        chassisEntity.setDescription(chassisDTO.getDescription());
        return chassisRepository.save(chassisEntity);
    }

    public void deleteChassis(Long id) throws ChassisEntityNotFoundException{
        if(chassisRepository.findById(id).isEmpty()){
            throw new ChassisEntityNotFoundException();
        }
        chassisRepository.deleteById(id);
    }


}
