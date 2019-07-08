package com.sample;

import com.sample.em.SampleEmQualifier1;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.time.LocalDateTime;

@Stateless
public class SampleEjb {

    @Inject
    @SampleEmQualifier1
    private SampleDao sampleDao;

    public void create() {
        sampleDao.save(new SampleJpo("dsds", LocalDateTime.now(), "dssds"));
    }

    public int add(int x, int y) {
        return x + y;
    }
}
