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

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        UserService service = (UserService) request.getServletContext().getAttribute("userService");
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
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
    }
}
