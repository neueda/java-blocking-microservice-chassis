package com.neueda.blocking.chassis.controller;

import com.neueda.blocking.chassis.client.GithubClient;
import com.neueda.blocking.chassis.constants.ChassisConstants;
import com.neueda.blocking.chassis.exception.IdFormatException;
import com.neueda.blocking.chassis.model.Chassis;
import com.neueda.blocking.chassis.entity.ChassisEntity;
import com.neueda.blocking.chassis.exception.ChassisEntityNotFoundException;
import com.neueda.blocking.chassis.service.ChassisService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
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

    @GetMapping(ChassisConstants.CHASSIS_URL+"/{id}")
    public ChassisEntity getChassisById(@PathVariable String id) {
        try{
        return chassisService.retrieveChassisById(Long.valueOf(id));
        }
        catch(NumberFormatException ex) {
            throw new IdFormatException(ChassisConstants.CHASSIS_URL + id, ex);
        }
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

    @DeleteMapping({"/chassis/{id}"})
    public void deleteChassis(@PathVariable("id") String id) throws ChassisEntityNotFoundException {
        try {
            chassisService.deleteChassis(Long.valueOf(id));
        }
        catch(NumberFormatException ex) {
            throw new IdFormatException("/v1/chassis/" + id, ex);
        }
    }

    @GetMapping({"chassisClientNameContain", "chassisClientNameContain/{usernamePart}"})
    public String getChassisWebClientResponse(@PathVariable String usernamePart) throws IOException, InterruptedException {

        HttpResponse<String> response = githubClient.searchUsernameContaining(usernamePart);
        return response.body();
    }
}