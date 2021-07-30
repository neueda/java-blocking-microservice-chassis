package com.neueda.javablockingmicroservicechassis.service;

import com.neueda.javablockingmicroservicechassis.entity.ChassisEntity;
import com.neueda.javablockingmicroservicechassis.repository.ChassisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import java.util.List;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;
import static org.springframework.data.domain.ExampleMatcher.matching;

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
        ChassisEntity chassisEntity = new ChassisEntity();
        chassisEntity.setName(name);
        ExampleMatcher matcher = matching().withMatcher("name", contains());
        Example<ChassisEntity> example = Example.of(chassisEntity, matcher);

        return chassisRepository.findAll(example);

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
