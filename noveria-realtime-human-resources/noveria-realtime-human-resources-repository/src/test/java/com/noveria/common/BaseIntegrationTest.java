package com.noveria.common;

import com.noveria.common.groups.IntegrationTest;
import com.noveria.helper.EntityManagerHelper;
import com.noveria.helper.PersitenceHelper;
import com.noveria.realtimehumanresources.model.address.helper.AddressHelper;
import org.junit.experimental.categories.Category;

import javax.persistence.EntityManager;

@Category(IntegrationTest.class)
public class BaseIntegrationTest {

    private static EntityManager entityManager = EntityManagerHelper.getEntityManagerFactory().createEntityManager();

    protected PersitenceHelper persitenceHelper;

    public BaseIntegrationTest()  {
         persitenceHelper = new PersitenceHelper(entityManager);
    }

    protected void beginTransaction() {
        persitenceHelper.beginTransaction();
    }

    protected void endTransaction()  {
        persitenceHelper.rollbackTransaction();
    }

    protected EntityManager getEntityManager() {
        return entityManager;
    }

    protected AddressHelper getAddressHelper() {
        return new AddressHelper();
    }
}
