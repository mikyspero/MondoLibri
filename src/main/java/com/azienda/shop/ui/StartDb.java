//package com.azienda.shop.ui;
//
//import com.azienda.shop.businessLogic.*;
//import com.azienda.shop.dao.*;
//import com.azienda.shop.model.*;
//import org.hibernate.service.Service;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;
//import java.security.NoSuchAlgorithmException;
//import java.util.List;
//
//public class StartDb {
//
//    public static void main(String[] args){
//        EntityManager entityManager = null;
//        try{
//            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Shop");
//            entityManager = entityManagerFactory.createEntityManager();
//            System.out.println("Creazione db effettuata");
//
//            //Role ROLE_ADMIN = new Role("ADMIN");
//            //Role PAolo = new Role("PAOLO");
//            UserDAO dao = new UserDAO(entityManager);
//            CartDAO cartDao = new CartDAO(entityManager);
//            ProductDAO productDao = new ProductDAO(entityManager);
//            PurchaseDAO purchaseDao = new PurchaseDAO(entityManager);
//            UserService service = new UserService(entityManager, dao, cartDao);
//            CartService cartService = new CartService(entityManager,cartDao, productDao, dao);
//            ProductService productService = new ProductService(entityManager, productDao,cartDao);
//            PurchaseService purchaseService = new PurchaseService(entityManager,purchaseDao,cartDao,productDao);
//
//            User u = User.createInstance("via ","aadsdafaafdcsd@as.com","234","adwafeazxcvb");
//            //u = service.register(u);
//            // Perform login test
//            User loggedInUser = service.loginWithEmail("aadsdafaafdcsd@as.com", "234");
//
//            try {
//                if (loggedInUser != null) {
//                    System.out.println("Login successful for user: " + loggedInUser.getUsername());
//                } else {
//                    System.out.println("Login failed: Invalid credentials");
//
//                }
//            }  catch (RuntimeException e) {
//                System.out.println("Login failed: " + e.getMessage());
//                e.printStackTrace();
//            }
//            List<User> roles = service.retrieveAll();
//            System.out.println(roles.size());
//            for(User role1 : roles){
//                List<Product> l = role1.getCart().getProducts();
//                for(Product product : l){
//                    System.out.println(product.getName());
//                }
//            }
//            // Perform purchases
//            performPurchase(purchaseService, service.retrieveById(1), productService.retrieveById(1), 1);
//            performPurchase(purchaseService, service.retrieveById(1),productService.retrieveById(2), 2);
//            performPurchase(purchaseService, service.retrieveById(2), productService.retrieveById(1), 1);
//
//            // Retrieve and print user purchase history
//            System.out.println("User 1 Purchase History:");
//            List<Purchase> user1Purchases = purchaseService.getUserPurchaseHistory(service.retrieveById(1));
//            printPurchaseHistory(user1Purchases);
//
//            System.out.println("\nUser 2 Purchase History:");
//            List<Purchase> user2Purchases = purchaseService.getUserPurchaseHistory(service.retrieveById(2));
//            printPurchaseHistory(user2Purchases);
//
//            // Get and print total revenue
//            double totalRevenue = purchaseService.getTotalRevenue();
//            System.out.println("\nTotal Revenue: $" + totalRevenue);
//
//
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//        finally{
//            if ( entityManager != null ) {
//                entityManager.close();
//            }
//            System.exit(0);
//        }
//
//    }
//
//
//    private static void testAddProductToCart(CartService cartService) {
//        try {
//            // Simulate adding a product to a user's cart
//            Integer userId = 1; // Replace with an existing user ID in your database
//            Integer productId = 5; // Replace with an existing product ID in your database
//
//            cartService.addProductToCart(userId, productId);
//            System.out.println("Product added to cart successfully.");
//        } catch (IllegalArgumentException e) {
//            System.err.println("Failed to add product to cart: " + e.getMessage());
//        }
//    }
//
//    private static void testRemoveProductFromCart(CartService cartService) {
//        try {
//            // Simulate removing a product from a user's cart
//            Integer userId = 1; // Replace with an existing user ID in your database
//            Integer productId = 5; // Replace with an existing product ID in your database
//
//            cartService.removeProductFromCart(userId, productId);
//            System.out.println("Product removed from cart successfully.");
//        } catch (IllegalArgumentException e) {
//            System.err.println("Failed to remove product from cart: " + e.getMessage());
//        }
//    }
//
//    private static void testGetCartByUserId(CartService cartService) {
//        try {
//            // Simulate retrieving a cart by user ID
//            Integer userId = 1; // Replace with an existing user ID in your database
//
//            Cart cart = cartService.getCartByUserId(userId);
//            if (cart != null) {
//                System.out.println("Cart retrieved successfully for user ID " + userId);
//            } else {
//                System.out.println("Cart not found for user ID " + userId);
//            }
//        } catch (IllegalArgumentException e) {
//            System.err.println("Failed to retrieve cart: " + e.getMessage());
//        }
//    }
//
//    private static void testClearCart(CartService cartService) {
//        try {
//            // Simulate clearing a user's cart
//            Integer userId = 1; // Replace with an existing user ID in your database
//
//            cartService.clearCart(userId);
//            System.out.println("Cart cleared successfully for user ID " + userId);
//        } catch (IllegalArgumentException e) {
//            System.err.println("Failed to clear cart: " + e.getMessage());
//        }
//    }
//
//    private static void performPurchase(PurchaseService purchaseService, User user, Product product, int quantity) {
//        try {
//            purchaseService.createPurchase(user, product);
//            System.out.println("Purchase created successfully for User " + user.getUsername() +
//                    " - Product: " + product.getName() + " (Quantity: " + quantity + ")");
//        } catch (Exception e) {
//            System.err.println("Failed to create purchase: " + e.getMessage());
//        }
//    }
//
//
//    private static void printPurchaseHistory(List<Purchase> purchases) {
//        for (Purchase purchase : purchases) {
//            System.out.println(purchase);
//        }
//    }
//}