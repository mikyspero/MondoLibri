package com.azienda.shop.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;


public abstract class AbstractDAO<T> implements DAOinterface<T> {
    protected EntityManager entityManager;
    protected abstract List<T> executeQuery(String query);
    protected abstract Class<T> getEntityClass();

    @Override
    public T create(T t) {
        entityManager.persist(t);
        return t;
    }

    @Override
    public T read(T t) {
        return t;
    }

    public List<T> findAll() {
        return entityManager.createQuery("SELECT e FROM " + getEntityClass().getSimpleName() + " e", getEntityClass())
                .getResultList();
    }

    public T findById(Integer id) {
        return entityManager.find(getEntityClass(), id);
    }


    boolean exist(T t) {
        return entityManager.contains(t);
    }


    @Override
    public T update(T t) {
        entityManager.merge(t);
        return t;
    }

    @Override
    public void delete(T t) {
        entityManager.remove(t);
    }


    public EntityManager getEntityManager() {
        return entityManager;
    }

    protected  AbstractDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

}
