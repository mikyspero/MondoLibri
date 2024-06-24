package com.azienda.shop.ui;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/index")
public class Index extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (username != null && password != null) {
            // Validate username and password (replace with your authentication logic)
            boolean isValidLogin = validateLogin(username, password);

            if (isValidLogin) {
                // Login successful (redirect or send success response)
                resp.sendRedirect("/success"); // Replace with your success page
            } else {
                // Login failed (send an error message)
                resp.setContentType("text/html");
                resp.getWriter().println("Invalid username or password.");
            }
        } else {
            // Handle missing username or password
            resp.setContentType("text/html");
            resp.getWriter().println("Username or password is missing.");
        }
    }

    // Implement your authentication logic here (replace with database lookup, etc.)
    private boolean validateLogin(String username, String password) {
        // ... (your validation logic)
        return true; // Replace with actual validation
    }
}
