package com.azienda.shop.businessLogic;

import com.azienda.shop.dao.CartDAO;
import com.azienda.shop.dao.ProductDAO;
import com.azienda.shop.dao.UserDAO;
import com.azienda.shop.model.Cart;
import com.azienda.shop.model.Product;
import com.azienda.shop.model.User;

import javax.persistence.EntityManager;

/**
 * Service class for managing operations related to user carts.
 * Extends AbstractService to leverage common transactional and CRUD operations.
 */
public class CartService extends AbstractService<Cart> {
    private final CartDAO cartDAO;
    private final ProductDAO productDAO;
    private final UserDAO userDAO;

    /**
     * Constructs a CartService instance.
     *
     * @param manager    EntityManager instance for managing JPA operations.
     * @param cartDAO    CartDAO instance for CRUD operations on carts.
     * @param productDAO ProductDAO instance for CRUD operations on products.
     * @param userDAO    UserDAO instance for CRUD operations on users.
     */
    public CartService(EntityManager manager, CartDAO cartDAO, ProductDAO productDAO, UserDAO userDAO) {
        super(manager, cartDAO);
        this.cartDAO = cartDAO;
        this.productDAO = productDAO;
        this.userDAO = userDAO;
    }

    /**
     * Adds a product to the user's cart.
     *
     * @param userId    ID of the user.
     * @param productId ID of the product to add to the cart.
     * @throws IllegalArgumentException if the user or product is not found.
     */
    public void addProductToCart(Integer userId, Integer productId) {
        executeTransaction(() -> {
            User user = userDAO.findById(userId);
            if (user == null) {
                throw new IllegalArgumentException("User not found with ID: " + userId);
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
                throw new IllegalArgumentException("Product not found with ID: " + productId);
            }

            for(Product item: cart.getProducts()) {
                if (item.getId() == product.getId()) {
                    throw new IllegalArgumentException("Product is already in cart" + productId);
                }
            }
            cart.getProducts().add(product);
            cartDAO.update(cart);
            return null;
        });
    }

    /**
     * Removes a product from the user's cart.
     *
     * @param userId    ID of the user.
     * @param productId ID of the product to remove from the cart.
     * @throws IllegalArgumentException if the user or product is not found, or if the cart is not found for the user.
     */
    public void removeProductFromCart(Integer userId, Integer productId) {
        executeTransaction(() -> {
            User user = userDAO.findById(userId);
            if (user == null) {
                throw new IllegalArgumentException("User not found with ID: " + userId);
            }

            Cart cart = user.getCart();
            if (cart == null) {
                throw new IllegalArgumentException("Cart not found for user ID: " + userId);
            }

            Product product = productDAO.findById(productId);
            if (product == null) {
                throw new IllegalArgumentException("Product not found with ID: " + productId);
            }

            cart.getProducts().remove(product);
            cartDAO.update(cart);
            return null;
        });
    }

    /**
     * Retrieves the cart of a user by user ID.
     *
     * @param userId ID of the user.
     * @return Cart object associated with the user.
     * @throws IllegalArgumentException if the user is not found.
     */
    public Cart getCartByUserId(Integer userId) {
        return executeTransaction(() -> {
            User user = userDAO.findById(userId);
            if (user == null) {
                throw new IllegalArgumentException("User not found with ID: " + userId);
            }
            return user.getCart();
        });
    }

    /**
     * Clears the cart of a user by user ID.
     *
     * @param userId ID of the user.
     * @throws IllegalArgumentException if the user is not found.
     */
    public void clearCart(Integer userId) {
        executeTransaction(() -> {
            User user = userDAO.findById(userId);
            if (user == null) {
                throw new IllegalArgumentException("User not found with ID: " + userId);
            }

            Cart cart = user.getCart();
            if (cart != null) {
                cart.getProducts().clear();
                cartDAO.update(cart);
            }
            return null;
        });
    }
}


