package com.neueda.blocking.chassis.controller;

import com.neueda.blocking.chassis.client.GithubClient;
import com.neueda.blocking.chassis.entity.ChassisEntity;
import com.neueda.blocking.chassis.exception.InputFormatException;
import com.neueda.blocking.chassis.model.Chassis;
import com.neueda.blocking.chassis.service.ChassisService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.List;

import static com.neueda.blocking.chassis.constants.ChassisConstants.BASE_URL;
import static com.neueda.blocking.chassis.constants.ChassisConstants.CHASSIS_URL;
import static java.lang.String.format;
import static java.lang.String.valueOf;
import static org.apache.commons.lang3.StringUtils.isNumeric;

;

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

    @GetMapping(CHASSIS_URL + "/{id}")
    public ChassisEntity getChassisById(@PathVariable String id) {
        if (!isNumeric(id)) {
            throw new InputFormatException(format("Please check the entered Id : %s", valueOf(id)), format("%s%s/%s", BASE_URL, CHASSIS_URL, valueOf(id)));

        }
        return chassisService.retrieveChassisById(Long.valueOf(id));

    }

    @GetMapping("/chassisSearch/{name}")
    public List<ChassisEntity> getChassisByName(@PathVariable String name) {
        return chassisService.searchChassisByName(name);
    }

    @PostMapping(CHASSIS_URL)
    @ResponseStatus(HttpStatus.CREATED)
    public ChassisEntity create(@RequestBody Chassis chassis) {
        return chassisService.addChassis(chassis);
    }

    @DeleteMapping({CHASSIS_URL + "/{id}"})
    public void deleteChassis(@PathVariable("id") String id) {
        if (!isNumeric(id)) {

            throw new InputFormatException(format("Please check the entered Id :%s", valueOf(id)), format("%s%s/%s", BASE_URL, CHASSIS_URL, valueOf(id)));
        }
        chassisService.deleteChassis(Long.valueOf(id));
    }

    @GetMapping({"chassisClientNameContain", "chassisClientNameContain/{usernamePart}"})
    public String getChassisWebClientResponse(@PathVariable String usernamePart) throws IOException, InterruptedException {

        HttpResponse<String> response = githubClient.searchUsernameContaining(usernamePart);
        return response.body();
    }
}