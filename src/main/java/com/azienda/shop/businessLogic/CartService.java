package com.azienda.shop.businessLogic;

import com.azienda.shop.dao.AbstractDAO;
import com.azienda.shop.dao.CartDAO;
import com.azienda.shop.dao.ProductDAO;
import com.azienda.shop.dao.UserDAO;
import com.azienda.shop.model.Cart;
import com.azienda.shop.model.Product;
import com.azienda.shop.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class CartService extends AbstractService<Cart> {
    private CartDAO cartDAO;
    private ProductDAO productDAO;
    private UserDAO userDAO;

    public CartService(EntityManager manager, CartDAO dao ){
        super(manager, dao);
    }

    public CartService(EntityManager manager, CartDAO cartDAO, ProductDAO productDAO, UserDAO userDAO) {
        super(manager, cartDAO);
        this.cartDAO = cartDAO;
        this.productDAO = productDAO;
        this.userDAO = userDAO;
    }


    public void addProductToCart(Integer userId, Integer productId) throws Exception {
        executeTransaction(() -> {
            User user = userDAO.findById(userId);
            if (user == null) {
                throw new IllegalArgumentException("User not found");
            }
            Cart cart = user.getCart();
            if (cart == null) {
                cart = new Cart();
                cart.setUser(user);
                user.setCart(cart);
                cartDAO.create(cart);
            }

            Product product = productDAO.findById(productId);
            if (product == null) {
                throw new IllegalArgumentException("Product not found");
            }

            cart.getProducts().add(product);
            cartDAO.update(cart);

            return null;
        });
    }

    public void removeProductFromCart(Integer userId, Integer productId) throws Exception {
        executeTransaction(() -> {
            User user = userDAO.findById(userId);
            if (user == null) {
                throw new IllegalArgumentException("User not found");
            }
            Cart cart = user.getCart();
            if (cart == null) {
                throw new IllegalArgumentException("Cart not found");
            }

            Product product = productDAO.findById(productId);
            if (product == null) {
                throw new IllegalArgumentException("Product not found");
            }

            cart.getProducts().remove(product);
            cartDAO.update(cart);

            return null;
        });
    }

    public Cart getCartByUserId(Integer userId) throws Exception {
        return executeTransaction(() -> {
            User user = userDAO.findById(userId);
            if (user == null) {
                throw new IllegalArgumentException("User not found");
            }
            return user.getCart();
        });
    }
}

