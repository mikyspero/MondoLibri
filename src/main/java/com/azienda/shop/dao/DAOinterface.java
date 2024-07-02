package com.azienda.shop.dao;

import com.azienda.shop.exceptions.DataAccessException;

public interface DAOinterface<T> {
    /**
     * Creates a new entity in the data store.
     *
     * @param t the entity to be created
     * @return the created entity with its primary key set (if applicable)
     */
    T create(T t) throws DataAccessException;

    /**
     * Retrieves an entity from the data store.
     *
     * @param t the entity to be retrieved
     * @return the retrieved entity, or null if not found
     */
    T read(T t) throws DataAccessException;

    /**
     * Updates an existing entity in the data store.
     *
     * @param t the entity to be updated
     * @return the updated entity
     */
    T update(T t) throws DataAccessException;

    /**
     * Deletes an entity from the data store.
     *
     * @param t the entity to be deleted
     */
    void delete(T t) throws DataAccessException;
}
