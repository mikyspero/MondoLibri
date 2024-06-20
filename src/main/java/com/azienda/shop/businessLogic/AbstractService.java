package com.azienda.shop.businessLogic;
import com.azienda.shop.dao.AbstractDAO;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.function.Supplier;

public abstract class AbstractService<T> {
    private EntityManager manager;
    private AbstractDAO<T> dao;;
    protected AbstractService(EntityManager manager, AbstractDAO dao) {
        this.manager = manager;
        this.dao = dao;
    }

    public EntityManager getManager() {
        return manager;
    }

    public void setManager(EntityManager manager) {
        this.manager = manager;
    }

    public AbstractDAO getDao() {
        return dao;
    }

    public void setDao(AbstractDAO dao) {
        this.dao = dao;
    }

    protected <R> R executeTransaction(Supplier<R> action){
        try{
            manager.getTransaction().begin();
            R result = action.get();
            manager.getTransaction().commit();
            return result;
        }catch (Exception e) {
            manager.getTransaction().rollback();
            throw e;
        }
    }

    public T insert(T toBeInserted) throws Exception {
        return executeTransaction(() -> {
            return dao.create(toBeInserted);
        });
    }

    public T retrieveById(Integer id) throws Exception {
       return executeTransaction( () -> {
           return dao.findById(id);
       });
    }

    public List<T> retrieveAll() throws Exception {
        return executeTransaction( () -> {
            return dao.findAll();
        });
    }

    public T updateElement(T toBeUpdated) throws Exception {
      return executeTransaction( () -> {
          return dao.update(toBeUpdated);
      });
    }

    public void delete(T toBeDeleted) throws Exception {
        try{
            manager.getTransaction().begin();
            manager.getTransaction().rollback();
            manager.getTransaction().commit();
        }catch (Exception e) {
            throw e;
        }
    }
}

