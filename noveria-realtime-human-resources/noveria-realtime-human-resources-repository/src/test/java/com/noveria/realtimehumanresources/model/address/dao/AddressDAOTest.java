package com.noveria.realtimehumanresources.model.address.dao;

import com.noveria.common.BaseUnitTest;
import com.noveria.realtimehumanresources.model.address.entities.Address;
import com.noveria.realtimehumanresources.model.employee.entities.Employee;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

public class AddressDAOTest extends BaseUnitTest {

    public static final String COUNT_QUERY = "SELECT count(o) from Address o ";
    public static final String EMPLOYEE_PRIMARY_ADDRESS_QUERY = "select a from Address a where a.employee = :thisEmployee and a.primaryAddress = true";
    public static final String FIND_EMPLOYEE_ADDRESS_LIST = "select a from Address a where a.employee = :thisEmployee";

    @InjectMocks
    AddressDAO addressDAO;

    @Mock
    EntityManager entityManager;

    @Mock
    Query countQuery;

    @Mock
    Query addressQuery;

    Long expectedCount;

    Address address;

    Employee employee;

    Long addressId = 1234l;

    Long employeeId = 5678l;

    @Before
    public void setUp() {
        expectedCount = new Long(1234l);

        address = new Address();
        address.setId(addressId);

        employee = new Employee();
        employee.setId(employeeId);

        address.setEmployee(employee);

        when(entityManager.createQuery(COUNT_QUERY)).thenReturn(countQuery);
        when(countQuery.getSingleResult()).thenReturn(expectedCount);


        when(entityManager.createQuery(EMPLOYEE_PRIMARY_ADDRESS_QUERY)).thenReturn(addressQuery);
        when(addressQuery.getSingleResult()).thenReturn(address);

        List<Address> addressList = new ArrayList<Address>();
        addressList.add(address);

        when(entityManager.createQuery(FIND_EMPLOYEE_ADDRESS_LIST)).thenReturn(addressQuery);
        when(addressQuery.getResultList()).thenReturn(addressList);

        when(entityManager.find(any(Class.class), anyLong())).thenReturn(address);
    }

    @Test
    public void countAll_uses_expected_query() {
        Long actualCount = addressDAO.countAll();

        Assert.assertEquals(expectedCount, actualCount);

        verify(entityManager, times(1)).createQuery(eq(COUNT_QUERY));
        verify(countQuery,times(1)).getSingleResult();
    }

    @Test
    public void create_persits_expected_address() {
        addressDAO.create(address);

        verify(entityManager, times(1)).persist(eq(address));
    }

    @Test
    public void delete_removes_expected_address() {
        addressDAO.delete(address);

        verify(entityManager, times(1)).merge(eq(address));
        verify(entityManager, times(1)).remove(Matchers.<Address>anyObject());
    }

    @Test
    public void delete_merges_expected_address() {
        addressDAO.update(address);

        verify(entityManager, times(1)).merge(eq(address));
    }

    @Test
    public void findById_looksfor_expected_address() {
        Long addressId = 1234l;

        Address actualAddress = addressDAO.findById(addressId);

        Assert.assertEquals(address.getId(), actualAddress.getId());
        Assert.assertEquals(employee.getId(), actualAddress.getEmployee().getId());

        verify(entityManager, times(1)).find(any(Class.class),eq(addressId));
    }


    @Test
    public void getEmployeePrimaryAddress_returns_expected_employee() {
        Address actualAddress = addressDAO.getEmployeePrimaryAddress(new Employee());
        Assert.assertEquals(address.getId(), actualAddress.getId());

        verify(entityManager, times(1)).createQuery(EMPLOYEE_PRIMARY_ADDRESS_QUERY);
        verify(addressQuery,times(1)).getSingleResult();
    }


    @Test
    public void getEmployeeAddressList_returns_expected_employee() {
        List<Address> addressList = addressDAO.getEmployeeAddressList(new Employee());
        Assert.assertEquals(1, addressList.size());
        Assert.assertEquals(address.getId(), addressList.get(0).getId());

        verify(entityManager, times(1)).createQuery(FIND_EMPLOYEE_ADDRESS_LIST);
        verify(addressQuery,times(1)).getResultList();
    }
}
