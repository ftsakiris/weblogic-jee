package com.sample.common;

import com.sample.EntityManagerProvider;
import org.eclipse.persistence.jpa.jpql.Assert;

import javax.inject.Inject;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class AbstractCrudDao {

    private static final String ARGUMENT_OBJECT = "argument.object";

    private static final String ARGUMENT_TYPE = "argument.type";

    private static final String ARGUMENT_FIELDNAME = "argument.fieldName";

    private static final String ARGUMENT_FIELDVALUE = "argument.fieldValue";

    @Inject
    protected EntityManagerProvider entityManagerProvider;

    public <T> void save(String jdbc, T obj) {
        Assert.isNotNull(obj, ARGUMENT_OBJECT);
        entityManagerProvider.getEntityManager(jdbc).persist(obj);
    }

    public <T> T findSingle(String unitName, Class<T> type, String fieldName, Object fieldValue) {
        Assert.isNotNull(type, ARGUMENT_TYPE);
        Assert.isNotNull(fieldName, ARGUMENT_FIELDNAME);
        Assert.isNotNull(fieldValue, ARGUMENT_FIELDVALUE);
        CriteriaBuilder builder = entityManagerProvider.getEntityManager(unitName).getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(type);
        Root<T> e = criteria.from(type);
        Predicate p = builder.equal(e.get(fieldName), fieldValue);
        criteria.where(p);
        TypedQuery<T> query = entityManagerProvider.getEntityManager(unitName).createQuery(criteria);
        List<T> list = query.getResultList();
        return list.isEmpty() ? null : list.get(0);
    }

    public <T> List<T> findList(String unitName, Class<T> type, String fieldName, Object fieldValue) {
        Assert.isNotNull(type, ARGUMENT_TYPE);
        Assert.isNotNull(fieldName, ARGUMENT_FIELDNAME);
        Assert.isNotNull(fieldValue, ARGUMENT_FIELDVALUE);
        CriteriaBuilder builder = entityManagerProvider.getEntityManager(unitName).getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(type);
        Root<T> e = criteria.from(type);
        Predicate p = builder.equal(e.get(fieldName), fieldValue);
        criteria.where(p);
        TypedQuery<T> query = entityManagerProvider.getEntityManager(unitName).createQuery(criteria);
        return query.getResultList();
    }

}