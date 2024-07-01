package com.azienda.shop.ui;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.azienda.shop.businessLogic.UserService;
import com.azienda.shop.dao.CartDAO;
import com.azienda.shop.dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

@WebServlet(value="/init", loadOnStartup = 1)
public class InitServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("Shop");
            getServletContext().setAttribute("emf", emf);
            System.out.println("Initialization successful");
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Failed to initialize application", e);
        }
    }
}

