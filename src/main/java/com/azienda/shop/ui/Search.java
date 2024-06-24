package com.azienda.shop.ui;

import com.azienda.shop.businessLogic.AbstractService;
import com.azienda.shop.businessLogic.ProductService;
import com.azienda.shop.dao.ProductDAO;
import com.azienda.shop.model.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.util.List;


@WebServlet ("/Search")
public class Search extends HttpServlet {

    @Override
    public void init() throws ServletException {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Shop");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        ProductDAO productDAO = new ProductDAO(entityManager);
        ProductService productService = new ProductService(entityManager, productDAO);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String query = req.getParameter("query");

        //AbstractService<Product> service = new ProductService();
        List<Product> products = productService.retrieveAll(query);

        req.setAttribute("products", products);
        req.getRequestDispatcher("/search.jsp").forward(req, resp);

    }



}
