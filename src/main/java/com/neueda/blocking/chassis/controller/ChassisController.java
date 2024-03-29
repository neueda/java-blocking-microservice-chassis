package com.neueda.blocking.chassis.controller;

import com.neueda.blocking.chassis.client.GithubClient;
import com.neueda.blocking.chassis.entity.ChassisEntity;
import com.neueda.blocking.chassis.exception.InputFormatException;
import com.neueda.blocking.chassis.model.Chassis;
import com.neueda.blocking.chassis.service.ChassisService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.neueda.blocking.chassis.constants.ChassisConstants.BASE_URL;
import static com.neueda.blocking.chassis.constants.ChassisConstants.CHASSIS_SEARCH_BY_NAME;
import static com.neueda.blocking.chassis.constants.ChassisConstants.CHASSIS_URL;
import static com.neueda.blocking.chassis.constants.ChassisConstants.CHASSIS_URL_WITH_ID;
import static com.neueda.blocking.chassis.constants.ChassisConstants.CLIENT_URL;
import static java.lang.String.format;
import static org.apache.commons.lang3.StringUtils.isNumeric;

@RequiredArgsConstructor
@RestController
@RequestMapping(BASE_URL)
public class ChassisController {

    private final ChassisService chassisService;
    private final GithubClient githubClient;

    @GetMapping(CHASSIS_URL)
    public List<ChassisEntity> getAllChassis() {
        return chassisService.retrieveAllChassis();
    }

    @GetMapping(CHASSIS_URL_WITH_ID)
    public ChassisEntity getChassisById(@PathVariable String id) {
        if (!isNumeric(id)) {
            throw new InputFormatException(format("Please check the entered Id : %s", id), format("%s%s", BASE_URL, CHASSIS_URL_WITH_ID));
        }
        return chassisService.retrieveChassisById(Long.valueOf(id));
    }

    @GetMapping(CHASSIS_SEARCH_BY_NAME)
    public List<ChassisEntity> getChassisByName(@PathVariable String name) {
        return chassisService.searchChassisByName(name);
    }

    @PostMapping(CHASSIS_URL)
    @ResponseStatus(HttpStatus.CREATED)
    public ChassisEntity create(@RequestBody Chassis chassis) {
        return chassisService.addChassis(chassis);
    }

    @DeleteMapping({CHASSIS_URL_WITH_ID})
    public void deleteChassis(@PathVariable("id") String id) {
        if (!isNumeric(id)) {
            throw new InputFormatException(format("Please check the entered Id :%s", id), format("%s%s", BASE_URL, CHASSIS_URL_WITH_ID));
        }
        chassisService.deleteChassis(Long.valueOf(id));
    }

    @GetMapping(CLIENT_URL)
    public String getChassisWebClientResponse(@PathVariable String usernamePart) {
        return githubClient.searchUsernameContaining(usernamePart);
    }

}