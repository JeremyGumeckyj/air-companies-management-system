package com.aircompanies.service.impl;

import com.aircompanies.FlightStatus;
import com.aircompanies.model.AirCompany;
import com.aircompanies.model.Flight;
import com.aircompanies.repository.AirCompanyRepository;
import com.aircompanies.service.AirCompanyService;
import com.aircompanies.service.util.exception.IllegalArgumentException;
import com.aircompanies.service.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AirCompanyServiceImpl implements AirCompanyService {
    private final AirCompanyRepository airCompanyRepository;

    @Autowired
    public AirCompanyServiceImpl(AirCompanyRepository airCompanyRepository) {
        this.airCompanyRepository = airCompanyRepository;
    }

    @Override
    public List<AirCompany> getAll() {
        return airCompanyRepository.findAll();
    }

    @Override
    public AirCompany getById(UUID id) {
        return airCompanyRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("AirCompany not found"));
    }

    @Override
    public AirCompany createAirCompany(AirCompany airCompany) {
        airCompany.setId(UUID.randomUUID());
        return airCompanyRepository.save(airCompany);
    }

    @Override
    public AirCompany updateAirCompany(AirCompany airCompany) {
        validateIfAirCompanyExists(airCompany.getId());
        return airCompanyRepository.save(airCompany);
    }

    @Override
    public void deleteById(UUID id) {
        validateIfAirCompanyExists(id);
        airCompanyRepository.deleteById(id);
    }

    @Override
    public void validateIfAirCompanyExists(UUID id) {
        if (Objects.isNull(id)) {
            throw new IllegalArgumentException("AirCompany ID can not be null");
        }

        Optional<AirCompany> airCompanyFromDB = airCompanyRepository.findById(id);
        airCompanyFromDB
                .orElseThrow(() -> new NotFoundException("AirCompany not found"));
    }

    @Override
    public List<Flight> findFlightsByCompanyAndStatus(String companyName, FlightStatus status) {
        Optional<AirCompany> company = airCompanyRepository.findByName(companyName);
        if (company.isPresent()) {
            return company.get().getFlights().stream()
                    .filter(flight -> flight.getFlightStatus() == status)
                    .collect(Collectors.toList());
        } else {
            return List.of();
        }
    }
}
