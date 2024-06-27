package com.azienda.shop.businessLogic;

import com.azienda.shop.dao.CartDAO;
import com.azienda.shop.dao.ProductDAO;
import com.azienda.shop.model.Cart;
import com.azienda.shop.model.Product;
import com.azienda.shop.model.User;
import com.azienda.shop.exceptions.ProductNotFoundException;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

public class ProductService extends AbstractService<Product> {

    private CartDAO cartDAO;


    public ProductService(EntityManager manager, ProductDAO dao) {
        super(manager, dao);
    }



    private boolean doesProductExist (String nameToBeChecked){
        Product sameName = ((ProductDAO) this.getDao()).findByName(nameToBeChecked);
        if (sameName != null) {
            throw new ProductNotFoundException(nameToBeChecked);
        }
        return true;
    }

    public Product createProduct(Product product, User user) throws Exception {
       return  executeTransaction(()->{
           Product sameName = ((ProductDAO) this.getDao()).findByName(product.getName());
           if (sameName != null) {
               throw new RuntimeException(sameName.getName() + " is already in the DB");
           }
           return ((ProductDAO) getDao()).create(product);
        });
    }

    public Product updateProduct(Product product, User user) {
        return executeTransaction(() -> {
            Product existingProduct = ((ProductDAO) getDao()).findById(product.getId());
            if (existingProduct == null) {
                throw new ProductNotFoundException("Product not found");
            }
            return ((ProductDAO) getDao()).update(product);
        });
    }

    public void deleteProduct(Integer productId, User user) {
        executeTransaction(() -> {
            Product product = ((ProductDAO) getDao()).findById(productId);
            if (product == null) {
                throw new ProductNotFoundException("Product not found");
            }
            List<Cart> allCarts = cartDAO.findAll();
            if (product == null) {
                throw new IllegalArgumentException("Product not found");
            }
            for (Cart cart : allCarts) {
                cart.getProducts().remove(product);
                cartDAO.update(cart);
            }
            ((ProductDAO) getDao()).delete(product);
        });
    }

    public List<Product> searchProducts(String keyword) {
        return executeTransaction(() -> ((ProductDAO) getDao()).searchByName(keyword));
    }

    public List<Product> searchProducts(Double minPrice, Double maxPrice) {
        return executeTransaction(() -> ((ProductDAO) getDao()).searchByPriceBounds(minPrice, maxPrice));
    }

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