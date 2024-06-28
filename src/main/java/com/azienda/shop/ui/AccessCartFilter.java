package com.azienda.shop.ui;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

@WebFilter("/cart")
public class AccessCartFilter implements Filter {

    @Override
    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
            throws IOException, ServletException {
        try {
            HttpServletRequest httpRequest=(HttpServletRequest) arg0;
            //chiave
            String username=(String)httpRequest.getSession().getAttribute("username");

            //
            if(username == null) {
                arg2.doFilter(arg0, arg1);
            }else {
                //
                arg0.getRequestDispatcher("/html/privata/cart.jsp").forward(arg0, arg1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            //ti butto fuori
//            arg0.getRequestDispatcher("/html/pubblica/Login.html").forward(arg0, arg1);
        }

    }

}
