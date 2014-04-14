package com.noveria.realtimehumanresources.model.address.entities;

import com.noveria.common.entities.BaseEntity;
import com.noveria.realtimehumanresources.model.employee.entities.Employee;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Address extends BaseEntity {

    @Id
    @GeneratedValue
    protected Long id;

    private String houseNumber;
    private String addressFirstLine;
    private String addressSecondLine;
    private String townCity;
    private String postCode;

    @ManyToOne
    private Employee employee;

    private boolean primaryAddress;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public void setAddressFirstLine(String addressFirstLine) {
        this.addressFirstLine = addressFirstLine;
    }

    public void setAddressSecondLine(String addressSecondLine) {
        this.addressSecondLine = addressSecondLine;
    }

    public void setTownCity(String townCity) {
        this.townCity = townCity;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public String getAddressFirstLine() {
        return addressFirstLine;
    }

    public String getAddressSecondLine() {
        return addressSecondLine;
    }

    public String getTownCity() {
        return townCity;
    }

    public String getPostCode() {
        return postCode;
    }

    public boolean isPrimaryAddress() {
        return primaryAddress;
    }

    public void setPrimaryAddress(boolean primaryAddress) {
        this.primaryAddress = primaryAddress;
    }
}
