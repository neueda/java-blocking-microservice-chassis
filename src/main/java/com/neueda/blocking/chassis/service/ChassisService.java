package com.neueda.blocking.chassis.service;

import com.neueda.blocking.chassis.entity.ChassisEntity;
import com.neueda.blocking.chassis.exception.InputFormatException;
import com.neueda.blocking.chassis.exception.NoRecordsFetchedException;
import com.neueda.blocking.chassis.model.Chassis;
import com.neueda.blocking.chassis.repository.ChassisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.neueda.blocking.chassis.constants.ChassisConstants.BASE_URL;
import static com.neueda.blocking.chassis.constants.ChassisConstants.CHASSIS_URL;
import static java.lang.String.format;
import static org.springframework.util.StringUtils.hasText;

@RequiredArgsConstructor
@Service
public class ChassisService {
    private final ChassisRepository chassisRepository;

    public List<ChassisEntity> retrieveAllChassis() {
        return chassisRepository.findAll();
    }

    public ChassisEntity retrieveChassisById(Long id) {
        return chassisRepository.findById(id).orElseThrow(() -> new NoRecordsFetchedException(format("Chassis not found with id: %s", id), format("%s%s/%s", BASE_URL, CHASSIS_URL, id)));
    }

    public List<ChassisEntity> searchChassisByName(String name) {
        if (!hasText(name)) {
            throw new InputFormatException("No Chassis with Blank value or Empty value", format("%s%s/%s", BASE_URL, CHASSIS_URL, name));
        }
        List<ChassisEntity> chassis = chassisRepository.findByName(name);
        if (chassis.isEmpty()) {
            throw new NoRecordsFetchedException(format("Chassis not found with name: %s", name), format("%s%s/%s", BASE_URL, CHASSIS_URL, name));
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
            throw new NoRecordsFetchedException(format("No records to deleted with the id: %s", id), format("%s%s/%s", BASE_URL, CHASSIS_URL, id));
        }
        chassisRepository.deleteById(id);
    }

}