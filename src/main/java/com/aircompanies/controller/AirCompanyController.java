package com.aircompanies.controller;

import com.aircompanies.FlightStatus;
import com.aircompanies.model.AirCompany;
import com.aircompanies.model.Flight;
import com.aircompanies.service.AirCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class AirCompanyController {

    @Autowired
    AirCompanyService airCompanyService;

    @GetMapping("/airCompanies")
    public List<AirCompany> getAll() {
        return airCompanyService.getAll();
    }

    @GetMapping("/airCompanies/{id}")
    public AirCompany getById(@PathVariable UUID id) {
        return airCompanyService.getById(id);
    }

    @PostMapping("/airCompanies")
    public AirCompany createAirCompany(@RequestBody AirCompany airCompany) {
        return airCompanyService.createAirCompany(airCompany);
    }

    @PutMapping("/airCompanies")
    public AirCompany updateAirCompany(@RequestBody AirCompany airCompany) {
        return airCompanyService.updateAirCompany(airCompany);
    }

    @DeleteMapping("/airCompanies/{id}")
    public ResponseEntity deleteById(@PathVariable UUID id) {
        airCompanyService.deleteById(id);
        return ResponseEntity.ok()
                .build();
    }

    @GetMapping("/airCompanies/{companyName}/flights/{status}")
    public List<Flight> findFlightsByCompanyAndStatus(@PathVariable String companyName, @PathVariable FlightStatus status) {
        return airCompanyService.findFlightsByCompanyAndStatus(companyName, status);
    }
}
