package com.azienda.shop.ui;

import com.azienda.shop.businessLogic.RoleService;
import com.azienda.shop.dao.DAOinterface;
import com.azienda.shop.dao.RoleDAO;
import com.azienda.shop.model.Role;
import org.hibernate.service.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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
            RoleDAO dao = new RoleDAO(entityManager);
            RoleService service = new RoleService(entityManager, dao);
            Role role = service.retrieveById(2);
            role.setNome("giampiero");
            role = service.updateElement(role);
            List<Role> roles = service.retrieveAll();
            for(Role role1 : roles){
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