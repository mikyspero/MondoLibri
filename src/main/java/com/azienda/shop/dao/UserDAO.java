package com.azienda.shop.dao;

import com.azienda.shop.exceptions.DataAccessException;
import com.azienda.shop.model.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import java.util.List;

/**
 * Data Access Object (DAO) for managing User entities.
 * Extends AbstractDAO to inherit common CRUD operations.
 */
public class UserDAO extends AbstractDAO<User> {

    /**
     * Constructs a UserDAO with the provided EntityManager instance.
     * @param entityManager The EntityManager instance to be used for database operations.
     */
    public UserDAO(EntityManager entityManager) {
        super(entityManager);
    }

    /**
     * Gets the Class object representing the User entity.
     * @return The Class object for the User entity.
     */
    @Override
    public Class<User> getEntityClass() {
        return User.class;
    }

    /**
     * Finds a user by their username.
     * @param username The username of the user to be retrieved.
     * @return The User entity corresponding to the provided username, or null if not found.
     * @throws DataAccessException if there is an error while finding the user by username.
     */
    public User findByName(String username) throws DataAccessException {
        try {
            return entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (PersistenceException e) {
            throw new DataAccessException("Error while finding User by username: " + username, e);
        }
    }

    /**
     * Finds a user by their email address.
     * @param email The email address of the user to be retrieved.
     * @return The User entity corresponding to the provided email address, or null if not found.
     * @throws DataAccessException if there is an error while finding the user by email.
     */
    public User findByEmail(String email) throws DataAccessException {
        try {
            return entityManager.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (PersistenceException e) {
            throw new DataAccessException("Error while finding User by email: " + email, e);
        }
    }

    /**
     * Executes a JPQL query (not implemented in this subclass).
     * @param query The JPQL query string to be executed.
     * @return A list of User entities resulting from the query execution.
     */
    @Override
    protected List<User> executeQuery(String query) {
        // Placeholder implementation, not used in this subclass
        return List.of();
    }
}

