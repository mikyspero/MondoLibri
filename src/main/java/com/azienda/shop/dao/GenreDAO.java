package com.azienda.shop.dao;

import com.azienda.shop.exceptions.DataAccessException;
import com.azienda.shop.model.Author;
import com.azienda.shop.model.Genre;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

public class GenreDAO extends AbstractDAO<Genre> {

    public  GenreDAO(EntityManager entityManager) {
        super(entityManager);
    }

    public Genre findByName(String name) {
        try{
            return entityManager.createQuery("SELECT u FROM Genre u WHERE u.name = :name", Genre.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch(Exception e){
            throw new DataAccessException("Error finding genre", e);
        }
    }

    public List<Genre> findAll() {
        try {
            return entityManager.createQuery("SELECT u FROM Genre u", Genre.class).getResultList();
        } catch(Exception e){
            throw new DataAccessException("Error finding genre", e);
        }
    }

    @Override
    protected List<Genre> executeQuery(String query) {
        return List.of();
    }

    @Override
    public  Class<Genre> getEntityClass() {
        return Genre.class;
    }
}
