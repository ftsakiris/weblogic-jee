package com.sample;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.time.LocalDateTime;

@Stateless
public class SampleEjb {

    @Inject
    private SampleDao sampleDao;

    public void create(String jdbc) {
        sampleDao.save(jdbc, new SampleJpo("dsds", LocalDateTime.now(), "dssds"));
    }

    public void create() {
        create("jdbc/ENSDS");
    }

    public int add(int x, int y) {
        return x + y;
    }
}
