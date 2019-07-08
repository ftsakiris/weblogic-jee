package com.sample.common;

import org.eclipse.persistence.jpa.jpql.Assert;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

public class AbstractCrudDao {

    protected EntityManager em;

    private static final String ARGUMENT_OBJECT = "argument.object";

    private static final String ARGUMENT_TYPE = "argument.type";

    private static final String ARGUMENT_FIELDNAME = "argument.fieldName";

    private static final String ARGUMENT_FIELDVALUE = "argument.fieldValue";

    public AbstractCrudDao(EntityManager em) {
        this.em = em;
    }

    public <T> void save(T obj) {
        Assert.isNotNull(obj, ARGUMENT_OBJECT);
        this.em.persist(obj);
    }

    public <T> T merge(T obj) {
        Assert.isNotNull(obj, ARGUMENT_OBJECT);
        return this.em.merge(obj);
    }

    public <T> void saveNow(T obj) {
        Assert.isNotNull(obj, ARGUMENT_OBJECT);
        this.em.persist(obj);
        this.em.flush();
        this.em.refresh(obj);
    }

    public <T> void mergeNow(T obj) {
        Assert.isNotNull(obj, ARGUMENT_OBJECT);
        this.em.merge(obj);
        this.em.flush();
        this.em.refresh(obj);
    }

    public <T> void persist(T obj) {
        Assert.isNotNull(obj, ARGUMENT_OBJECT);
        this.em.persist(obj);
    }

    public <T> T find(Class<T> clazz, Long id) {
        return this.em.find(clazz, id);
    }

    public <T> void remove(T obj) {
        Assert.isNotNull(obj, ARGUMENT_OBJECT);
        this.em.remove(obj);
    }

    public <T> T findSingle(Class<T> type, String fieldName, Object fieldValue) {
        Assert.isNotNull(type, ARGUMENT_TYPE);
        Assert.isNotNull(fieldName, ARGUMENT_FIELDNAME);
        Assert.isNotNull(fieldValue, ARGUMENT_FIELDVALUE);
        CriteriaBuilder builder = this.em.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(type);
        Root<T> e = criteria.from(type);
        Predicate p = builder.equal(e.get(fieldName), fieldValue);
        criteria.where(p);
        TypedQuery<T> query = this.em.createQuery(criteria);
        List<T> list = query.getResultList();
        return list.isEmpty() ? null : list.get(0);
    }

    public <T> List<T> findList(Class<T> type, String fieldName, Object fieldValue) {
        Assert.isNotNull(type, ARGUMENT_TYPE);
        Assert.isNotNull(fieldName, ARGUMENT_FIELDNAME);
        Assert.isNotNull(fieldValue, ARGUMENT_FIELDVALUE);
        CriteriaBuilder builder = this.em.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(type);
        Root<T> e = criteria.from(type);
        Predicate p = builder.equal(e.get(fieldName), fieldValue);
        criteria.where(p);
        TypedQuery<T> query = this.em.createQuery(criteria);
        return query.getResultList();
    }

    public <T> List<T> findEmptyColumnList(Class<T> type, String fieldName) {
        Assert.isNotNull(type, ARGUMENT_TYPE);
        CriteriaBuilder builder = this.em.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(type);
        Root<T> e = criteria.from(type);
        Predicate p = builder.isNull(e.get(fieldName));
        criteria.where(p);
        TypedQuery<T> query = this.em.createQuery(criteria);
        return query.getResultList();
    }

    public <T> List<T> findAll(Class<T> type) {
        Assert.isNotNull(type, ARGUMENT_TYPE);
        String sqlquery = String.format("select e FROM %s e", type.getSimpleName());
        TypedQuery<T> query = this.em.createQuery(sqlquery, type);
        return query.getResultList();
    }

    public <T, F> boolean exists(Class<T> type, String fieldName, F fieldValue) {
        Assert.isNotNull(type, ARGUMENT_TYPE);
        Assert.isNotNull(fieldName, ARGUMENT_FIELDNAME);
        Assert.isNotNull(fieldValue, ARGUMENT_FIELDVALUE);
        CriteriaBuilder builder = this.em.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<T> e = criteria.from(type);
        criteria.select(builder.count(e));
        criteria.where(builder.equal(e.<F>get(fieldName), fieldValue));
        TypedQuery<Long> query = this.em.createQuery(criteria);
        int count = query.getSingleResult().intValue();
        return count > 0;
    }

    public <T> int count(Class<T> type) {
        Assert.isNotNull(type, ARGUMENT_TYPE);
        CriteriaBuilder builder = this.em.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<T> e = criteria.from(type);
        criteria.select(builder.count(e));
        TypedQuery<Long> query = this.em.createQuery(criteria);
        return query.getSingleResult().intValue();
    }

    public <T> int deleteAllEntries(Class<T> type) {
        // sorry, no truncate just delete
        CriteriaBuilder builder = this.em.getCriteriaBuilder();
        CriteriaDelete<T> query = builder.createCriteriaDelete(type);
        query.from(type);
        return em.createQuery(query).executeUpdate();
    }

}