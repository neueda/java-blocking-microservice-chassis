package com.neueda.javablockingmicroservicechassis.controller;

import com.neueda.javablockingmicroservicechassis.entity.ChassisEntity;
import com.neueda.javablockingmicroservicechassis.service.ChassisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<?> getChassisById(@PathVariable String id){
        ChassisEntity chassis = chassisService.retriveChassisById(Long.valueOf(id));
        return new ResponseEntity<>(chassis, HttpStatus.OK);
    }

    @GetMapping("/chassisSearch")
    public ResponseEntity<?> getChassisByName(@RequestParam String name){
        List<ChassisEntity> chassisEntities = chassisService.searchChassisByName(name);
        if(chassisEntities.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(chassisEntities, HttpStatus.OK);
    }

    @PostMapping("/chassis")
    public ResponseEntity<?> create(@RequestBody ChassisEntity chassisEntity){
        ChassisEntity chassis = chassisService.addChassis(chassisEntity);
        return new ResponseEntity<>(chassis, HttpStatus.CREATED);
    }

    @DeleteMapping({"/chassis/{id}"})
    public ResponseEntity<?> deleteTodo(@PathVariable("id") String id) {
        chassisService.deleteChassis(Long.valueOf(id));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
