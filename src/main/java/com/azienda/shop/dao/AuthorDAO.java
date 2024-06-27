package com.azienda.shop.dao;

import com.azienda.shop.model.Author;

import javax.persistence.EntityManager;
import java.util.List;

public class AuthorDAO extends AbstractDAO<Author> {
    public AuthorDAO(EntityManager entityManager) {
        super(entityManager);
    }

//    public Author findById(int id) {
//        return entityManager.find(Author.class, id);
//    }
//
//    public List<Author> findAll() {
//        return entityManager.createQuery("from Author").getResultList();
//    }

    @Override
    protected List<Author> executeQuery(String query) {
        return List.of();
    }

    @Override
    public Class<Author> getEntityClass() {
        return Author.class;
    }

}
