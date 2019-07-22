package com.sample;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Path("sample")
public class RestSample {

    @Inject
    private SampleEjb sampleEjb;

    @GET
    public String create(@QueryParam("unitName") String unitName) {
        sampleEjb.create(unitName);
        return unitName;
    }
}
