package com.azienda.shop.dao;

public interface DAOinterface<T> {
    /**
     * Creates a new entity in the data store.
     *
     * @param t the entity to be created
     * @return the created entity with its primary key set (if applicable)
     */
    T create(T t);

    /**
     * Retrieves an entity from the data store.
     *
     * @param t the entity to be retrieved
     * @return the retrieved entity, or null if not found
     */
    T read(T t);

    /**
     * Updates an existing entity in the data store.
     *
     * @param t the entity to be updated
     * @return the updated entity
     */
    T update(T t);

    /**
     * Deletes an entity from the data store.
     *
     * @param t the entity to be deleted
     */
    void delete(T t);
}
