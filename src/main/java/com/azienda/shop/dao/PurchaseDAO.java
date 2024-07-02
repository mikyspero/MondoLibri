package com.azienda.shop.dao;

import com.azienda.shop.exceptions.DataAccessException;
import com.azienda.shop.model.Product;
import com.azienda.shop.model.Purchase;
import com.azienda.shop.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

/**
 * Data Access Object (DAO) for managing Purchase entities.
 * Extends AbstractDAO to inherit common CRUD operations.
 */
public class PurchaseDAO extends AbstractDAO<Purchase> {

    /**
     * Constructs a PurchaseDAO with the provided EntityManager instance.
     * @param entityManager The EntityManager instance to be used for database operations.
     */
    public PurchaseDAO(EntityManager entityManager) {
        super(entityManager);
    }

    /**
     * Gets the Class object representing the Purchase entity.
     * @return The Class object for the Purchase entity.
     */
    @Override
    public Class<Purchase> getEntityClass() {
        return Purchase.class;
    }

    /**
     * Finds purchases made by a specific user.
     * @param user The user whose purchases are to be retrieved.
     * @return A list of Purchase entities made by the specified user.
     * @throws DataAccessException if there is an error while finding purchases by user
     */
    public List<Purchase> findByUser(User user) throws DataAccessException {
        try {
            String jpql = "SELECT p FROM Purchase p WHERE p.user = :user";
            TypedQuery<Purchase> query = entityManager.createQuery(jpql, Purchase.class);
            query.setParameter("user", user);
            return query.getResultList();
        } catch (PersistenceException e) {
            throw new DataAccessException("Error while finding Purchases by user: " + user.getId(), e);
        }
    }

    /**
     * Finds purchases that involve a specific product.
     * @param product The product involved in the purchases to be retrieved.
     * @return A list of Purchase entities that involve the specified product.
     * @throws DataAccessException if there is an error while finding purchases by product
     */
    public List<Purchase> findByProduct(Product product) throws DataAccessException {
        try {
            String jpql = "SELECT p FROM Purchase p WHERE p.product = :product";
            TypedQuery<Purchase> query = entityManager.createQuery(jpql, Purchase.class);
            query.setParameter("product", product);
            return query.getResultList();
        } catch (PersistenceException e) {
            throw new DataAccessException("Error while finding Purchases by product: " + product.getId(), e);
        }
    }

    /**
     * Finds purchases made within a specified date range.
     * @param startDate The start date of the date range.
     * @param endDate The end date of the date range.
     * @return A list of Purchase entities made between the specified start and end dates.
     * @throws DataAccessException if there is an error while finding purchases by date range
     */
    public List<Purchase> findByDateRange(Date startDate, Date endDate) throws DataAccessException {
        try {
            String jpql = "SELECT p FROM Purchase p WHERE p.purchaseDate BETWEEN :startDate AND :endDate";
            TypedQuery<Purchase> query = entityManager.createQuery(jpql, Purchase.class);
            query.setParameter("startDate", startDate);
            query.setParameter("endDate", endDate);
            return query.getResultList();
        } catch (PersistenceException e) {
            throw new DataAccessException("Error while finding Purchases by date range: " + startDate + " to " + endDate, e);
        }
    }

    /**
     * Counts the number of purchases made for a specific product.
     * @param product The product for which the purchase count is to be retrieved.
     * @return The number of Purchase entities associated with the specified product.
     * @throws DataAccessException if there is an error while counting purchases by product
     */
    public Long countPurchasesByProduct(Product product) throws DataAccessException {
        try {
            String jpql = "SELECT COUNT(p) FROM Purchase p WHERE p.product = :product";
            TypedQuery<Long> query = entityManager.createQuery(jpql, Long.class);
            query.setParameter("product", product);
            return query.getSingleResult();
        } catch (PersistenceException e) {
            throw new DataAccessException("Error while counting Purchases by product: " + product.getId(), e);
        }
    }

    /**
     * Finds the most recent purchases, limited by the specified count.
     * @param count The maximum number of recent purchases to retrieve.
     * @return A list of the most recent Purchase entities, sorted by purchase date in descending order.
     * @throws DataAccessException if there is an error while finding the most recent purchases
     */
    public List<Purchase> findMostRecentPurchases(int count) throws DataAccessException {
        try {
            String jpql = "SELECT p FROM Purchase p ORDER BY p.purchaseDate DESC";
            TypedQuery<Purchase> query = entityManager.createQuery(jpql, Purchase.class);
            query.setMaxResults(count);
            return query.getResultList();
        } catch (PersistenceException e) {
            throw new DataAccessException("Error while finding the most recent Purchases, count: " + count, e);
        }
    }

    /**
     * Executes a JPQL query (not implemented in this subclass).
     * @param query The JPQL query string to be executed.
     * @return A list of Purchase entities resulting from the query execution.
     * @throws DataAccessException if there is an error while executing the query
     */
    @Override
    protected List<Purchase> executeQuery(String query) throws DataAccessException {
        try {
            TypedQuery<Purchase> typedQuery = entityManager.createQuery(query, Purchase.class);
            return typedQuery.getResultList();
        } catch (PersistenceException e) {
            throw new DataAccessException("Error while executing query: " + query, e);
        }
    }
}


