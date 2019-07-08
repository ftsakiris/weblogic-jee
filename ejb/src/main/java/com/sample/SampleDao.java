package com.sample;

import com.sample.common.AbstractCrudDao;
import com.sample.em.SampleEmQualifier1;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@SampleEmQualifier1
public class SampleDao extends AbstractCrudDao {

    @Inject
    public SampleDao(@SampleEmQualifier1 EntityManager em) {
        super(em);
    }

    public SampleJpo get(String mrn) {
        return findSingle(SampleJpo.class, "mrn", mrn);
    }

    public List<SampleJpo> findList(String fieldName, Object fieldValue) {
        return super.findList(SampleJpo.class, fieldName, fieldValue);
    }
}

