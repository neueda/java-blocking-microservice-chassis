package com.neueda.blocking.chassis.service;

import com.neueda.blocking.chassis.entity.ChassisEntity;
import com.neueda.blocking.chassis.exception.NameFormatException;
import com.neueda.blocking.chassis.exception.NoRecordsFetchedException;
import com.neueda.blocking.chassis.model.Chassis;
import com.neueda.blocking.chassis.repository.ChassisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.neueda.blocking.chassis.constants.ChassisConstants.BASE_URL;
import static com.neueda.blocking.chassis.constants.ChassisConstants.CHASSIS_URL;
import static org.springframework.util.StringUtils.hasText;

@RequiredArgsConstructor
@Service
public class ChassisService {
    private final ChassisRepository chassisRepository;

    public List<ChassisEntity> retrieveAllChassis() {
        return chassisRepository.findAll();
    }

    public ChassisEntity retrieveChassisById(Long id) {
        return chassisRepository.findById(id).orElseThrow(() -> new NoRecordsFetchedException("Chassis not found with id : " + id, BASE_URL + CHASSIS_URL + "/" + id));
    }


    public List<ChassisEntity> searchChassisByName(String name) {
        if (!hasText(name)) {
            throw new NameFormatException("No Chassis with Blank value or Empty value", BASE_URL + CHASSIS_URL + "/" + name);
        }
        List<ChassisEntity> chassis = chassisRepository.findByName(name);
        if (chassis.isEmpty()) {
            throw new NoRecordsFetchedException("Chassis not found with name : " + name, BASE_URL + CHASSIS_URL + "/" + name);
        }
        return chassis;
    }

    public ChassisEntity addChassis(Chassis chassis) {
        ChassisEntity chassisEntity = new ChassisEntity();
        chassisEntity.setName(chassis.name());
        chassisEntity.setDescription(chassis.description());
        return chassisRepository.save(chassisEntity);
    }

    public void deleteChassis(Long id) {
        if (chassisRepository.findById(id).isEmpty()) {
            throw new NoRecordsFetchedException("No records to deleted with the id:" + String.valueOf(id), BASE_URL + CHASSIS_URL + "/" + String.valueOf(id));
        }
        chassisRepository.deleteById(id);

    }
}