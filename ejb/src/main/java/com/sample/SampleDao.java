package com.sample;

import com.sample.common.AbstractCrudDao;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class SampleDao extends AbstractCrudDao {

    public SampleJpo get(String unitName, String mrn) {
        return findSingle(unitName, SampleJpo.class, "mrn", mrn);
    }

    public List<SampleJpo> findList(String unitName, String fieldName, Object fieldValue) {
        return super.findList(unitName, SampleJpo.class, fieldName, fieldValue);
    }
}

