package com.azienda.shop.ui;

import com.azienda.shop.businessLogic.CartService;
import com.azienda.shop.businessLogic.ProductService;
import com.azienda.shop.businessLogic.UserService;
import com.azienda.shop.dao.CartDAO;
import com.azienda.shop.dao.ProductDAO;
import com.azienda.shop.dao.UserDAO;
import com.azienda.shop.model.Cart;
import com.azienda.shop.model.User;

import com.azienda.shop.model.Cart;
import com.azienda.shop.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;

@WebServlet("/index")
public class IndexServlet extends HttpServlet {
    UserService userService;
    CartService cartService;
    private static final long serialVersionUID = 1L;

    public void init() throws ServletException {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Shop");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        ProductDAO productDAO = new ProductDAO(entityManager);
        UserDAO userDAO = new UserDAO(entityManager);
        CartDAO cartDAO = new CartDAO(entityManager);

        this.cartService = new CartService(entityManager, cartDAO, productDAO, userDAO);
        this.userService = new UserService(entityManager, userDAO, cartDAO);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        if (username != null) {
            User user = userService.findByUsername(username);
            if (user != null) {
                Integer userid = user.getId();
                Cart toBePassed = userService.GetRelatedCart(userid);
                request.setAttribute("cart", toBePassed);
            }
        }
//        HttpSession session = request.getSession(false);
//        if (session == null || session.getAttribute("username") == null) {
//            response.sendRedirect("index");
//            return;
//        }

        request.getRequestDispatcher("/jsp/index.jsp").forward(request, response);
    }
}
