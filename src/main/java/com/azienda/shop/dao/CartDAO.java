package com.azienda.shop.dao;

import com.azienda.shop.model.Cart;
import com.azienda.shop.model.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

public class CartDAO  extends AbstractDAO<Cart>{

    public CartDAO(EntityManager entityManager) {
        super(entityManager);
    }

    public Cart findByUser(User user) {
        try {
            return entityManager.createQuery("SELECT c FROM Cart c WHERE c.user.id = :userId", Cart.class)
                    .setParameter("userId", user.getId())
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    protected List<Cart> executeQuery(String query) {
        return List.of();
    }

    @Override
    public Class<Cart> getEntityClass() {
        return Cart.class;
    }
}
