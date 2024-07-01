package com.azienda.shop.factories;

import com.azienda.shop.businessLogic.CartService;
import com.azienda.shop.businessLogic.ProductService;
import com.azienda.shop.businessLogic.PurchaseService;
import com.azienda.shop.businessLogic.UserService;

public class ServiceFactory {
    DaoFactory daoFactory;

    private ServiceFactory(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public static ServiceFactory getInstance(DaoFactory daoFactory) {
        return new ServiceFactory(daoFactory);
    }

    public CartService getCartService() {
        return new CartService(daoFactory.getEntityManager(), daoFactory.makeCartDAO(), daoFactory.makeProductDao(), daoFactory.makeUserDAO());
    }

    public ProductService getProductService() {
        return new ProductService(daoFactory.getEntityManager(), daoFactory.makeProductDao(), daoFactory.makeCartDAO());
    }

    public PurchaseService getPurchaseService() {
        return new PurchaseService(daoFactory.getEntityManager(), daoFactory.makePurchaseDAO(), daoFactory.makeCartDAO(), daoFactory.makeProductDao());
    }

    public UserService getUserService() {
        return new UserService(daoFactory.getEntityManager(), daoFactory.makeUserDAO(), daoFactory.makeCartDAO());
    }
}
