package com.aircompanies.repository;

import com.aircompanies.model.AirCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AirCompanyRepository extends JpaRepository<AirCompany, UUID> {
    Optional<AirCompany> findByName(String name);
}
