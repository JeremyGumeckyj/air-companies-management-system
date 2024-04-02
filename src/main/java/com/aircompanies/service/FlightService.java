package com.aircompanies.service;

import com.aircompanies.model.Flight;
import com.aircompanies.FlightStatus;

import java.util.List;
import java.util.UUID;

public interface FlightService {
    List<Flight> getAll();

    Flight getById(UUID id);

    Flight createFlight(Flight flight);

    Flight updateFlight(Flight flight);

    void deleteById(UUID id);

    void validateIfFlightExists(UUID id);

    List<Flight> findFlightsInActiveStatusStartedMoreThan24HoursAgo();

    void changeFlightStatus(UUID flightId, FlightStatus newStatus);

    List<Flight> findFlightsInCompletedStatusWithDelay();
}
