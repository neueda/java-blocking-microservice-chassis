package com.neueda.blocking.chassis.controller;

import com.neueda.blocking.chassis.model.Chassis;
import com.neueda.blocking.chassis.entity.ChassisEntity;
import com.neueda.blocking.chassis.exception.ChassisEntityNotFoundException;
import com.neueda.blocking.chassis.service.ChassisService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.List;
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1")
public class ChassisController {

    private final ChassisService chassisService;

    @GetMapping("/chassis")
    public List<ChassisEntity> getAllChassis()
    {
        return chassisService.retrieveAllChassis();
    }

    @GetMapping("/chassis/{id}")
    public ChassisEntity getChassisById(@PathVariable String id) throws ChassisEntityNotFoundException
    {
        return chassisService.retrieveChassisById(Long.valueOf(id));
    }

    @GetMapping("/chassisSearch/{name}")
    public List<ChassisEntity> getChassisByName(@PathVariable String name) throws ChassisEntityNotFoundException
    {
        return chassisService.searchChassisByName(name);
    }

    @PostMapping("/chassis")
    @ResponseStatus(HttpStatus.CREATED)
    public ChassisEntity create(@RequestBody Chassis chassis)
    {
        return chassisService.addChassis(chassis);
    }

    @DeleteMapping({"/chassis/{id}"})
    public void deleteChassis(@PathVariable("id") String id) throws ChassisEntityNotFoundException
    {
        chassisService.deleteChassis(Long.valueOf(id));
    }
}