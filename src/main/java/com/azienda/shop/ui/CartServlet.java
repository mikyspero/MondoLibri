package com.azienda.shop.ui;

import com.azienda.shop.businessLogic.CartService;
import com.azienda.shop.businessLogic.ProductService;
import com.azienda.shop.businessLogic.UserService;
import com.azienda.shop.dao.CartDAO;
import com.azienda.shop.dao.ProductDAO;
import com.azienda.shop.dao.UserDAO;
import com.azienda.shop.factories.ServiceFactory;
import com.azienda.shop.model.Cart;
import com.azienda.shop.model.Product;
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
import java.util.List;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {
    ProductService productService;
    UserService userService;
    CartService cartService;

    @Override
    public void init() throws ServletException {
        EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
        ServiceFactory factory = ServiceFactory.getInstance(emf);
        this.cartService = factory.getCartService();
        this.userService = factory.getUserService();
    }




//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        resp.sendRedirect(req.getContextPath() + "/jsp/privata/cart.jsp");
//    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            HttpSession session = req.getSession();
            String username = (String) session.getAttribute("username");
            if (username != null) {
                User user = userService.findByUsername(username);
                if (user != null) {
                    Integer userid = user.getId();
                    Cart toBePassed = userService.GetRelatedCart(userid);
                    req.setAttribute("cart", toBePassed);
                }
            }
            // Reindirizza alla pagina del carrello invece di fare un forward
            req.getRequestDispatcher("/jsp/privata/cart.jsp").forward(req, resp);
            // resp.sendRedirect(req.getContextPath() + "/jsp/privata/cart.jsp");
//            String s = req.getContextPath() + "/jsp/privata/cart.jsp";
//            System.out.println(s);
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            // Gestisci l'errore, magari reindirizzando a una pagina di errore
            resp.sendRedirect(req.getContextPath() + "/error");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            // Gestisci l'errore, magari reindirizzando a una pagina di errore
            resp.sendRedirect(req.getContextPath() + "/error");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
