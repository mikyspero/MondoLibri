package com.azienda.shop.businessLogic;

import com.azienda.shop.dao.AbstractDAO;
import com.azienda.shop.exceptions.DataAccessException;
import com.azienda.shop.exceptions.PersistenceException;
import javassist.NotFoundException;
import org.hibernate.service.spi.ServiceException;

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

    protected <R> R executeTransaction(Supplier<R> action) throws ServiceException {
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
        } catch (DataAccessException e) {
            if (isNewTransaction && transaction.isActive()) {
                transaction.rollback();
            }
            throw new PersistenceException("Database operation failed", e);
        } catch (Exception e) {
            if (isNewTransaction && transaction.isActive()) {
                transaction.rollback();
            }
            throw new ServiceException("Transaction failed", e);
        }
    }

    protected void executeTransaction(Runnable action) throws ServiceException {
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

        } catch (DataAccessException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new PersistenceException("Database operation failed", e);
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new ServiceException("Transaction failed", e);
        }
    }

    public T insert(T toBeInserted) throws ServiceException {
        try {
            return executeTransaction(() -> dao.create(toBeInserted));
        } catch (DataAccessException e) {
            throw new PersistenceException("Failed to insert entity", e);
        }
    }

    public T retrieveById(Integer id) throws NotFoundException,PersistenceException {
        try {
            T entity = executeTransaction(() -> dao.findById(id));
            if (entity == null) {
                return null;
            }
            return entity;
        } catch (DataAccessException e) {
            throw new PersistenceException("Failed to retrieve entity with id: " + id, e);
        }
    }

    public List<T> retrieveAll() throws ServiceException {
        try {
            return executeTransaction(() -> dao.findAll());
        } catch (DataAccessException e) {
            throw new PersistenceException("Failed to retrieve all entities", e);
        }
    }

    public T updateElement(T toBeUpdated) throws ServiceException {
        try {
            return executeTransaction(() -> dao.update(toBeUpdated));
        } catch (DataAccessException e) {
            throw new PersistenceException("Failed to update entity", e);
        }
    }

    public boolean doesElementExist(T toBeFound) throws ServiceException {
        try {
            return executeTransaction(() -> dao.exist(toBeFound));
        } catch (DataAccessException e) {
            throw new PersistenceException("Failed to check if element exists", e);
        }
    }

    public void delete(T toBeDeleted) throws ServiceException {
        try {
            executeTransaction(() -> dao.delete(toBeDeleted));
        } catch (DataAccessException e) {
            throw new PersistenceException("Failed to delete entity", e);
        }
    }
}