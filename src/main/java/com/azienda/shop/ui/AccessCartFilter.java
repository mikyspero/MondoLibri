package com.azienda.shop.ui;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter("/cart")
public class AccessCartFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        try {
            String username = (String) httpRequest.getSession().getAttribute("username");

            if (username == null) {
                // L'utente non è loggato, reindirizza alla pagina di login
                httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
            } else {
                // L'utente è loggato, permetti l'accesso al carrello
                chain.doFilter(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // In caso di errore, reindirizza a una pagina di errore
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/error");
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Inizializzazione del filtro, se necessario
    }

    @Override
    public void destroy() {
        // Pulizia delle risorse, se necessario
    }
}