package com.azienda.shop.dao;

import com.azienda.shop.model.User;

import javax.persistence.EntityManager;
import java.util.List;

public class UserDAO extends AbstractDAO<User> {

    public UserDAO(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    protected List<User> executeQuery(String query) {

        return List.of();
    }

    @Override
    protected Class<User> getEntityClass() {
        return null;
    }
}
