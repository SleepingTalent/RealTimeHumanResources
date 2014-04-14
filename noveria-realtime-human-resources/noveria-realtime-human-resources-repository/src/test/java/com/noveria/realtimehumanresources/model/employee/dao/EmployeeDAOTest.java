package com.noveria.realtimehumanresources.model.employee.dao;

import com.noveria.common.BaseUnitTest;
import com.noveria.realtimehumanresources.model.employee.entities.Employee;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

public class EmployeeDAOTest extends BaseUnitTest {

    public static final String COUNT_QUERY = "SELECT count(o) from Employee o ";
    public static final String EMPLOYEE_ID_QUERY = "select e from Employee e where e.staffNumber = :thisStaffNumber";

    @InjectMocks
    EmployeeDAO employeeDAO;

    @Mock
    EntityManager entityManager;

    @Mock
    Query countQuery;

    @Mock
    Query employeeIdQuery;

    Long expectedCount;

    Employee employee;

    @Before
    public void setUp() {
        expectedCount = new Long(10l);
        employee = new Employee();
        employee.setId(1234l);

        when(entityManager.createQuery(eq(COUNT_QUERY))).thenReturn(countQuery);
        when(countQuery.getSingleResult()).thenReturn(expectedCount);

        when(entityManager.find(eq(Employee.class),eq(employee.getId()))).thenReturn(employee);
    }

    @Test
    public void countAll_uses_expected_query() {
        Long actualCount = employeeDAO.countAll();

        Assert.assertEquals(expectedCount, actualCount);

        verify(entityManager, times(1)).createQuery(eq(COUNT_QUERY));
        verify(countQuery,times(1)).getSingleResult();
    }

    @Test
    public void create_persits_expected_employee() {
        Employee employee = new Employee();
        employeeDAO.create(employee);

        verify(entityManager, times(1)).persist(eq(employee));
    }

    @Test
    public void delete_removes_expected_employee() {
        Employee employee = new Employee();

        employeeDAO.delete(employee);

        verify(entityManager, times(1)).merge(eq(employee));
        verify(entityManager, times(1)).remove(Matchers.<Employee>anyObject());
    }

    @Test
    public void delete_merges_expected_employee() {
        Employee employee = new Employee();

        employeeDAO.update(employee);

        verify(entityManager, times(1)).merge(eq(employee));
    }

    @Test
    public void findById_looksfor_expected_employee() {
        Long employeeId = 1234l;

        when(entityManager.find(any(Class.class),eq(employeeId))).thenReturn(new Employee());

        employeeDAO.findById(employeeId);

        verify(entityManager, times(1)).find(any(Class.class),eq(employeeId));
    }

    @Test(expected = NoResultException.class)
    public void findById_throws_noResultsException_whenEmployeeNotFound() {
        Long employeeId = 4567l;
        employeeDAO.findById(employeeId);
    }

    @Test
    public void getEmployeeDetails_returns_expected_employee() {
        Long staffNumber = 1234l;

        Employee actualEmployee = employeeDAO.getEmployeeDetails(staffNumber);
        Assert.assertEquals(employee.getId(), actualEmployee.getId());

        verify(entityManager, times(1)).find(eq(Employee.class),eq(employee.getId()));
    }

}
