package main.repository;

import main.model.Airplane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AirplaneRepository extends JpaRepository<Airplane, UUID> {
}
