package com.azienda.shop.businessLogic;

import com.azienda.shop.dao.RoleDAO;
import com.azienda.shop.model.Role;
import javax.persistence.EntityManager;

public class RoleService extends AbstractService<Role> {
    public RoleService(EntityManager manager, RoleDAO dao) {
        super(manager, dao);
    }
}