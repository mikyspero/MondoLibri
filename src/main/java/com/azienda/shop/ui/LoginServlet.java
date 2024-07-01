package com.azienda.shop.ui;

import com.azienda.shop.businessLogic.ProductService;
import com.azienda.shop.businessLogic.PurchaseService;
import com.azienda.shop.businessLogic.UserService;
import com.azienda.shop.dao.CartDAO;
import com.azienda.shop.dao.ProductDAO;
import com.azienda.shop.dao.PurchaseDAO;
import com.azienda.shop.dao.UserDAO;
import com.azienda.shop.factories.ServiceFactory;
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

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserService service;

    @Override
    public void init() throws ServletException {
        EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
        ServiceFactory factory = ServiceFactory.getInstance(emf);
        this.service = factory.getUserService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
//        User user = new User(username, password);
            service.loginWithUser(username, password);
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            response.sendRedirect("index");

            // Dummy authentication logic
//        if ("admin".equals(username) && "password".equals(password)) {
//            HttpSession session = request.getSession();
//            session.setAttribute("username", username);
//            response.sendRedirect("welcome");
//        } else {
//            request.setAttribute("errorMessage", "Invalid username or password");
//            request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
//        }
        } catch (IOException e) {

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
    }
}
