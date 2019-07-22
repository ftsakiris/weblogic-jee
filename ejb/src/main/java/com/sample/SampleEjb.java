package com.sample;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.time.LocalDateTime;

@Stateless
public class SampleEjb {

    @Inject
    private SampleDao sampleDao;

    public void create(String unitName) {
        sampleDao.save(unitName, new SampleJpo("dsds", LocalDateTime.now(), "dssds"));
    }

    public void create() {
        create("ENS-pu-1");
    }

    public int add(int x, int y) {
        return x + y;
    }
}
