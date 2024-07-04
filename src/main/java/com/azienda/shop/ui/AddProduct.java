package com.azienda.shop.ui;

import com.azienda.shop.businessLogic.*;
import com.azienda.shop.factories.ServiceFactory;
import com.azienda.shop.model.Author;
import com.azienda.shop.model.Genre;
import com.azienda.shop.model.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import javax.persistence.EntityManagerFactory;

import java.io.IOException;
import java.nio.file.Paths;

@WebServlet("/addproduct")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,  // 1 MB
        maxFileSize = 1024 * 1024 * 5,    // 5 MB
        maxRequestSize = 1024 * 1024 * 10 // 10 MB
)
public class AddProduct extends HttpServlet {
    ProductService productService;
    UserService userService;
    CartService cartService;
    AuthorService authorService;
    GenreService genreService;

    public void init() throws ServletException {
        EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
        ServiceFactory factory = ServiceFactory.getInstance(emf);
        this.cartService = factory.getCartService();
        this.productService = factory.getProductService();
        this.userService = factory.getUserService();
        this.authorService = factory.getAuthorService();
        this.genreService = factory.getGenreService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            String authorString = request.getParameter("author");
            if (authorString == null) {
                throw new ServletException("You need an Author");
            }
            Author author = authorService.findAuthorByName(authorString);
            if (author == null) {
                author = authorService.insert(new Author(authorString));
            }

            String genreString = request.getParameter("genre");
            if (genreString == null) {
                throw new ServletException("You need a Genre");
            }
            Genre genre = genreService.findGenreByName(genreString);
            if (genre == null) {
                genre = genreService.insert(new Genre(genreString));
            }

            String name = request.getParameter("name");
            Double price = Double.parseDouble(request.getParameter("price"));
            Integer quantity = Integer.parseInt(request.getParameter("quantity"));
            String description = request.getParameter("description");
            String language = request.getParameter("language");
            String imageURL = request.getParameter("imageURL");

            Product product = new Product(name, author, genre, price, quantity, description, language, imageURL);
            productService.createProduct(product);

            request.getRequestDispatcher("/admincatalogue").forward(request, response);

        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
