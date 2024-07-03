package com.azienda.shop.ui;
import com.azienda.shop.businessLogic.CartService;
import com.azienda.shop.businessLogic.UserService;
import com.azienda.shop.factories.ServiceFactory;
import com.azienda.shop.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.hibernate.service.spi.ServiceException;
import javax.persistence.EntityManagerFactory;
import java.io.IOException;
import java.util.List;

@WebServlet("/alluser")
public class UsersServlet extends HttpServlet {
    UserService userService;
    CartService cartService;

    public void init() throws ServletException {
        EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
        ServiceFactory factory = ServiceFactory.getInstance(emf);
        this.cartService = factory.getCartService();
        this.userService = factory.getUserService();
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            List<User> user = userService.retrieveAll();
            request.setAttribute("user", user);
            request.getRequestDispatcher("/jsp/privata/admin/allusers.jsp").forward(request, response);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }

    }
}
