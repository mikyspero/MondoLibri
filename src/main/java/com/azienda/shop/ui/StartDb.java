package com.azienda.shop.ui;

import com.azienda.shop.businessLogic.RoleService;
import com.azienda.shop.businessLogic.UserService;
import com.azienda.shop.dao.DAOinterface;
import com.azienda.shop.dao.RoleDAO;
import com.azienda.shop.dao.UserDAO;
import com.azienda.shop.model.Role;
import com.azienda.shop.model.User;
import org.hibernate.service.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class StartDb {

    public static void main(String[] args){
        EntityManager entityManager = null;
        try{
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Shop");
            entityManager = entityManagerFactory.createEntityManager();
            System.out.println("Creazione db effettuata");
            //Role ROLE_ADMIN = new Role("ADMIN");
            //Role PAolo = new Role("PAOLO");
            UserDAO dao = new UserDAO(entityManager);
            UserService service = new UserService(entityManager, dao);
            User u = User.createInstance("via ","aadsdafaafdcsd@as.com","234","adwafeazxcvb");
            // Perform login test
            try {
                User loggedInUser = service.loginWithEmail("aadsdafaafdcsd@as.com", "234");
                if (loggedInUser != null) {
                    System.out.println("Login successful for user: " + loggedInUser.getUsername());
                } else {
                    System.out.println("Login failed: Invalid credentials");

                }
            }  catch (RuntimeException e) {
                System.out.println("Login failed: " + e.getMessage());
                e.printStackTrace();
            }            List<User> roles = service.retrieveAll();
            for(User role1 : roles){
                System.out.println(role1);
            }


        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally{
            if ( entityManager != null ) {
                entityManager.close();
            }
            System.exit(0);
        }

    }

}