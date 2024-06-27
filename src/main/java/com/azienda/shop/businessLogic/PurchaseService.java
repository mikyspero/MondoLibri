package com.azienda.shop.businessLogic;

import com.azienda.shop.dao.PurchaseDAO;
import com.azienda.shop.dao.CartDAO;
import com.azienda.shop.dao.ProductDAO;
import com.azienda.shop.model.Purchase;
import com.azienda.shop.model.Cart;
import com.azienda.shop.model.Product;
import com.azienda.shop.model.User;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

public class PurchaseService extends AbstractService<Purchase> {
    private CartDAO cartDAO;
    private ProductDAO productDAO;

    public PurchaseService(EntityManager manager, PurchaseDAO purchaseDAO, CartDAO cartDAO, ProductDAO productDAO) {
        super(manager, purchaseDAO);
        this.cartDAO = cartDAO;
        this.productDAO = productDAO;
    }

    public Purchase createPurchase(User user, Product product, int quantity) throws Exception {
        return executeTransaction(() -> {
            // Check if there's enough stock
            if (product.getQuantity() < quantity) {
                throw new IllegalStateException("Not enough stock for product: " + product.getName());
            }

            // Create the purchase
            Purchase purchase = new Purchase(new Date(), product, user);
            Purchase createdPurchase = insert(purchase);

            // Update product stock
            product.setQuantity(product.getQuantity() - quantity);
            productDAO.update(product);

            // Remove the product from the user's cart if it exists
            Cart cart = cartDAO.findByUser(user);
            if (cart != null) {
                cart.getProducts().remove(product);
                cartDAO.update(cart);
            }

            return createdPurchase;
        });
    }

    public List<Purchase> getUserPurchaseHistory(User user) throws Exception {
        return executeTransaction(() -> ((PurchaseDAO)getDao()).findByUser(user));
    }

    public List<Purchase> getProductPurchaseHistory(Product product) throws Exception {
        return executeTransaction(() -> ((PurchaseDAO)getDao()).findByProduct(product));
    }

    public List<Purchase> getPurchasesByDateRange(Date startDate, Date endDate) throws Exception {
        return executeTransaction(() -> ((PurchaseDAO)getDao()).findByDateRange(startDate, endDate));
    }

    public Long getProductPurchaseCount(Product product) throws Exception {
        return executeTransaction(() -> ((PurchaseDAO)getDao()).countPurchasesByProduct(product));
    }

    public List<Purchase> getRecentPurchases(int count) throws Exception {
        return executeTransaction(() -> ((PurchaseDAO)getDao()).findMostRecentPurchases(count));
    }

    @Override
    public Purchase retrieveById(Integer purchaseId)  {
        Purchase purchase = super.retrieveById(purchaseId);
        if (purchase == null) {
            throw new IllegalArgumentException("Purchase not found with id: " + purchaseId);
        }
        return purchase;
    }

    public void cancelPurchase(Integer purchaseId) throws Exception {
        executeTransaction(() -> {
            Purchase purchase = retrieveById(purchaseId);

            // Restore product stock
            Product product = purchase.getProduct();
            product.setQuantity(product.getQuantity() + 1); // Assuming quantity is always 1
            productDAO.update(product);

            // Delete the purchase
            delete(purchase);
        });
    }

    // Additional method to get total revenue
    public double getTotalRevenue() throws Exception {
        return executeTransaction(() -> {
            List<Purchase> allPurchases = retrieveAll();
            return allPurchases.stream()
                    .mapToDouble(purchase -> purchase.getProduct().getPrice())
                    .sum();
        });
    }
}
