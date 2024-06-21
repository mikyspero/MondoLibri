package com.azienda.shop.businessLogic;

import com.azienda.shop.dao.AuthorDAO;
import com.azienda.shop.model.Author;

import javax.persistence.EntityManager;

public class AuthorService extends AbstractService<Author> {
    public AuthorService(EntityManager manager, AuthorDAO dao) {
        super(manager,dao);
    }
}
