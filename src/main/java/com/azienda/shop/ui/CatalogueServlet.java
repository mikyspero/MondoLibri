package com.azienda.shop.ui;
import com.azienda.shop.businessLogic.ProductService;
import com.azienda.shop.dao.CartDAO;
import com.azienda.shop.dao.ProductDAO;
import com.azienda.shop.factories.ServiceFactory;
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
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@WebServlet("/index")
public class CatalogueServlet extends HttpServlet {
    ProductService productService;

    @Override
    public void init() throws ServletException {
        EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
        ServiceFactory factory = ServiceFactory.getInstance(emf);
        productService = factory.getProductService();
    }




    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String keyword = req.getParameter("keyword");
            Double minPrice = parsePrice(req.getParameter("minPrice"));
            Double maxPrice = parsePrice(req.getParameter("maxPrice"));
            List<Product> products = searchProducts(keyword, minPrice, maxPrice);
            for (Product product : products) {
                System.out.println(product);
            }
            System.out.println(products.size());
            req.setAttribute("products", products);
            req.getRequestDispatcher("/jsp/index.jsp").forward(req, resp);

        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e){

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

    private Double parsePrice(String priceStr) {
        try {
            return priceStr != null && !priceStr.isEmpty() ? Double.valueOf(priceStr) : null;
        } catch (NumberFormatException e) {
            // Log the exception
            return null;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
