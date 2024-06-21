package com.azienda.shop.businessLogic;

import com.azienda.shop.dao.RoleDAO;
import com.azienda.shop.dao.UserDAO;
import com.azienda.shop.model.User;

import javax.persistence.EntityManager;

public class UserService extends AbstractService<User> {
    public UserService(EntityManager manager, UserDAO dao) {
        super(manager,dao);
    }

}
