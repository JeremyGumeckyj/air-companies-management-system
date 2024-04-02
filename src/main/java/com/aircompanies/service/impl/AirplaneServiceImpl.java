package com.aircompanies.service.impl;

import com.aircompanies.model.AirCompany;
import com.aircompanies.model.Airplane;
import com.aircompanies.service.AirCompanyService;
import com.aircompanies.repository.AirplaneRepository;
import com.aircompanies.service.AirplaneService;
import com.aircompanies.service.util.exception.IllegalArgumentException;
import com.aircompanies.service.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class AirplaneServiceImpl implements AirplaneService {
    private final AirplaneRepository airplaneRepository;
    private final AirCompanyService airCompanyService;

    @Autowired
    public AirplaneServiceImpl(AirplaneRepository airplaneRepository, AirCompanyService airCompanyService) {
        this.airplaneRepository = airplaneRepository;
        this.airCompanyService = airCompanyService;
    }

    @Override
    public List<Airplane> getAll() {
        return airplaneRepository.findAll();
    }

    @Override
    public Airplane getById(UUID id) {
        return airplaneRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Airplane not found"));
    }

    @Override
    public Airplane createAirplane(Airplane airplane, UUID airCompanyId) {
        airplane.setId(UUID.randomUUID());
        if (airCompanyId != null) {
            AirCompany company = airCompanyService.getById(airCompanyId);
            airplane.setAirCompany(company);
        }
        return airplaneRepository.save(airplane);
    }

    @Override
    public Airplane updateAirplane(Airplane airplane) {
        validateIfAirplaneExists(airplane.getId());
        return airplaneRepository.save(airplane);
    }

    @Override
    public void deleteById(UUID id) {
        validateIfAirplaneExists(id);
        airplaneRepository.deleteById(id);
    }

    @Override
    public void validateIfAirplaneExists(UUID id) {
        if (Objects.isNull(id)) {
            throw new IllegalArgumentException("Airplane ID can not be null");
        }

        Optional<Airplane> airplaneFromDB = airplaneRepository.findById(id);
        airplaneFromDB
                .orElseThrow(() -> new NotFoundException("Airplane not found"));
    }

    @Override
    @Transactional
    public Airplane moveAirplaneToAnotherCompany(UUID airplaneId, UUID newCompanyId) {
        Airplane airplane = getById(airplaneId);
        AirCompany newCompany = airCompanyService.getById(newCompanyId);
        airplane.setAirCompany(newCompany);
        return airplaneRepository.save(airplane);
    }
}
