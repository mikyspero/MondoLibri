package com.azienda.shop.businessLogic;

import com.azienda.shop.dao.AuthorDAO;
import com.azienda.shop.dao.ProductDAO;
import com.azienda.shop.exceptions.ProductNotFoundException;
import com.azienda.shop.model.Author;
import com.azienda.shop.model.Product;

import javax.persistence.EntityManager;

public class AuthorService extends AbstractService<Author> {
    public AuthorService(EntityManager manager, AuthorDAO dao) {
        super(manager,dao);
    }

    public Author findAuthorByName(String nameToBeChecked) {
        Author sameName = ((AuthorDAO) this.getDao()).findByName(nameToBeChecked);
        if (sameName == null) {
            return null;
        }
        return sameName;
    }
}
