package com.azienda.shop.dao;

import com.azienda.shop.exceptions.DataAccessException;
import com.azienda.shop.model.Product;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import java.util.List;

/**
 * Data Access Object (DAO) for managing Product entities.
 * Extends AbstractDAO to inherit common CRUD operations.
 */
public class ProductDAO extends AbstractDAO<Product> {

    /**
     * Constructs a ProductDAO with the provided EntityManager instance.
     * @param entityManager The EntityManager instance to be used for database operations.
     */
    public ProductDAO(EntityManager entityManager) {
        super(entityManager);
    }

    /**
     * Finds a Product entity by its name.
     * @param name The name of the product to find.
     * @return The Product with the specified name, or null if not found.
     * @throws DataAccessException if there is an error while finding the product by name
     */
    public Product findByName(String name) throws DataAccessException {
        try {
            return entityManager.createQuery("SELECT u FROM Product u WHERE u.name = :name", Product.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (PersistenceException e) {
            throw new DataAccessException("Error while finding Product by name: " + name, e);
        }
    }

    /**
     * Searches for products by name.
     * @param name The name to search for.
     * @return A list of products whose names contain the specified name.
     * @throws DataAccessException if there is an error while searching for products by name
     */
    public List<Product> search(String name) throws DataAccessException {
        try {
            return entityManager.createQuery("SELECT p FROM Product p WHERE LOWER(p.name) LIKE :name", Product.class)
                    .setParameter("name", "%" + name.toLowerCase() + "%")
                    .getResultList();
        } catch (PersistenceException e) {
            throw new DataAccessException("Error while searching for Products by name: " + name, e);
        }
    }

    /**
     * Searches for products within a specified price range.
     * @param minPrice The minimum price.
     * @param maxPrice The maximum price.
     * @return A list of products within the specified price range.
     * @throws DataAccessException if there is an error while searching for products by price range
     */
    public List<Product> search(double minPrice, double maxPrice) throws DataAccessException {
        try {
            return entityManager.createQuery("SELECT p FROM Product p WHERE p.price BETWEEN :minPrice AND :maxPrice", Product.class)
                    .setParameter("minPrice", minPrice)
                    .setParameter("maxPrice", maxPrice)
                    .getResultList();
        } catch (PersistenceException e) {
            throw new DataAccessException("Error while searching for Products by price range: " + minPrice + " - " + maxPrice, e);
        }
    }

    /**
     * Searches for products by name and price range.
     * @param name The name to search for.
     * @param minPrice The minimum price.
     * @param maxPrice The maximum price.
     * @return A list of products that match the specified name and price range.
     * @throws DataAccessException if there is an error while searching for products by name and price range
     */
    public List<Product> search(String name, double minPrice, double maxPrice) throws DataAccessException {
        try {
            return entityManager.createQuery(
                            "SELECT p FROM Product p WHERE LOWER(p.name) LIKE :name AND p.price BETWEEN :minPrice AND :maxPrice", Product.class)
                    .setParameter("name", "%" + name.toLowerCase() + "%")
                    .setParameter("minPrice", minPrice)
                    .setParameter("maxPrice", maxPrice)
                    .getResultList();
        } catch (PersistenceException e) {
            throw new DataAccessException("Error while searching for Products by name and price range: " + name + ", " + minPrice + " - " + maxPrice, e);
        }
    }

    /**
     * Finds all Product entities.
     * @return A list of all Product entities.
     * @throws DataAccessException if there is an error while finding all products
     */
    public List<Product> findAll() throws DataAccessException {
        try {
            return entityManager.createQuery("SELECT u FROM Product u", Product.class).getResultList();
        } catch (PersistenceException e) {
            throw new DataAccessException("Error while finding all Products", e);
        }
    }

    /**
     * Executes a query (not implemented in this subclass).
     * @param query The query string to be executed.
     * @return An empty list, as this method is not implemented in this subclass.
     */
    @Override
    protected List<Product> executeQuery(String query) {
        return List.of(); // Return an empty list as placeholder
    }

    /**
     * Gets the Class object representing the Product entity.
     * @return The Class object for the Product entity.
     */
    @Override
    public Class<Product> getEntityClass() {
        return Product.class;
    }
}


