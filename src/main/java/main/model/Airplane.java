package main.model;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Year;
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

    @Column(name = "factorySerialNumber")
    private String factorySerialNumber;

    @Column(name = "airCompanyId")
    private UUID airCompanyId;

    @Column(name = "numberOfFlights")
    private int numberOfFlights;

    @Column(name = "flightDistance")
    private double flightDistance;

    @Column(name = "fuelCapacity")
    private int fuelCapacity;

    @Column(name = "type")
    private String type;

    @Column(name = "createdAt")
    private Year createdAt;

    public Airplane() {
    }

    public Airplane(UUID id, String name, String factorySerialNumber, UUID airCompanyId, int numberOfFlights, double flightDistance, int fuelCapacity, String type, Year createdAt) {
        this.id = id;
        this.name = name;
        this.factorySerialNumber = factorySerialNumber;
        this.airCompanyId = airCompanyId;
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

    public UUID getAirCompanyId() {
        return airCompanyId;
    }

    public void setAirCompanyId(UUID airCompanyId) {
        this.airCompanyId = airCompanyId;
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

    public Year getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Year createdAt) {
        this.createdAt = createdAt;
    }
}
