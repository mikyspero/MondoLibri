package com.azienda.shop.ui;

import com.azienda.shop.businessLogic.ProductService;
import com.azienda.shop.businessLogic.PurchaseService;
import com.azienda.shop.businessLogic.RoleService;
import com.azienda.shop.businessLogic.UserService;
import com.azienda.shop.dao.*;
import com.azienda.shop.model.Role;
import com.azienda.shop.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    PurchaseService purchaseService;
    ProductService productService;
    UserService userService;
    RoleService roleService;

    @Override
    public void init() throws ServletException {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Shop");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        ProductDAO productDAO = new ProductDAO(entityManager);
        CartDAO cartDAO = new CartDAO(entityManager);
        PurchaseDAO purchaseDAO = new PurchaseDAO(entityManager);
        UserDAO userDAO = new UserDAO(entityManager);
        RoleDAO roleDAO = new RoleDAO(entityManager);
        purchaseService = new PurchaseService(entityManager, purchaseDAO, cartDAO, productDAO);
        productService = new ProductService(entityManager, productDAO, cartDAO);

        this.productService = new ProductService(entityManager, productDAO, cartDAO);
        this.userService = new UserService(entityManager, userDAO, cartDAO);
        this.roleService = new RoleService(entityManager, roleDAO);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            String address = request.getParameter("address");
            Role role = roleService.retrieveById(2);

            User user = User.createInstance(role,username,password,email,address);
            userService.register(user);
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            response.sendRedirect("index");

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch(Exception e){

        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/registration.jsp").forward(request, response);
    }
}
