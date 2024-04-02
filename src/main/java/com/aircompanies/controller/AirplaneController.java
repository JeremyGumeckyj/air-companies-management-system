package com.aircompanies.controller;

import com.aircompanies.model.Airplane;
import com.aircompanies.service.AirplaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class AirplaneController {

    @Autowired
    AirplaneService airplaneService;

    @GetMapping("/airplanes")
    public List<Airplane> getAll() {
        return airplaneService.getAll();
    }

    @GetMapping("/airplanes/{id}")
    public Airplane getById(@PathVariable UUID id){
        return airplaneService.getById(id);
    }

    @PostMapping("/airplanes")
    public Airplane createAirplane(@RequestBody Airplane airplane, @RequestParam(required = false) UUID airCompanyId) {
        return airplaneService.createAirplane(airplane, airCompanyId);
    }

    @PutMapping("/airplanes")
    public Airplane updateAirplane(@RequestBody Airplane airplane){
        return airplaneService.updateAirplane(airplane);
    }

    @DeleteMapping("/airplanes/{id}")
    public ResponseEntity deleteById(@PathVariable UUID id){
        airplaneService.deleteById(id);
        return ResponseEntity.ok()
                .build();
    }

    @PostMapping("/airplanes/{airplaneId}/move/{newCompanyId}")
    public Airplane moveAirplaneToAnotherCompany(@PathVariable UUID airplaneId, @PathVariable UUID newCompanyId) {
        return airplaneService.moveAirplaneToAnotherCompany(airplaneId, newCompanyId);
    }
}
