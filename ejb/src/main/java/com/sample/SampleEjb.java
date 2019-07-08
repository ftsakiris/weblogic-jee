package com.sample;

import com.sample.em.SampleEmQualifier1;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.time.LocalDateTime;

@Stateless
public class SampleEjb {

//    @Inject
//    @SampleEmQualifier1
//    private SampleDao sampleDao;

    @Inject
    @SampleEmQualifier1
    EntityManager em;

//    public void create() {
//        sampleDao.save(new SampleJpo("dsds", LocalDateTime.now(), "dssds"));
//    }

    public void create() {
        em.persist(new SampleJpo("dsds", LocalDateTime.now(), "dssds"));
    }

    public int add(int x, int y) {
        return x + y;
    }
}
