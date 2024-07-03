package com.azienda.shop.ui;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/alluser")
public class AccessAdminFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);
        Object usernameAttr = session.getAttribute("username");
        if(usernameAttr == null) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/notadmin");
            return;
        }
        String username = usernameAttr.toString();
        if (username == null || !("admin").equals(username)) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/notadmin");
            return;
        }
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void destroy() {}
}