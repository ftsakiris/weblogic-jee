package com.sample;

import org.eclipse.persistence.config.PersistenceUnitProperties;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

@Singleton
public class EntityManagerProvider {

    private Map<String, EntityManager> entityManagerMap;

    @PostConstruct
    public void init() {
        entityManagerMap = new HashMap<>();
    }

    public EntityManager getEntityManager(String jdbc) {
        final EntityManager entityManager = entityManagerMap.get(jdbc);
        if (entityManager == null) {
            final EntityManager newEntityManager = initEntityManager(jdbc);
            entityManagerMap.put(jdbc, newEntityManager);
            return newEntityManager;
        }
        return entityManager;
    }

    private EntityManager initEntityManager(String jdbc) {
        final Map props = new HashMap();
        props.put(PersistenceUnitProperties.JTA_DATASOURCE, jdbc);
        final EntityManagerFactory emf = Persistence.createEntityManagerFactory("ENS-pu-1", props);
        final EntityManager em = emf.createEntityManager();
        entityManagerMap.get(jdbc);
        return em;
    }
}
