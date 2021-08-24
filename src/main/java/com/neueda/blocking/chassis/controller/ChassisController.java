package com.neueda.blocking.chassis.controller;

import com.neueda.blocking.chassis.client.GithubClient;
import com.neueda.blocking.chassis.constants.ChassisConstants;
import com.neueda.blocking.chassis.exception.IdFormatException;
import com.neueda.blocking.chassis.model.Chassis;
import com.neueda.blocking.chassis.entity.ChassisEntity;
import com.neueda.blocking.chassis.exception.ChassisEntityNotFoundException;
import com.neueda.blocking.chassis.service.ChassisService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(ChassisConstants.BASE_URL)
public class ChassisController {

    private final ChassisService chassisService;
    private final GithubClient githubClient;

    @GetMapping(ChassisConstants.CHASSIS_URL)
    public List<ChassisEntity> getAllChassis() {
        return chassisService.retrieveAllChassis();
    }

    @GetMapping(ChassisConstants.CHASSIS_URL + "/{id}")
    public ResponseEntity<ChassisEntity> getChassisById(@PathVariable String id) {
        if (!StringUtils.isNumeric(id)) {
            throw new IdFormatException(ChassisConstants.BASE_URL + ChassisConstants.CHASSIS_URL + "/"+ id + " .Please check the entered Id");
        }
        return new ResponseEntity<>(chassisService.retrieveChassisById(Long.valueOf(id)), HttpStatus.OK);

    }

    @GetMapping("/chassisSearch/{name}")
    public List<ChassisEntity> getChassisByName(@PathVariable String name) {
        return chassisService.searchChassisByName(name);
    }

    @PostMapping(ChassisConstants.CHASSIS_URL)
    @ResponseStatus(HttpStatus.CREATED)
    public ChassisEntity create(@RequestBody Chassis chassis) {
        return chassisService.addChassis(chassis);
    }

    @DeleteMapping({ChassisConstants.CHASSIS_URL + "/{id}"})
    public ResponseEntity<Void> deleteChassis(@PathVariable("id") String id) {
        if(!StringUtils.isNumeric(id)){
            throw new IdFormatException(ChassisConstants.BASE_URL + ChassisConstants.CHASSIS_URL + "/" +id + " .Please check the entered Id");
        }
        chassisService.deleteChassis(Long.valueOf(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping({"chassisClientNameContain", "chassisClientNameContain/{usernamePart}"})
    public String getChassisWebClientResponse(@PathVariable String usernamePart) throws IOException, InterruptedException {

        HttpResponse<String> response = githubClient.searchUsernameContaining(usernamePart);
        return response.body();
    }
}