package com.azienda.shop.ui;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

@WebFilter("/html/privata/*")
public class AccessAdminFilter implements Filter {

    @Override
    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
            throws IOException, ServletException {
        try {
            HttpServletRequest httpRequest=(HttpServletRequest) arg0;
            //chiave
            String sessione=(String)httpRequest.getSession().getAttribute("loggato");

            //se ti sei loggato ti faccio andare avanti
            if(sessione != null) {
                //ti butto fuori
                arg0.getRequestDispatcher("/html/pubblica/Login.html").forward(arg0, arg1);

            }else {
                arg2.doFilter(arg0, arg1);

            }
        } catch (Exception e) {
            e.printStackTrace();
            //ti butto fuori
            arg0.getRequestDispatcher("/html/pubblica/Login.html").forward(arg0, arg1);
        }

    }

}
