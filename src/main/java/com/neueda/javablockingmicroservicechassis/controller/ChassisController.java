package com.neueda.javablockingmicroservicechassis.controller;

import com.neueda.javablockingmicroservicechassis.entity.ChassisEntity;
import com.neueda.javablockingmicroservicechassis.service.ChassisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1")
public class ChassisController {

    @Autowired
    private ChassisService chassisService;

    @GetMapping("/chassis")
    public ResponseEntity<?> getAllChassis(){
        List<ChassisEntity> chassisEntities = chassisService.retrieveAllChassis();
        if(chassisEntities.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(chassisEntities, HttpStatus.OK);
    }

    @GetMapping("/chassis/{id}")
    public Optional<ChassisEntity> getChassisById(@PathVariable String id){
        return chassisService.retriveChassisById(Long.valueOf(id));
    }

    @GetMapping("/chassisSearch")
    public List<ChassisEntity> getChassisByName(@RequestParam String name){
        return chassisService.searchChassisByName(name);
    }

    @PostMapping("/chassis")
    @ResponseStatus(HttpStatus.CREATED)
    public ChassisEntity create(@RequestBody ChassisEntity chassisEntity){
        return chassisService.addChassis(chassisEntity);
    }

}
