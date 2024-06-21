package com.azienda.shop.dao;
import com.azienda.shop.model.Role;

import javax.persistence.EntityManager;
import java.util.List;


public class RoleDAO  extends AbstractDAO<Role>{


    public RoleDAO(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    protected List<Role> executeQuery(String query) {

        return List.of();
    }

    @Override
    protected Class<Role> getEntityClass() {
        return Role.class;
    }
}
