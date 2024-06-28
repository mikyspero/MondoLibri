package com.azienda.shop.dao;

import com.azienda.shop.model.Product;
import com.azienda.shop.model.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

public class ProductDAO extends AbstractDAO<Product> {

    public ProductDAO(EntityManager entityManager) {
        super(entityManager);
    }


    public Product findByName(String name) {
        try{
            return entityManager.createQuery("SELECT u FROM Product u WHERE u.name = :name", Product.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Product> search(String name) {
        return entityManager.createQuery("SELECT p FROM Product p WHERE LOWER(p.name) LIKE :name", Product.class)
                .setParameter("name", "%" + name.toLowerCase() + "%")
                .getResultList();
    }


    public List<Product> search(double minPrice, double maxPrice) {
        return entityManager.createQuery("SELECT p FROM Product p WHERE p.price BETWEEN :minPrice AND :maxPrice", Product.class)
                .setParameter("minPrice", minPrice)
                .setParameter("maxPrice", maxPrice)
                .getResultList();
    }

    // Search products by name and price range
    public List<Product> search(String name, double minPrice, double maxPrice) {
        return entityManager.createQuery(
                        "SELECT p FROM Product p WHERE LOWER(p.name) LIKE :name AND p.price BETWEEN :minPrice AND :maxPrice", Product.class)
                .setParameter("name", "%" + name.toLowerCase() + "%")
                .setParameter("minPrice", minPrice)
                .setParameter("maxPrice", maxPrice)
                .getResultList();
    }

    public List<Product> findAll() {
        return entityManager.createQuery("SELECT u FROM Product u", Product.class).getResultList();
    }


    @Override
    protected List<Product> executeQuery(String query) {
        return List.of();
    }

    @Override
    public Class<Product> getEntityClass() {
        return Product.class;
    }
}

