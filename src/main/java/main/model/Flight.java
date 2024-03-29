package main.model;

import main.FlightStatus;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Table(name = "flight")
public class Flight {

    @Id
    @Column(name = "id")
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "flightStatus")
    private FlightStatus flightStatus;

    @Column(name = "airCompanyId")
    private UUID airCompanyId;

    @Column(name = "airplaneId")
    private UUID airplaneId;

    @Column(name = "departureCountry")
    private String departureCountry;

    @Column(name = "destinationCountry")
    private String destinationCountry;

    @Column(name = "distance")
    private double distance;

    @Column(name = "estimatedFlightTime")
    private int estimatedFlightTime;

    @Column(name = "startedAt")
    private ZonedDateTime startedAt;

    @Column(name = "endedAt")
    private ZonedDateTime endedAt;

    @Column(name = "delayStartedAt")
    private ZonedDateTime delayStartedAt;

    @Column(name = "createdAt")
    private ZonedDateTime createdAt;

    public Flight() {
    }

    public Flight(UUID id, FlightStatus flightStatus, UUID airCompanyId, UUID airplaneId, String departureCountry, String destinationCountry, double distance, int estimatedFlightTime, ZonedDateTime startedAt, ZonedDateTime endedAt, ZonedDateTime delayStartedAt, ZonedDateTime createdAt) {
        this.id = id;
        this.flightStatus = flightStatus;
        this.airCompanyId = airCompanyId;
        this.airplaneId = airplaneId;
        this.departureCountry = departureCountry;
        this.destinationCountry = destinationCountry;
        this.distance = distance;
        this.estimatedFlightTime = estimatedFlightTime;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
        this.delayStartedAt = delayStartedAt;
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public FlightStatus getFlightStatus() {
        return flightStatus;
    }

    public void setFlightStatus(FlightStatus flightStatus) {
        this.flightStatus = flightStatus;
    }

    public UUID getAirCompanyId() {
        return airCompanyId;
    }

    public void setAirCompanyId(UUID airCompanyId) {
        this.airCompanyId = airCompanyId;
    }

    public UUID getAirplaneId() {
        return airplaneId;
    }

    public void setAirplaneId(UUID airplaneId) {
        this.airplaneId = airplaneId;
    }

    public String getDepartureCountry() {
        return departureCountry;
    }

    public void setDepartureCountry(String departureCountry) {
        this.departureCountry = departureCountry;
    }

    public String getDestinationCountry() {
        return destinationCountry;
    }

    public void setDestinationCountry(String destinationCountry) {
        this.destinationCountry = destinationCountry;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getEstimatedFlightTime() {
        return estimatedFlightTime;
    }

    public void setEstimatedFlightTime(int estimatedFlightTime) {
        this.estimatedFlightTime = estimatedFlightTime;
    }

    public ZonedDateTime getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(ZonedDateTime startedAt) {
        this.startedAt = startedAt;
    }

    public ZonedDateTime getEndedAt() {
        return endedAt;
    }

    public void setEndedAt(ZonedDateTime endedAt) {
        this.endedAt = endedAt;
    }

    public ZonedDateTime getDelayStartedAt() {
        return delayStartedAt;
    }

    public void setDelayStartedAt(ZonedDateTime delayStartedAt) {
        this.delayStartedAt = delayStartedAt;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
