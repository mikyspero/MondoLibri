package com.azienda.shop.ui;

import com.azienda.shop.businessLogic.CartService;
import com.azienda.shop.businessLogic.ProductService;
import com.azienda.shop.businessLogic.UserService;
import com.azienda.shop.dao.CartDAO;
import com.azienda.shop.dao.ProductDAO;
import com.azienda.shop.dao.UserDAO;
import com.azienda.shop.model.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.util.List;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {
    ProductService productService;
    UserService userService;
    CartService cartService;

    @Override
    public void init() throws ServletException {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Shop");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        ProductDAO productDAO = new ProductDAO(entityManager);
        UserDAO userDAO = new UserDAO(entityManager);
        CartDAO cartDAO = new CartDAO(entityManager);

        CartService cartService = new CartService(entityManager, cartDAO, productDAO, userDAO);
        ProductService productService = new ProductService(entityManager, productDAO, cartDAO);
        UserService userService = new UserService(entityManager, userDAO, cartDAO);
    }




    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        req.getRequestDispatcher("/jsp/catalogo.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
