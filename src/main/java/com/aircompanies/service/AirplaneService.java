package com.aircompanies.service;

import com.aircompanies.model.Airplane;

import java.util.List;
import java.util.UUID;

public interface AirplaneService {
    List<Airplane> getAll();

    Airplane getById(UUID id);

    Airplane createAirplane(Airplane airplane, UUID airCompanyId);

    Airplane updateAirplane(Airplane airplane);

    void deleteById(UUID id);

    void validateIfAirplaneExists(UUID id);

    Airplane moveAirplaneToAnotherCompany(UUID airplaneId, UUID newCompanyId);
}
