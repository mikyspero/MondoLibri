package com.azienda.shop.factories;

import com.azienda.shop.businessLogic.*;
import com.azienda.shop.dao.*;
import com.azienda.shop.model.Genre;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class ServiceFactory implements AutoCloseable {
    private final EntityManager em;
    private final DaoFactory daoFactory;

    // Services
    private CartService cartService;
    private ProductService productService;
    private PurchaseService purchaseService;
    private UserService userService;
    private RoleService roleService;
    private AuthorService authorService;
    private GenreService genreService;

    private ServiceFactory(EntityManagerFactory emf) {
        this.em = emf.createEntityManager();
        this.daoFactory = new DaoFactory(em);
    }

    public static ServiceFactory getInstance(EntityManagerFactory emf) {
        return new ServiceFactory(emf);
    }

    public CartService getCartService() {
        if (cartService == null) {
            cartService = new CartService(
                    em,
                    daoFactory.getCartDAO(),
                    daoFactory.getProductDAO(),
                    daoFactory.getUserDAO()
            );
        }
        return cartService;
    }

    public ProductService getProductService() {
        if (productService == null) {
            productService = new ProductService(
                    em,
                    daoFactory.getProductDAO(),
                    daoFactory.getCartDAO()
            );
        }
        return productService;
    }

    public PurchaseService getPurchaseService() {
        if (purchaseService == null) {
            purchaseService = new PurchaseService(
                    em,
                    daoFactory.getPurchaseDAO(),
                    daoFactory.getCartDAO(),
                    daoFactory.getProductDAO()
            );
        }
        return purchaseService;
    }

    public UserService getUserService() {
        if (userService == null) {
            userService = new UserService(
                    em,
                    daoFactory.getUserDAO(),
                    daoFactory.getCartDAO()
            );
        }
        return userService;
    }

    public RoleService getRoleService() {
        if (roleService == null) {
            roleService = new RoleService(
                    em,
                    daoFactory.getRoleDAO()
            );
        }
        return roleService;
    }

    public AuthorService getAuthorService() {
        if (authorService == null) {
            authorService = new AuthorService(
                    em,
                    daoFactory.getAuthorDAO()
            );
        }
        return authorService;
    }

    public GenreService getGenreService() {
        if (genreService == null) {
            genreService = new GenreService(
                    em,
                    daoFactory.getGenreDAO()
            );
        }
        return genreService;
    }

    @Override
    public void close() {
        if (em != null && em.isOpen()) {
            em.close();
        }
    }

    private static class DaoFactory {
        private final EntityManager em;
        private CartDAO cartDAO;
        private ProductDAO productDAO;
        private PurchaseDAO purchaseDAO;
        private UserDAO userDAO;
        private RoleDAO roleDAO;
        private AuthorDAO authorDAO;
        private GenreDAO genreDAO;

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

        public RoleDAO getRoleDAO() {
            if (roleDAO == null) {
                roleDAO = new RoleDAO(em);
            }
            return roleDAO;
        }

        public AuthorDAO getAuthorDAO() {
            if (authorDAO == null) {
                authorDAO = new AuthorDAO(em);
            }
            return authorDAO;
        }

        public GenreDAO getGenreDAO() {
            if (genreDAO == null) {
                genreDAO = new GenreDAO(em);
            }
            return genreDAO;
        }
    }
}