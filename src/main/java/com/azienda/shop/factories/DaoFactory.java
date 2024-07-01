package com.azienda.shop.factories;

import com.azienda.shop.dao.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class DaoFactory {
    private final EntityManager em;

    private DaoFactory(EntityManager em) {
        this.em = em;
    }

    public static DaoFactory getIstance(EntityManagerFactory emf){
        return new DaoFactory(emf.createEntityManager());
    }

    public CartDAO makeCartDAO() {
        return new CartDAO(em);
    }

    public ProductDAO makeProductDao(){
        return new ProductDAO(em);
    }

    public PurchaseDAO makePurchaseDAO(){
        return new PurchaseDAO(em);
    }

    public UserDAO makeUserDAO(){
        return new UserDAO(em);
    }

    public EntityManager getEntityManager() {
        return em;
    }
}
