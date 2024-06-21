package com.azienda.shop.businessLogic;

import com.azienda.shop.dao.ProductDAO;
import com.azienda.shop.model.Product;

import javax.persistence.EntityManager;

public class ProductService extends AbstractService<Product> {
    public ProductService(EntityManager manager, ProductDAO dao) {
        super( manager, dao);
    }

}
