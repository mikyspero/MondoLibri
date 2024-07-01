package com.azienda.shop.factories;

import com.azienda.shop.businessLogic.*;
import com.azienda.shop.dao.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class ServiceFactory {
    private final DaoFactory daoFactory;

    private ServiceFactory(EntityManagerFactory emf) {
        this.daoFactory = new DaoFactory(emf.createEntityManager());
    }

    public static ServiceFactory getInstance(EntityManagerFactory emf) {
        return new ServiceFactory(emf);
    }

    public CartService getCartService() {
        return new CartService(
                daoFactory.getEntityManager(),
                daoFactory.getCartDAO(),
                daoFactory.getProductDAO(),
                daoFactory.getUserDAO()
        );
    }

    public ProductService getProductService() {
        return new ProductService(
                daoFactory.getEntityManager(),
                daoFactory.getProductDAO(),
                daoFactory.getCartDAO()
        );
    }

    public PurchaseService getPurchaseService() {
        return new PurchaseService(
                daoFactory.getEntityManager(),
                daoFactory.getPurchaseDAO(),
                daoFactory.getCartDAO(),
                daoFactory.getProductDAO()
        );
    }

    public UserService getUserService() {
        return new UserService(
                daoFactory.getEntityManager(),
                daoFactory.getUserDAO(),
                daoFactory.getCartDAO()
        );
    }

    private static class DaoFactory {
        private final EntityManager em;
        private CartDAO cartDAO;
        private ProductDAO productDAO;
        private PurchaseDAO purchaseDAO;
        private UserDAO userDAO;

        private DaoFactory(EntityManager em) {
            this.em = em;
        }

        public CartDAO getCartDAO() {
            if (cartDAO == null) {
                cartDAO = new CartDAO(em);
            }
            return cartDAO;
        }

        public ProductDAO getProductDAO() {
            if (productDAO == null) {
                productDAO = new ProductDAO(em);
            }
            return productDAO;
        }

        public PurchaseDAO getPurchaseDAO() {
            if (purchaseDAO == null) {
                purchaseDAO = new PurchaseDAO(em);
            }
            return purchaseDAO;
        }

        public UserDAO getUserDAO() {
            if (userDAO == null) {
                userDAO = new UserDAO(em);
            }
            return userDAO;
        }

        public EntityManager getEntityManager() {
            return em;
        }
    }
}