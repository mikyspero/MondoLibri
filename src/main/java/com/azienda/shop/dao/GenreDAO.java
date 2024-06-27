package com.azienda.shop.dao;

import com.azienda.shop.model.Genre;

import javax.persistence.EntityManager;
import java.util.List;

public class GenreDAO extends AbstractDAO<Genre> {

    public  GenreDAO(EntityManager entityManager) {
        super(entityManager);
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
