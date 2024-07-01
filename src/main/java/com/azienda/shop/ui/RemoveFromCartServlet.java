package com.azienda.shop.ui;

import com.azienda.shop.businessLogic.CartService;
import com.azienda.shop.businessLogic.UserService;
import com.azienda.shop.dao.CartDAO;
import com.azienda.shop.dao.ProductDAO;
import com.azienda.shop.dao.UserDAO;
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

@WebServlet("/removecart")
public class RemoveFromCartServlet extends HttpServlet {

    CartService cartService;
    UserService userService;

    @Override
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            HttpSession session = req.getSession();
            Integer productId = Integer.parseInt(req.getParameter("id"));
            String username = (String) session.getAttribute("username");
            if (username == null) {
                throw new ServletException("missing username");
            }
            User user = userService.findByUsername(username);
            if (user == null) {
                throw new ServletException("requested user not found");
            }
            Integer userId = user.getId();
            cartService.removeProductFromCart(userId, productId);
            Cart updatedCart = userService.GetRelatedCart(userId);
            req.setAttribute("cart", updatedCart);
            // Redirect to the cart page
            resp.sendRedirect(req.getContextPath() + "/jsp/privata/cart.jsp");
        }catch (ServletException e) {
            System.out.println("Error parsing User Data: " + e.getMessage());
            e.printStackTrace();
            resp.sendRedirect(req.getContextPath() + "/error");
        }
        catch (NumberFormatException e) {
            System.out.println("Error parsing product ID: " + e.getMessage());
            e.printStackTrace();
            resp.sendRedirect(req.getContextPath() + "/error");
        } catch (RuntimeException e) {
            System.out.println("Error removing product from cart: " + e.getMessage());
            e.printStackTrace();
            resp.sendRedirect(req.getContextPath() + "/error");
        }
    }
}