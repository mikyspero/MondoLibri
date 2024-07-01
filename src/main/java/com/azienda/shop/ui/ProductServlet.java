package com.azienda.shop.ui;

import com.azienda.shop.businessLogic.ProductService;
import com.azienda.shop.model.Product;
import com.azienda.shop.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/product")
public class ProductServlet extends HttpServlet {

    private ProductService productService;

    @Override
    public void init() throws ServletException {
        // Initialize ProductService here
        // This is a placeholder; you'll need to properly initialize it with EntityManager, ProductDAO, and CartDAO
        this.productService = new ProductService(null, null, null);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return;
        }

        try {
            switch (action) {
                case "create":
                    createProduct(req, resp, user);
                    break;
                case "update":
                   // updateProduct(req, resp, user);
                    break;
                case "delete":
                    deleteProduct(req, resp, user);
                    break;
                default:
            }
        } catch (Exception e) {
        }
    }

    private void createProduct(HttpServletRequest req, HttpServletResponse resp, User user) throws Exception {
        String name = req.getParameter("name");
        Double price = Double.parseDouble(req.getParameter("price"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        String description = req.getParameter("description");
        String language = req.getParameter("language");
        Product product = new Product(name, price, quantity, description, language);
        Product createdProduct = productService.createProduct(product, user);
    }



    private void deleteProduct(HttpServletRequest req, HttpServletResponse resp, User user) throws Exception {
        Integer id = Integer.parseInt(req.getParameter("id"));
        productService.deleteProduct(id, user);
    }
}