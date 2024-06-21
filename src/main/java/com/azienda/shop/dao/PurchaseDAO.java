package com.azienda.shop.dao;

import com.azienda.shop.model.Purchase;

import javax.persistence.EntityManager;
import java.util.List;

public class PurchaseDAO extends  AbstractDAO<Purchase> {

    public PurchaseDAO(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    protected List<Purchase> executeQuery(String query) {

        return List.of();
    }

    @Override
    protected Class<Purchase> getEntityClass() {
        return  Purchase.class;
    }
}
