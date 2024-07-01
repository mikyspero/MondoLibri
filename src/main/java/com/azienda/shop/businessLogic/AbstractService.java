package com.azienda.shop.businessLogic;

import com.azienda.shop.dao.AbstractDAO;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.function.Supplier;

/**
 * AbstractService class provides common functionalities for interacting with entities
 * using JPA EntityManager and AbstractDAO.
 *
 * @param <T> Type of the entity managed by this service.
 */
public abstract class AbstractService<T> {
    private EntityManager manager;
    private AbstractDAO<T> dao;

    /**
     * Constructs an AbstractService instance with an EntityManager and AbstractDAO.
     *
     * @param manager EntityManager instance for managing JPA operations.
     * @param dao     AbstractDAO instance for performing CRUD operations on entities.
     */
    protected AbstractService(EntityManager manager, AbstractDAO<T> dao) {
        this.manager = manager;
        this.dao = dao;
    }

    /**
     * Retrieves the EntityManager instance associated with this service.
     *
     * @return EntityManager instance.
     */
    public EntityManager getManager() {
        return manager;
    }

    /**
     * Sets the EntityManager instance for this service.
     *
     * @param manager EntityManager instance to set.
     */
    public void setManager(EntityManager manager) {
        this.manager = manager;
    }

    /**
     * Retrieves the AbstractDAO instance associated with this service.
     *
     * @return AbstractDAO instance.
     */
    public AbstractDAO getDao() {
        return dao;
    }

    /**
     * Sets the AbstractDAO instance for this service.
     *
     * @param dao AbstractDAO instance to set.
     */
    public void setDao(AbstractDAO dao) {
        this.dao = dao;
    }

    /**
     * Executes a transactional action using the provided Supplier.
     * If the transaction fails, it rolls back; otherwise, it commits.
     *
     * @param action Supplier action to execute within the transaction.
     * @param <R>    Return type of the action.
     * @return Result of the action.
     * @throws RuntimeException if an exception occurs during the transaction.
     */
    protected <R> R executeTransaction(Supplier<R> action) {
        EntityManager em = getManager();
        EntityTransaction transaction = em.getTransaction();
        boolean isNewTransaction = false;
        try {
            if (!transaction.isActive()) {
                transaction.begin();
                isNewTransaction = true;
            }
            R result = action.get();
            if (isNewTransaction) {
                if (!transaction.getRollbackOnly()) {
                    transaction.commit();
                } else {
                    transaction.rollback();
                }
            }
            return result;
        } catch (Exception e) {
            if (isNewTransaction && transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    /**
     * Executes a transactional action using the provided Runnable.
     * If the transaction fails, it rolls back; otherwise, it commits.
     *
     * @param action Runnable action to execute within the transaction.
     * @throws RuntimeException if an exception occurs during the transaction.
     */
    protected void executeTransaction(Runnable action) {
        EntityManager em = getManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            if (!transaction.isActive()) {
                transaction.begin();
            }

            action.run();

            if (transaction.isActive() && !transaction.getRollbackOnly()) {
                transaction.commit();
            } else {
                transaction.rollback();
            }

        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    /**
     * Inserts a new entity into the database using the DAO.
     *
     * @param toBeInserted Entity to be inserted.
     * @return Inserted entity.
     */
    public T insert(T toBeInserted) {
        return executeTransaction(() -> dao.create(toBeInserted));
    }

    /**
     * Retrieves an entity by its ID from the database using the DAO.
     *
     * @param id ID of the entity to retrieve.
     * @return Retrieved entity or null if not found.
     */
    public T retrieveById(Integer id) {
        return executeTransaction(() -> dao.findById(id));
    }

    /**
     * Retrieves all entities of this type from the database using the DAO.
     *
     * @return List of all entities.
     */
    public List<T> retrieveAll() {
        return executeTransaction(() -> dao.findAll());
    }

    /**
     * Updates an existing entity in the database using the DAO.
     *
     * @param toBeUpdated Entity to be updated.
     * @return Updated entity.
     */
    public T updateElement(T toBeUpdated) {
        return executeTransaction(() -> dao.update(toBeUpdated));
    }

    /**
     * Checks if a given entity exists in the database using the DAO.
     *
     * @param toBeFound Entity to check for existence.
     * @return True if the entity exists, false otherwise.
     */
    public boolean doesElementExist(T toBeFound) {
        return dao.exist(toBeFound);
    }

    /**
     * Deletes an entity from the database using the DAO.
     *
     * @param toBeDeleted Entity to be deleted.
     */
    public void delete(T toBeDeleted) {
        executeTransaction(() -> dao.delete(toBeDeleted));
    }
}


