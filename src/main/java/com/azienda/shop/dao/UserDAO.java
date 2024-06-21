package com.azienda.shop.dao;

import com.azienda.shop.model.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

public class UserDAO extends AbstractDAO<User> {

    public UserDAO(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    protected List<User> executeQuery(String query) {

        return List.of();
    }
    public User findByName(String username) {
        try{
            return entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }

    }

    public User findByEmail(String email) {
        try{
            return entityManager.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }

    }


    @Override
    protected Class<User> getEntityClass() {
        return  User.class;
    }
}
