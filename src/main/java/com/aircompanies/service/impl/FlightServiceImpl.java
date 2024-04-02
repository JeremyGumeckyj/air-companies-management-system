package com.aircompanies.service.impl;

import com.aircompanies.model.Flight;
import com.aircompanies.service.FlightService;
import com.aircompanies.FlightStatus;
import com.aircompanies.repository.FlightRepository;
import com.aircompanies.service.util.exception.IllegalArgumentException;
import com.aircompanies.service.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FlightServiceImpl implements FlightService {
    private final FlightRepository flightRepository;

    @Autowired
    public FlightServiceImpl(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @Override
    public List<Flight> getAll() {
        return flightRepository.findAll();
    }

    @Override
    public Flight getById(UUID id) {
        return flightRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Flight not found"));
    }

    @Override
    public Flight createFlight(Flight flight) {
        flight.setId(UUID.randomUUID());
        flight.setFlightStatus(FlightStatus.PENDING);
        flight.setCreatedAt(LocalDateTime.now());
        return flightRepository.save(flight);
    }

    @Override
    public Flight updateFlight(Flight flight) {
        validateIfFlightExists(flight.getId());
        return flightRepository.save(flight);
    }

    @Override
    public void deleteById(UUID id) {
        validateIfFlightExists(id);
        flightRepository.deleteById(id);
    }

    @Override
    public void validateIfFlightExists(UUID id) {
        if (Objects.isNull(id)) {
            throw new IllegalArgumentException("Flight ID can not be null");
        }

        Optional<Flight> flightFromDB = flightRepository.findById(id);
        flightFromDB
                .orElseThrow(() -> new NotFoundException("Flight not found"));
    }

    @Override
    public List<Flight> findFlightsInActiveStatusStartedMoreThan24HoursAgo() {
        List<Flight> allFlights = flightRepository.findAll();
        LocalDateTime twentyFourHoursAgo = LocalDateTime.now().minusHours(24);

        return allFlights.stream()
                .filter(flight -> flight.getFlightStatus() == FlightStatus.ACTIVE)
                .filter(flight -> flight.getStartedAt() != null && flight.getStartedAt().isBefore(twentyFourHoursAgo))
                .collect(Collectors.toList());
    }

    @Override
    public void changeFlightStatus(UUID flightId, FlightStatus newStatus) {
        Flight flight = getById(flightId);
        switch (newStatus) {
            case DELAYED:
                flight.setDelayStartedAt(LocalDateTime.now());
                break;
            case ACTIVE:
                flight.setStartedAt(LocalDateTime.now());
                break;
            case COMPLETED:
                flight.setEndedAt(LocalDateTime.now());
                break;
            default:
                throw new IllegalArgumentException("Invalid status provided");
        }
        flight.setFlightStatus(newStatus);
        updateFlight(flight);
    }


    @Override
    public List<Flight> findFlightsInCompletedStatusWithDelay() {
        return getAll().stream()
                .filter(flight -> flight.getFlightStatus() == FlightStatus.COMPLETED)
                .filter(this::hasDelay)
                .collect(Collectors.toList());
    }

    private boolean hasDelay(Flight flight) {
        LocalDateTime startedAt = flight.getStartedAt();
        LocalDateTime endedAt = flight.getEndedAt();

        if (startedAt == null || endedAt == null) {
            return false;
        }

        Duration difference = Duration.between(startedAt, endedAt);
        Duration estimatedFlightTime = Duration.ofMinutes(flight.getEstimatedFlightTime());
        return difference.compareTo(estimatedFlightTime) > 0;
    }
}
