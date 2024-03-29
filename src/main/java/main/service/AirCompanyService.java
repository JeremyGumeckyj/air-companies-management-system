package main.service;

import main.model.AirCompany;

import java.util.List;
import java.util.UUID;

public interface AirCompanyService {
    List<AirCompany> getAll();

    AirCompany getById(UUID id);

    AirCompany createAirCompany(AirCompany airCompany);

    AirCompany updateAirCompany(AirCompany airCompany);

    void deleteById(UUID id);

    void validateIfAirCompanyExists(UUID id);
}
