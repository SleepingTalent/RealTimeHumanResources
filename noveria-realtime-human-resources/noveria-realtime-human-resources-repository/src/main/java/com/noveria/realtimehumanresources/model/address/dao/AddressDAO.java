package com.noveria.realtimehumanresources.model.address.dao;

import com.noveria.common.dao.BaseDAO;
import com.noveria.realtimehumanresources.model.address.entities.Address;
import com.noveria.realtimehumanresources.model.employee.entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

public class AddressDAO extends BaseDAO<Address> {

    private static final String FIND_EMPLOYEE_MAIN_ADDRESS = "select a from Address a where a.employee = :thisEmployee and a.primaryAddress = true";
    private static final String FIND_EMPLOYEE_ADDRESS_LIST = "select a from Address a where a.employee = :thisEmployee";

    public AddressDAO(EntityManager entityManager) {
        super(Address.class,entityManager);
    }

    public Address getEmployeePrimaryAddress(Employee employee) throws NoResultException {
        Query query = entityManager.createQuery(FIND_EMPLOYEE_MAIN_ADDRESS);
        query.setParameter("thisEmployee",employee);
        return (Address) query.getSingleResult();
    }

    public List<Address> getEmployeeAddressList(Employee employee) {
        Query query = entityManager.createQuery(FIND_EMPLOYEE_ADDRESS_LIST);
        query.setParameter("thisEmployee",employee);
        return query.getResultList();
    }
}
