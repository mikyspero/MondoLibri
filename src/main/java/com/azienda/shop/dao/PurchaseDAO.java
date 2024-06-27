package com.azienda.shop.dao;

import com.azienda.shop.model.Purchase;
import com.azienda.shop.model.User;
import com.azienda.shop.model.Product;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

public class PurchaseDAO extends AbstractDAO<Purchase> {

    public PurchaseDAO(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Purchase> getEntityClass() {
        return Purchase.class;
    }



    // Find purchases by user
    public List<Purchase> findByUser(User user) {
        String jpql = "SELECT p FROM Purchase p WHERE p.user = :user";
        TypedQuery<Purchase> query = entityManager.createQuery(jpql, Purchase.class);
        query.setParameter("user", user);
        return query.getResultList();
    }

    // Find purchases by product
    public List<Purchase> findByProduct(Product product) {
        String jpql = "SELECT p FROM Purchase p WHERE p.product = :product";
        TypedQuery<Purchase> query = entityManager.createQuery(jpql, Purchase.class);
        query.setParameter("product", product);
        return query.getResultList();
    }

    // Find purchases by date range
    public List<Purchase> findByDateRange(Date startDate, Date endDate) {
        String jpql = "SELECT p FROM Purchase p WHERE p.purchaseDate BETWEEN :startDate AND :endDate";
        TypedQuery<Purchase> query = entityManager.createQuery(jpql, Purchase.class);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        return query.getResultList();
    }

    // Count purchases by product
    public Long countPurchasesByProduct(Product product) {
        String jpql = "SELECT COUNT(p) FROM Purchase p WHERE p.product = :product";
        TypedQuery<Long> query = entityManager.createQuery(jpql, Long.class);
        query.setParameter("product", product);
        return query.getSingleResult();
    }

    // Find most recent purchases (limit by count)
    public List<Purchase> findMostRecentPurchases(int count) {
        String jpql = "SELECT p FROM Purchase p ORDER BY p.purchaseDate DESC";
        TypedQuery<Purchase> query = entityManager.createQuery(jpql, Purchase.class);
        query.setMaxResults(count);
        return query.getResultList();
    }

    @Override
    protected List<Purchase> executeQuery(String query) {
        TypedQuery<Purchase> typedQuery = entityManager.createQuery(query, Purchase.class);
        return typedQuery.getResultList();
    }
}
