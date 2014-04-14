package com.noveria.helper;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerHelper {

    public static EntityManagerFactory getEntityManagerFactory() {
        return Persistence.createEntityManagerFactory("integration-test");
    }
}
