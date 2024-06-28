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

@WebServlet(value="/init",loadOnStartup = 1 )
public class InitServlet extends HttpServlet{
    @Override
    public void init() throws ServletException {
        EntityManager manager=null;
        try {
            EntityManagerFactory factory=Persistence.createEntityManagerFactory("Shop");
            manager =factory.createEntityManager();
            System.out.println("connessione OK");
            UserDAO userDAO = new UserDAO(manager);
            UserService userService=new UserService(manager, userDAO, new CartDAO(manager));
            getServletContext().setAttribute("userService", userService);
        } catch (Exception e) {
            e.printStackTrace();
            manager.close();
            System.exit(0);
        }


    }
}
