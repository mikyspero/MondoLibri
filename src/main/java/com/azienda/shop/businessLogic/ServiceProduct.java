package com.azienda.shop.businessLogic;

import com.azienda.shop.dao.ProductDAO;
import com.azienda.shop.model.Product;

import javax.persistence.EntityManager;

public class ServiceProduct {
    private EntityManager manager;


    public Product insertProduct(String name, Double price, int quantity,  String description, String language ) {
        try {
            manager.getTransaction().begin();
            Product product1 = new Product(name, price, quantity, description, language);
            EntityManager EntityManager = null;
            ProductDAO productDAO = new ProductDAO(manager);
            product1 = productDAO.create(product1);
            manager.getTransaction().commit();
            return product1;
        } catch (Exception e) {
            manager.getTransaction().rollback();
            throw e;
        }
    }
}
