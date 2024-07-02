package com.azienda.shop.businessLogic;

import com.azienda.shop.dao.AuthorDAO;
import com.azienda.shop.dao.GenreDAO;
import com.azienda.shop.exceptions.DataAccessException;
import com.azienda.shop.exceptions.PersistenceException;
import com.azienda.shop.model.Author;
import com.azienda.shop.model.Genre;

import javax.persistence.EntityManager;

public class GenreService extends AbstractService<Genre>{
    public GenreService(EntityManager manager, GenreDAO dao) {
        super(manager,dao);
    }

    public Genre findGenreByName(String nameToBeChecked) {
        try {
            Genre sameName = ((GenreDAO) this.getDao()).findByName(nameToBeChecked);
            if (sameName == null) {
                return null;
            }
            return sameName;
        } catch (DataAccessException e) {
            throw new PersistenceException("Failed to fetch requested genre",e);
        }
    }
}
