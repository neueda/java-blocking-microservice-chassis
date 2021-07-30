package com.neueda.javablockingmicroservicechassis.controller;

import com.neueda.javablockingmicroservicechassis.dto.ChassisDTO;
import com.neueda.javablockingmicroservicechassis.entity.ChassisEntity;
import com.neueda.javablockingmicroservicechassis.service.ChassisService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1")
public class ChassisController {


    private final ChassisService chassisService;

//    public ChassisController(ChassisService chassisService) {
//        this.chassisService = chassisService;
//    }


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
    public ResponseEntity<?> create(@RequestBody ChassisDTO chassisDTO){
        ChassisEntity chassis = chassisService.addChassis(chassisDTO);
        return new ResponseEntity<>(chassis, HttpStatus.CREATED);
    }

    @DeleteMapping({"/chassis/{id}"})
    public ResponseEntity<?> deleteChassis(@PathVariable("id") String id) {
        chassisService.deleteChassis(Long.valueOf(id));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
