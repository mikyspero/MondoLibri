package com.azienda.shop.businessLogic;

import com.azienda.shop.dao.RoleDAO;

import javax.persistence.EntityManager;

public class UserService {
    private EntityManager manager;
    private RoleDAO dao;


    public UserService(EntityManager manager, RoleDAO dao) {
        this.manager = manager;
        this.dao = dao;
    }

    public EntityManager getManager() {
        return manager;
    }

    public void setManager(EntityManager manager) {
        this.manager = manager;
    }

    public RoleDAO getDao() {
        return dao;
    }

    public void setDao(RoleDAO dao) {
        this.dao = dao;
    }
}
