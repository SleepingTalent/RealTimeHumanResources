package com.noveria.realtimehumanresources.model.employee.entities;

import com.noveria.common.entities.BaseEntity;
import com.noveria.realtimehumanresources.model.address.entities.Address;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Employee extends BaseEntity {

    @Id
    @GeneratedValue
    protected Long id;

    private String firstName;
    private String lastName;
    private Date dateOfBirth;

    @OneToMany(mappedBy="employee",cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Address> addressList;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setAddressList(List<Address> address) {
        this.addressList = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public List<Address> getAddressList() {
        return addressList;
    }
}
