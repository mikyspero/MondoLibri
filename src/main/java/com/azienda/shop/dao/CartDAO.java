package com.azienda.shop.dao;

import com.azienda.shop.model.Cart;

import javax.persistence.EntityManager;
import java.util.List;

public class CartDAO  extends AbstractDAO<Cart>{

    public CartDAO(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    protected List<Cart> executeQuery(String query) {
        return List.of();
    }

    @Override
    protected Class<Cart> getEntityClass() {
        return null;
    }
}
