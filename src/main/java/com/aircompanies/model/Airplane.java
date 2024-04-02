package com.aircompanies.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "airplane")
public class Airplane {

    @Id
    @Column(name = "id")
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "factory_serial_number")
    private String factorySerialNumber;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "air_company_id")
    private AirCompany airCompany;

    @Column(name = "number_of_flights")
    private int numberOfFlights;

    @Column(name = "flight_distance")
    private double flightDistance;

    @Column(name = "fuel_capacity")
    private int fuelCapacity;

    @Column(name = "type")
    private String type;

    @Column(name = "created_at")
    private LocalDate createdAt;

    public Airplane() {
    }

    public Airplane(UUID id, String name, String factorySerialNumber, AirCompany airCompany, int numberOfFlights, double flightDistance, int fuelCapacity, String type, LocalDate createdAt) {
        this.id = id;
        this.name = name;
        this.factorySerialNumber = factorySerialNumber;
        this.airCompany = airCompany;
        this.numberOfFlights = numberOfFlights;
        this.flightDistance = flightDistance;
        this.fuelCapacity = fuelCapacity;
        this.type = type;
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFactorySerialNumber() {
        return factorySerialNumber;
    }

    public void setFactorySerialNumber(String factorySerialNumber) {
        this.factorySerialNumber = factorySerialNumber;
    }

    public AirCompany getAirCompany() {
        return airCompany;
    }

    public void setAirCompany(AirCompany airCompany) {
        this.airCompany = airCompany;
    }

    public int getNumberOfFlights() {
        return numberOfFlights;
    }

    public void setNumberOfFlights(int numberOfFlights) {
        this.numberOfFlights = numberOfFlights;
    }

    public double getFlightDistance() {
        return flightDistance;
    }

    public void setFlightDistance(double flightDistance) {
        this.flightDistance = flightDistance;
    }

    public int getFuelCapacity() {
        return fuelCapacity;
    }

    public void setFuelCapacity(int fuelCapacity) {
        this.fuelCapacity = fuelCapacity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }
}
