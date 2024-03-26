package model;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Year;
import java.util.UUID;

@Entity
@Table(name = "airCompany")
public class AirCompany {

    @Id
    @Column(name = "id")
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "companyType")
    private String companyType;

    @Column(name = "foundedAt")
    private Year foundedAt;

    public AirCompany() {
    }

    public AirCompany(UUID id, String name, String companyType, Year foundedAt) {
        this.id = id;
        this.name = name;
        this.companyType = companyType;
        this.foundedAt = foundedAt;
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

    public Year getFoundedAt() {
        return foundedAt;
    }

    public void setFoundedAt(Year foundedAt) {
        this.foundedAt = foundedAt;
    }
}
