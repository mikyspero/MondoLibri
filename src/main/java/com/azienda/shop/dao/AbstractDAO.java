package com.azienda.shop.dao;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * This abstract class defines common CRUD operations for a DAO (Data Access Object).
 * @param <T> The entity type managed by the DAO.
 */
public abstract class AbstractDAO<T> implements DAOinterface<T> {

    /**
     * The EntityManager instance used for database operations.
     */
    protected EntityManager entityManager;

    /**
     * Constructs an AbstractDAO with the provided EntityManager instance.
     * @param entityManager The EntityManager instance to be used by the DAO.
     */
    protected AbstractDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Abstract method to execute a query and return a list of results.
     * Subclasses must provide the implementation for their specific entity type.
     * @param query The query string to be executed.
     * @return A list of results of type T.
     */
    protected abstract List<T> executeQuery(String query);

    /**
     * Abstract method to get the entity class.
     * Subclasses must provide the implementation for their specific entity type.
     * @return The Class object representing the entity type T.
     */
    public abstract Class<T> getEntityClass();

    /**
     * Persists a new entity instance to the database.
     * @param t The entity instance to be persisted.
     * @return The persisted entity instance.
     */
    @Override
    public T create(T t) {
        entityManager.persist(t);
        return t;
    }

    /**
     * Returns the given entity instance without performing any operations.
     * This method might not be useful as is and could be removed or modified.
     * @param t The entity instance to be returned.
     * @return The given entity instance.
     */
    @Override
    public T read(T t) {
        return t;
    }

    /**
     * Finds all instances of the entity type.
     * @return A list of all instances of type T.
     */
    public List<T> findAll() {
        return entityManager.createQuery("SELECT e FROM " + getEntityClass().getSimpleName() + " e", getEntityClass())
                .getResultList();
    }

    /**
     * Finds an entity instance by its ID.
     * @param id The ID of the entity to find.
     * @return The entity instance with the specified ID, or null if not found.
     */
    public T findById(Integer id) {
        return entityManager.find(getEntityClass(), id);
    }

    /**
     * Checks if an entity instance is managed by the EntityManager.
     * @param t The entity instance to check.
     * @return true if the entity is managed (exists in the persistence context), false otherwise.
     */
    public boolean exist(T t) {
        return entityManager.contains(t);
    }

    /**
     * Updates an existing entity instance in the database.
     * @param t The entity instance to be updated.
     * @return The updated entity instance.
     */
    @Override
    public T update(T t) {
        entityManager.merge(t);
        return t;
    }

    /**
     * Removes an entity instance from the database.
     * @param t The entity instance to be deleted.
     */
    @Override
    public void delete(T t) {
        entityManager.remove(t);
    }

    /**
     * Getter method for the EntityManager instance.
     * @return The EntityManager instance used by the DAO.
     */
    public EntityManager getEntityManager() {
        return entityManager;
    }
}

