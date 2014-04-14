package com.noveria.realtimehumanresources.model.address.dao;

import com.noveria.common.BaseIntegrationTest;
import com.noveria.realtimehumanresources.model.address.entities.Address;
import com.noveria.realtimehumanresources.model.employee.entities.Employee;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.NoResultException;
import java.util.Date;
import java.util.List;

public class AddressDAOIntegrationTest extends BaseIntegrationTest {

    AddressDAO addressDAO;

    Employee employee;

    Address mainAddress;

    Address oldAddress;

    @Before
    public void setUp() {
        addressDAO = new AddressDAO(getEntityManager());

        employee = new Employee();
        employee.setFirstName("Dave");
        employee.setLastName("Smith");
        employee.setDateOfBirth(new Date());

        mainAddress = new Address();
        mainAddress.setHouseNumber("20");
        mainAddress.setAddressFirstLine("Long Acre");
        mainAddress.setAddressSecondLine("Sandgrove Park");
        mainAddress.setTownCity("Meanwhile City");
        mainAddress.setPostCode("AB1 CD4");
        mainAddress.setPrimaryAddress(true);

        oldAddress = new Address();
        oldAddress.setHouseNumber("14");
        oldAddress.setAddressFirstLine("Chapel Level");
        oldAddress.setAddressSecondLine("Hyde Park");
        oldAddress.setTownCity("Lost City");
        oldAddress.setPostCode("EF2 HK5");
        oldAddress.setPrimaryAddress(false);


        beginTransaction();
        employee = persitenceHelper.persistNewEmployee(employee);
        mainAddress = persitenceHelper.persistNewAddress(mainAddress,employee);
        oldAddress = persitenceHelper.persistNewAddress(oldAddress, employee);
    }

    @After
    public void tearDown() {
        endTransaction();
    }

    @Test
    public void create_createsAddress_AsExpected() {
        Address newAddress = new Address();
        newAddress.setHouseNumber("20");
        newAddress.setAddressFirstLine("Long Acre");
        newAddress.setAddressSecondLine("Sandgrove Park");
        newAddress.setTownCity("Meanwhile City");
        newAddress.setPostCode("AB1 CD4");
        newAddress.setPrimaryAddress(false);
        newAddress.setEmployee(employee);

        Assert.assertNull("Expected Id to be Null!", newAddress.getId());

        addressDAO.create(newAddress);

        Assert.assertNotNull("Expected Id to be populated!",newAddress.getId());
    }

    @Test(expected = NoResultException.class)
    public void delete_removesEmployee_AsExpected(){
        addressDAO.delete(mainAddress);
        addressDAO.findById(mainAddress.getId());
    }

    @Test
    public void countAll_returns_AsExpected() {
        Assert.assertEquals(2, addressDAO.countAll());
    }

    @Test
    public void update_updatesAddress_AsExpected() {
        mainAddress.setAddressFirstLine("modified");

        addressDAO.update(mainAddress);

        Address actual = addressDAO.findById(mainAddress.getId());

        Assert.assertEquals(mainAddress.getId(),actual.getId());
        Assert.assertEquals(mainAddress.getHouseNumber(),actual.getHouseNumber());
        Assert.assertEquals("modified",actual.getAddressFirstLine());
        Assert.assertEquals(mainAddress.getAddressSecondLine(),actual.getAddressSecondLine());
        Assert.assertEquals(mainAddress.getTownCity(),actual.getTownCity());
        Assert.assertEquals(mainAddress.getPostCode(),actual.getPostCode());
    }

    @Test
    public void findById_returns_AsExpected() {
        Address actual = addressDAO.findById(mainAddress.getId());

        Assert.assertEquals(mainAddress.getId(),actual.getId());
        Assert.assertEquals(mainAddress.getHouseNumber(),actual.getHouseNumber());
        Assert.assertEquals(mainAddress.getAddressFirstLine(),actual.getAddressFirstLine());
        Assert.assertEquals(mainAddress.getAddressSecondLine(),actual.getAddressSecondLine());
        Assert.assertEquals(mainAddress.getTownCity(),actual.getTownCity());
        Assert.assertEquals(mainAddress.getPostCode(),actual.getPostCode());
    }

    @Test
    public void getEmployeePrimaryAddress_returns_AsExpected() {
        Address actual = addressDAO.getEmployeePrimaryAddress(employee);

        Assert.assertEquals(mainAddress.getId(),actual.getId());
        Assert.assertEquals(mainAddress.getHouseNumber(),actual.getHouseNumber());
        Assert.assertEquals(mainAddress.getAddressFirstLine(),actual.getAddressFirstLine());
        Assert.assertEquals(mainAddress.getAddressSecondLine(),actual.getAddressSecondLine());
        Assert.assertEquals(mainAddress.getTownCity(),actual.getTownCity());
        Assert.assertEquals(mainAddress.getPostCode(),actual.getPostCode());
    }

    @Test
    public void getEmployeeAddressList_returns_AsExpected() {
        List<Address> actual = addressDAO.getEmployeeAddressList(employee);
        Assert.assertEquals(2,actual.size());

    }



}

