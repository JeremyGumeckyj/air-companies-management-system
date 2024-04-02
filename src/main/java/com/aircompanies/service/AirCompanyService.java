package com.aircompanies.service;

import com.aircompanies.model.AirCompany;
import com.aircompanies.model.Flight;
import com.aircompanies.FlightStatus;

import java.util.List;
import java.util.UUID;

public interface AirCompanyService {
    List<AirCompany> getAll();

    AirCompany getById(UUID id);

    AirCompany createAirCompany(AirCompany airCompany);

    AirCompany updateAirCompany(AirCompany airCompany);

    void deleteById(UUID id);

    void validateIfAirCompanyExists(UUID id);

    List<Flight> findFlightsByCompanyAndStatus(String companyName, FlightStatus status);
}
