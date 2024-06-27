package com.azienda.shop.dao;
import java.util.List;
import javax.persistence.EntityManager;

// This interface defines the common CRUD operations for a DAO (Data Access Object)
public abstract class AbstractDAO<T> implements DAOinterface<T> {
    // Protected field to hold the EntityManager instance
    protected EntityManager entityManager;

    // Abstract method to execute a query and return a list of results
    // Subclasses must provide the implementation for their specific entity type
    protected abstract List<T> executeQuery(String query);

    // Abstract method to get the entity class
    // Subclasses must provide the implementation for their specific entity type
    public abstract Class<T> getEntityClass();

    // Implementation of the create method
    // Persists a new entity instance to the database
    @Override
    public T create(T t) {
        entityManager.persist(t);
        return t;
    }

    // Implementation of the read method
    // Returns the given entity instance without performing any operations
    // This method might not be useful as is and could be removed or modified
    @Override
    public T read(T t) {
        return t;
    }

    // Method to find all instances of the entity type
    // Uses a JPQL query based on the entity class name
    public List<T> findAll() {
        return entityManager.createQuery("SELECT e FROM " + getEntityClass().getSimpleName() + " e", getEntityClass())
                .getResultList();
    }

    // Method to find an entity instance by its ID
    public T findById(Integer id) {
        return entityManager.find(getEntityClass(), id);
    }

    // Method to check if an entity instance is managed by the EntityManager
    public boolean exist(T t) {
        return entityManager.contains(t);
    }

    // Implementation of the update method
    // Updates an existing entity instance in the database
    @Override
    public T update(T t) {
        entityManager.merge(t);
        return t;
    }

    // Implementation of the delete method
    // Removes an entity instance from the database
    @Override
    public void delete(T t) {
        entityManager.remove(t);
    }

    // Getter method for the EntityManager instance
    public EntityManager getEntityManager() {
        return entityManager;
    }

    // Constructor that takes an EntityManager instance
    protected AbstractDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }




}
