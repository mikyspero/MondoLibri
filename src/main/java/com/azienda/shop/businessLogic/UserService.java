package com.azienda.shop.businessLogic;

import com.azienda.shop.dao.UserDAO;
import com.azienda.shop.model.User;
import com.azienda.shop.utils.PasswordHasher;

import javax.persistence.EntityManager;
import java.security.NoSuchAlgorithmException;
import java.util.function.Function;

public class UserService extends AbstractService<User> {

    private boolean allowRegister(User toBeRegistered) {
        UserDAO userDAO = (UserDAO) getDao();
        User sameName = userDAO.findByName(toBeRegistered.getUsername());
        if (sameName != null) {
            throw new RuntimeException("Username already exists");
        }
        User sameMail = userDAO.findByEmail(toBeRegistered.getEmail());
        if (sameMail != null) {
            throw new RuntimeException("Email already used");
        }
        return true;
    }

    public User register(User toBeRegistered) {
        return executeTransaction(() -> {
            if (allowRegister(toBeRegistered)) {
                User registeredUser = ((UserDAO) getDao()).create(toBeRegistered);
                return registeredUser;
            } else {
                return null;
            }
        });
    }

    protected User login(String identifier, String password, Function<String, User> findByIdentifier) {
        return executeTransaction(() -> {
            try {
                User user = findByIdentifier.apply(identifier);
                if (user == null) {
                    throw new RuntimeException("User not found with the provided identifier");
                }
                String hashedPassword = PasswordHasher.hashPassword(password);
                if (user.getPassword().equals(hashedPassword)) {
                    return user;
                } else {
                    throw new RuntimeException("Invalid password");
                }
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException("Error hashing password", e);
            }
        });
    }

    public User loginWithUser(String username, String password) {
        return login(username, password, (name) -> ((UserDAO) getDao()).findByName(name));
    }

    public User loginWithEmail(String email, String password) {
        return login(email, password, (mail) -> ((UserDAO) getDao()).findByEmail(email));
    }

    public UserService(EntityManager manager, UserDAO dao) {
        super(manager, dao);
    }
}

