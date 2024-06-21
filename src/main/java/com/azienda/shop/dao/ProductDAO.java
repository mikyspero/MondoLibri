package com.azienda.shop.dao;

import com.azienda.shop.model.Product;

import javax.persistence.EntityManager;
import java.util.List;

public class ProductDAO extends AbstractDAO<Product> {

    public ProductDAO(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    protected List<Product> executeQuery(String query) {
        return List.of();
    }

    @Override
    protected Class<Product> getEntityClass() {
        return Product.class;
    }
}
