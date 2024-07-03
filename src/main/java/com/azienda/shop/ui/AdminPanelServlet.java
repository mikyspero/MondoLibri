package com.azienda.shop.ui;

import com.azienda.shop.businessLogic.CartService;
import com.azienda.shop.businessLogic.ProductService;
import com.azienda.shop.businessLogic.UserService;
import com.azienda.shop.factories.ServiceFactory;
import com.azienda.shop.model.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

import javax.persistence.EntityManagerFactory;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admincatalogue")
public class AdminPanelServlet extends HttpServlet {
    ProductService productService;
    UserService userService;
    CartService cartService;

    @Override
    public void init() throws ServletException {
        EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
        ServiceFactory factory = ServiceFactory.getInstance(emf);
        this.cartService = factory.getCartService();
        this.userService = factory.getUserService();
        this.productService = factory.getProductService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String keyword = request.getParameter("keyword");
            Double minPrice = parsePrice(request.getParameter("minPrice"));
            Double maxPrice = parsePrice(request.getParameter("maxPrice"));
            List<Product> products = searchProducts(keyword, minPrice, maxPrice);
            for (Product product : products) {
                System.out.println(product);
            }
            System.out.println(products.size());
            request.setAttribute("products", products);
            request.getRequestDispatcher("/jsp/privata/admin/admincatalogo.jsp").forward(request, response);
        } catch (jakarta.servlet.ServletException e) {
            e.printStackTrace();
        }
    }

    private Double parsePrice(String priceStr) {
        try {
            return priceStr != null && !priceStr.isEmpty() ? Double.valueOf(priceStr) : null;
        } catch (NumberFormatException e) {
            // Log the exception
            return null;
        }
    }

    private List<Product> searchProducts(String keyword, Double minPrice, Double maxPrice) {
        boolean hasKeyword = keyword != null && !keyword.isEmpty();
        boolean hasPriceRange = minPrice != null && maxPrice != null;

        if (hasKeyword && hasPriceRange) {
            return productService.search(keyword, minPrice, maxPrice);
        } else if (hasKeyword) {
            return productService.search(keyword);
        } else if (hasPriceRange) {
            return productService.search(minPrice, maxPrice);
        } else {
            return productService.retrieveAll();
        }
    }

}
