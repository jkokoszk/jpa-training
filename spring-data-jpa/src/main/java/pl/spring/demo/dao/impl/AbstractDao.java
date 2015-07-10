package pl.spring.demo.dao.impl;

import pl.spring.demo.dao.Dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

@Transactional(Transactional.TxType.SUPPORTS)
public abstract class AbstractDao<T, K extends Serializable> implements Dao<T, K> {

    @PersistenceContext
    protected EntityManager entityManager;

    private Class<T> domainClass;

    @Override
    public void save(T entity) {
        entityManager.persist(entity);
    }

    @Override
    @SuppressWarnings("unchecked")
    public T getReference(K id) {
        return entityManager.getReference(getDomainClass(), id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public T find(K id) {
        return entityManager.find(getDomainClass(), id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = builder.createQuery(getDomainClass());
        criteriaQuery.from(getDomainClass());
        TypedQuery<T> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public T update(T entity) {
        return entityManager.merge(entity);
    }

    @Override
    public void delete(T entity) {
        entityManager.remove(entity);
    }

    @Override
    public void delete(K id) {
        entityManager.remove(getReference(id));
    }

    @Override
    public void deleteAll() {
        entityManager.createQuery("delete " + getDomainClassName()).executeUpdate();
    }

    @Override
    public long count() {
        return (long) entityManager.createQuery("Select count(*) from " + getDomainClassName()).getSingleResult();
    }

    @Override
    public boolean exists(K id) {
        // TODO fix it, probably find one is needed
        return getReference(id) != null;
    }

    @SuppressWarnings("unchecked")
    protected Class<T> getDomainClass() {
        if (domainClass == null) {
            ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
            domainClass = (Class<T>) type.getActualTypeArguments()[0];
        }
        return domainClass;
    }

    protected String getDomainClassName() {
        return getDomainClass().getName();
    }
}