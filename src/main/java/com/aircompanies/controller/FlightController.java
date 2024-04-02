package com.aircompanies.controller;

import com.aircompanies.model.Flight;
import com.aircompanies.service.FlightService;
import com.aircompanies.FlightStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class FlightController {
    @Autowired
    FlightService flightService;

    @GetMapping("/flights")
    public List<Flight> getAll() {
        return flightService.getAll();
    }

    @GetMapping("/flights/{id}")
    public Flight getById(@PathVariable UUID id){
        return flightService.getById(id);
    }

    @PostMapping("/flights")
    public Flight createFlight(@RequestBody Flight flight) {
        return flightService.createFlight(flight);
    }

    @PutMapping("/flights")
    public Flight updateFlight(@RequestBody Flight flight){
        return flightService.updateFlight(flight);
    }

    @DeleteMapping("/flights/{id}")
    public ResponseEntity deleteById(@PathVariable UUID id){
        flightService.deleteById(id);
        return ResponseEntity.ok()
                .build();
    }

    @PostMapping("/flights/{flightId}/status/{newStatus}")
    public void changeFlightStatus(@PathVariable UUID flightId, @PathVariable FlightStatus newStatus) {
        flightService.changeFlightStatus(flightId, newStatus);
    }

    @GetMapping("/flights/active24")
    public List<Flight> getActiveFlightsStartedMoreThan24HoursAgo() {
        return flightService.findFlightsInActiveStatusStartedMoreThan24HoursAgo();
    }

    @GetMapping("/flights/completedWithDelay")
    public List<Flight> getCompletedFlightsWithDelay() {
        return flightService.findFlightsInCompletedStatusWithDelay();
    }
}
