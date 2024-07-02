package com.azienda.shop.businessLogic;

import com.azienda.shop.dao.CartDAO;
import com.azienda.shop.dao.UserDAO;
import com.azienda.shop.exceptions.AuthenticationException;
import com.azienda.shop.model.Cart;
import com.azienda.shop.model.User;
import com.azienda.shop.utils.PasswordHasher;

import javax.persistence.EntityManager;
import java.security.NoSuchAlgorithmException;
import java.util.function.Function;

/**
 * Service class for managing operations related to users.
 * Extends AbstractService to leverage common transactional and CRUD operations.
 */
public class UserService extends AbstractService<User> {

    private CartDAO cart;

    /**
     * Constructs a UserService instance.
     *
     * @param manager EntityManager instance for managing JPA operations.
     * @param dao     UserDAO instance for CRUD operations on users.
     * @param cart    CartDAO instance for CRUD operations on carts.
     */
    public UserService(EntityManager manager, UserDAO dao, CartDAO cart) {
        super(manager, dao);
        this.cart = cart;
    }

        /**
     * Checks if user registration is allowed by verifying the uniqueness of username and email.
     *
     * @param toBeRegistered User object containing registration details.
     * @return true if registration is allowed (username and email are unique), false otherwise.
     * @throws UserAlreadyExistsException if username or email already exists in the system.
     */
    private boolean allowRegister(User toBeRegistered) {
        // Check if there's an existing user with the same username
        User sameName = ((UserDAO) getDao()).findByName(toBeRegistered.getUsername());
        if (sameName != null) {
            throw new RuntimeException("Username already exists");
        }

        // Check if there's an existing user with the same email
        User sameMail = ((UserDAO) getDao()).findByEmail(toBeRegistered.getEmail());
        if (sameMail != null) {
            throw new RuntimeException("Email already in use");
        }

        return true;
    }

    /**
     * Registers a new user in the system.
     *
     * @param toBeRegistered User object containing registration details.
     * @return The registered User object.
     */
    public User register(User toBeRegistered) {
        return executeTransaction(() -> {
            if (allowRegister(toBeRegistered)) {

                User registeredUser = ((UserDAO) getDao()).create(toBeRegistered);
                Cart userCart = this.cart.create(new Cart(registeredUser)); // Create cart for the registered user
                registeredUser.setCart(userCart); // Set the created cart to the registered user
                return registeredUser;
            } else {
                return null; // Registration not allowed
            }
        });
    }

    /**
     * Authenticates a user based on username and password.
     *
     * @param username        Username of the user attempting to log in.
     * @param password        Password of the user attempting to log in.
     * @return Authenticated User object if successful.
     * @throws AuthenticationException if authentication fails (invalid username or password).
     */
    public User loginWithUser(String username, String password) {
        return login(username, password, (name) -> ((UserDAO) getDao()).findByName(name));
    }

    /**
     * Authenticates a user based on email and password.
     *
     * @param email           Email address of the user attempting to log in.
     * @param password        Password of the user attempting to log in.
     * @return Authenticated User object if successful.
     * @throws AuthenticationException if authentication fails (invalid email or password).
     */
    public User loginWithEmail(String email, String password) {
        return login(email, password, (mail) -> ((UserDAO) getDao()).findByEmail(mail));
    }

    /**
     * Performs user authentication based on a generic identifier (username or email).
     *
     * @param identifier      Identifier (username or email) of the user attempting to log in.
     * @param password        Password of the user attempting to log in.
     * @param findByIdentifier Function to find a user by identifier (username or email).
     * @return Authenticated User object if successful.
     * @throws AuthenticationException if authentication fails (invalid identifier or password).
     */
    protected User login(String identifier, String password, Function<String, User> findByIdentifier) {
        return executeTransaction(() -> {
            try {
                User user = findByIdentifier.apply(identifier);

                // Check if user exists
                if (user == null) {
                    throw new AuthenticationException("User not found with the provided identifier");
                }

                // Verify password
                if (PasswordHasher.comparePassword(password, user.getPassword())) {
                    return user; // Authentication successful
                } else {
                    throw new AuthenticationException("Incorrect password");
                }
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException("Error during password hashing", e);
            }
        });
    }
    public Cart GetRelatedCart(Integer id) {
        return this.retrieveById(id).getCart();
    }


    public User findByUsername(String name) {
        return ((UserDAO) getDao()).findByName(name);
    }
}
