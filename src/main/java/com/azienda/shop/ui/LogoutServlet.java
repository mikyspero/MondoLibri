package com.azienda.shop.ui;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            HttpSession session = request.getSession(false); // Ottieni la sessione esistente senza crearne una nuova
            if (session != null) {
                session.invalidate(); // Invalida la sessione
            }
            response.sendRedirect("index"); // Reindirizza alla pagina di login
        } catch (IOException e) {
            response.sendRedirect("error");
            e.printStackTrace();
        } catch (Exception e){
            response.sendRedirect("error");
            e.printStackTrace();
        }
    }
}
