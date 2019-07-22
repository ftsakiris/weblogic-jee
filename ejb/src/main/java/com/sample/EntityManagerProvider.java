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

    public EntityManager getEntityManager(String unitName) {
        final EntityManager entityManager = entityManagerMap.get(unitName);
        if (entityManager == null) {
            return initEntityManager(unitName);
        }
        return entityManager;
    }

    private EntityManager initEntityManager(String unitName) {
        final Map props = new HashMap();
//        props.put(PersistenceUnitProperties.JTA_DATASOURCE, "dataSource");
        final EntityManagerFactory emf = Persistence.createEntityManagerFactory(unitName, props);
        final EntityManager em = emf.createEntityManager();
        entityManagerMap.get(unitName);
        return em;
    }
}
