package com.neueda.blocking.chassis.service;

import com.neueda.blocking.chassis.constants.ChassisConstants;
import com.neueda.blocking.chassis.entity.ChassisEntity;
import com.neueda.blocking.chassis.exception.ChassisEntityNotFoundException;
import com.neueda.blocking.chassis.exception.NameFormatException;
import com.neueda.blocking.chassis.exception.NoRecordsFetchedException;
import com.neueda.blocking.chassis.model.Chassis;
import com.neueda.blocking.chassis.repository.ChassisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
        return chassisRepository.findById(id).orElseThrow(() -> new NoRecordsFetchedException("Chassis not found with id : "+id));
    }


    public List<ChassisEntity> searchChassisByName(String name) {
        if (!StringUtils.hasText(name)) {
            throw new NameFormatException("No Chassis with Blank value or Empty value ");
        }
        List<ChassisEntity> chassis = chassisRepository.findByName(name);
        if (chassis.isEmpty()) {
            throw new NoRecordsFetchedException("Chassis not found with name : " + name);
        }
        return chassis;
    }

    public ChassisEntity addChassis(Chassis chassis) {
        ChassisEntity chassisEntity = new ChassisEntity();
        chassisEntity.setName(chassis.name());
        chassisEntity.setDescription(chassis.description());
        return chassisRepository.save(chassisEntity);
    }

    public void deleteChassis(Long id)  {
        if (chassisRepository.findById(id).isEmpty()) {
            throw new NoRecordsFetchedException(ChassisConstants.BASE_URL+ChassisConstants.CHASSIS_URL +"/"+String.valueOf(id));
        }
        chassisRepository.deleteById(id);

    }
}