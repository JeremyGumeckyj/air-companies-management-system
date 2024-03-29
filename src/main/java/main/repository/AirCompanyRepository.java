package main.repository;

import main.model.AirCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AirCompanyRepository extends JpaRepository<AirCompany, UUID> {
}
