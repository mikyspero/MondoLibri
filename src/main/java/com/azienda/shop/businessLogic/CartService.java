package com.azienda.shop.businessLogic;

import com.azienda.shop.dao.AbstractDAO;
import com.azienda.shop.dao.CartDAO;
import com.azienda.shop.dao.UserDAO;
import com.azienda.shop.model.Cart;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class CartService extends AbstractService<Cart> {
    public CartService(EntityManager manager, CartDAO dao ){
        super(manager, dao);
    }
}
