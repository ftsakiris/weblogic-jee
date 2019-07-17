package com.sample.em;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@ApplicationScoped
public class SampleEmProducer2 {

    public static final String ENS_PU = "ENS-pu-2";

    @PersistenceContext(unitName = ENS_PU)
    private EntityManager em;

    @Produces
    @SampleEmQualifier1
    public EntityManager getEntityManager() {
        return em;
    }
}
