package main.service.impl;

import main.model.AirCompany;
import main.repository.AirCompanyRepository;
import main.service.AirCompanyService;
import main.service.util.exception.IllegalArgumentException;
import main.service.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

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
}
