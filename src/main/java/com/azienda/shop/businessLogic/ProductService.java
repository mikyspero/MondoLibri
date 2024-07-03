package com.azienda.shop.businessLogic;

import com.azienda.shop.dao.CartDAO;
import com.azienda.shop.dao.ProductDAO;
import com.azienda.shop.model.Cart;
import com.azienda.shop.model.Product;
import com.azienda.shop.model.User;
import com.azienda.shop.exceptions.ProductNotFoundException;
import com.azienda.shop.utils.BlobConverter;
import java.sql.Blob;
import javax.persistence.EntityManager;
import java.util.List;

/**
 * Service class that provides business logic operations related to products in the system.
 * It manages CRUD operations for products, handles product availability updates, and product searches.
 */
public class ProductService extends AbstractService<Product> {

    private CartDAO cartDAO; // Data Access Object for Cart entities

    /**
     * Constructor to initialize ProductService with necessary dependencies.
     * @param manager EntityManager instance for managing entity persistence.
     * @param dao ProductDAO instance for data access operations related to Product entities.
     * @param cartDAO CartDAO instance for data access operations related to Cart entities.
     */
    public ProductService(EntityManager manager, ProductDAO dao, CartDAO cartDAO) {
        super(manager, dao);
        this.cartDAO = cartDAO;
    }

    /**
     * Checks if a product with the given name already exists in the database.
     * @param nameToBeChecked Name of the product to check for existence.
     * @return True if the product with the name already exists, false otherwise.
     * @throws ProductNotFoundException If the product with the given name is not found.
     */
    private boolean doesProductExist(String nameToBeChecked) {
        Product sameName = ((ProductDAO) this.getDao()).findByName(nameToBeChecked);
        if (sameName != null) {
            throw new ProductNotFoundException(nameToBeChecked);
        }
        return true;
    }

    /**
     * Creates a new product in the database.
     * @param product Product object to be created.
     * @return The created Product object.
     * @throws Exception If an error occurs during the creation process.
     */
    public Product createProduct(Product product) throws Exception {
        return executeTransaction(() -> {
            Product sameName = ((ProductDAO) this.getDao()).findByName(product.getName());
            if (sameName != null) {
                throw new RuntimeException(sameName.getName() + " is already in the DB");
            }
            return ((ProductDAO) getDao()).create(product);
        });
    }

    public Product createProduct(Product product, String imagePath) throws Exception {
        return executeTransaction(() -> {
            try {
                Product sameName = ((ProductDAO) this.getDao()).findByName(product.getName());
                if (sameName != null) {
                    throw new RuntimeException(sameName.getName() + " is already in the DB");
                }
                if (imagePath != null && !imagePath.isEmpty()) {
                    Blob imageBlob = null;
                    imageBlob = BlobConverter.generateBlob(imagePath);
                    product.setImage(imageBlob);
                }
                return ((ProductDAO) getDao()).create(product);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }



    /**
     * Updates an existing product in the database.
     * @param product Updated Product object.
     * @param user User performing the operation.
     * @return The updated Product object.
     */
    public Product updateProduct(Product product, User user) {
        return executeTransaction(() -> {
            Product existingProduct = ((ProductDAO) getDao()).findById(product.getId());
            if (existingProduct == null) {
                throw new ProductNotFoundException("Product not found");
            }
            return ((ProductDAO) getDao()).update(product);
        });
    }

    /**
     * Deletes a product from the database.
     * @param productId ID of the product to be deleted.
     */
    public void deleteProduct(Integer productId) {
        executeTransaction(() -> {
            Product product = ((ProductDAO) getDao()).findById(productId);
            if (product == null) {
                throw new ProductNotFoundException("Product not found");
            }
            List<Cart> allCarts = cartDAO.findAll();
            for (Cart cart : allCarts) {
                cart.getProducts().remove(product);
                cartDAO.update(cart);
            }
            ((ProductDAO) getDao()).delete(product);
        });
    }

    /**
     * Searches for products in the database by name using a keyword.
     * @param keyword Keyword to search for in product names.
     * @return List of Product objects matching the search criteria.
     */
    public List<Product> search(String keyword) {
        return executeTransaction(() -> ((ProductDAO) getDao()).search(keyword));
    }

    public List<Product> search(String keyword, Double minPrice, Double maxPrice) {
        return executeTransaction(() -> ((ProductDAO) getDao()).search(keyword, minPrice, maxPrice));

    }

    /**
     * Searches for products in the database by price range.
     * @param minPrice Minimum price of the products to search for.
     * @param maxPrice Maximum price of the products to search for.
     * @return List of Product objects within the specified price range.
     */
    public List<Product> search(Double minPrice, Double maxPrice) {
        return executeTransaction(() -> ((ProductDAO) getDao()).search(minPrice, maxPrice));
    }

    /**
     * Updates the availability (quantity) of a product after a purchase.
     * @param productId ID of the product to update.
     * @param quantitySold Quantity of the product sold in the transaction.
     */
    public void updateProductAvailability(Integer productId, int quantitySold) {
        executeTransaction(() -> {
            Product product = ((ProductDAO) getDao()).findById(productId);
            if (product == null) {
                throw new ProductNotFoundException("Product not found");
            }
            int newQuantity = product.getQuantity() - quantitySold;
            if (newQuantity < 0) {
                throw new IllegalStateException("Not enough products in stock");
            }
            product.setQuantity(newQuantity);
            return ((ProductDAO) getDao()).update(product);
        });
    }
}

