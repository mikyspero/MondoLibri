package com.azienda.shop.businessLogic;

import com.azienda.shop.dao.AuthorDAO;
import com.azienda.shop.exceptions.DataAccessException;
import com.azienda.shop.exceptions.PersistenceException;
import com.azienda.shop.model.Author;

import javax.persistence.EntityManager;

public class AuthorService extends AbstractService<Author> {
    public AuthorService(EntityManager manager, AuthorDAO dao) {
        super(manager, dao);
    }

    public Author findAuthorByName(String nameToBeChecked) throws PersistenceException {
        try {
            return executeTransaction(() -> ((AuthorDAO) getDao()).findByName(nameToBeChecked));
        } catch (DataAccessException e) {
            // Wrap PersistenceException with more specific message
            throw new PersistenceException("Error occurred while finding author by name: " + nameToBeChecked, e);
        }
    }
}