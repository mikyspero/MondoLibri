package com.azienda.shop.ui;

import com.azienda.shop.businessLogic.UserService;
import com.azienda.shop.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            UserService service = (UserService) getServletContext().getAttribute("userService");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            String address = request.getParameter("address");

            User user = User.createInstance(username,password,email,address);
            service.register(user);
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            response.sendRedirect("index");

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/registration.jsp").forward(request, response);
    }
}
