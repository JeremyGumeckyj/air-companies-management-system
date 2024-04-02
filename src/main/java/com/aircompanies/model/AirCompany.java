package com.aircompanies.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "air_company")
public class AirCompany {

    @Id
    @Column(name = "id")
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "company_type")
    private String companyType;

    @Column(name = "founded_at")
    private LocalDate foundedAt;

    @OneToMany(mappedBy = "airCompany", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Flight> flights = new ArrayList<>();

    public AirCompany() {
    }

    public AirCompany(UUID id, String name, String companyType, LocalDate foundedAt, List<Flight> flights) {
        this.id = id;
        this.name = name;
        this.companyType = companyType;
        this.foundedAt = foundedAt;
        this.flights = flights;
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

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public LocalDate getFoundedAt() {
        return foundedAt;
    }

    public void setFoundedAt(LocalDate foundedAt) {
        this.foundedAt = foundedAt;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }
}
