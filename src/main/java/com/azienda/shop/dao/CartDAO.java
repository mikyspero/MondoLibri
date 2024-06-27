package com.azienda.shop.dao;

import com.azienda.shop.model.Cart;
import com.azienda.shop.model.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

/**
 * Data Access Object (DAO) for managing Cart entities.
 * Extends AbstractDAO to inherit common CRUD operations.
 */
public class CartDAO extends AbstractDAO<Cart> {

    /**
     * Constructs a CartDAO with the provided EntityManager instance.
     * @param entityManager The EntityManager instance to be used for database operations.
     */
    public CartDAO(EntityManager entityManager) {
        super(entityManager);
    }

    /**
     * Finds a Cart entity by the associated User.
     * @param user The User whose cart is to be found.
     * @return The Cart associated with the specified User, or null if not found.
     */
    public Cart findByUser(User user) {
        try {
            return entityManager.createQuery("SELECT c FROM Cart c WHERE c.user.id = :userId", Cart.class)
                    .setParameter("userId", user.getId())
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * Executes a query (not implemented in this subclass).
     * @param query The query string to be executed.
     * @return An empty list, as this method is not implemented in this subclass.
     */
    @Override
    protected List<Cart> executeQuery(String query) {
        return List.of(); // Return an empty list as placeholder
    }

    /**
     * Gets the Class object representing the Cart entity.
     * @return The Class object for the Cart entity.
     */
    @Override
    public Class<Cart> getEntityClass() {
        return Cart.class;
    }
}
