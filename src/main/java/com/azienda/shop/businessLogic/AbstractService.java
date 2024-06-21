package com.azienda.shop.businessLogic;
import com.azienda.shop.dao.AbstractDAO;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.function.Supplier;

public abstract class AbstractService<T>{
    private EntityManager manager;
    private AbstractDAO<T> dao;
    protected AbstractService(EntityManager manager, AbstractDAO<T> dao) {
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

    protected <R> R executeTransaction(Supplier<R> action) {
        EntityManager em = getManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            if (!transaction.isActive()) {
                transaction.begin();
            }

            R result = action.get();

            if (transaction.isActive() && !transaction.getRollbackOnly()) {
                transaction.commit();
            } else {
                transaction.rollback();
            }

            return result;

        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    public T insert(T toBeInserted) throws Exception {
        return executeTransaction(() -> dao.create(toBeInserted));
    }

    public T retrieveById(Integer id) throws Exception {
       return executeTransaction( () -> dao.findById(id));
    }

    public List<T> retrieveAll() throws Exception {
        return executeTransaction( () -> dao.findAll());
    }

    public T updateElement(T toBeUpdated) throws Exception {
      return executeTransaction( () -> dao.update(toBeUpdated));
    }

    public boolean doesElementExist(T toBeFound) throws Exception {
            return dao.exist(toBeFound);
    }

    public void delete(T toBeDeleted) throws Exception {
        try{
            manager.getTransaction().begin();
            dao.delete(toBeDeleted);
            manager.getTransaction().commit();
        }catch (Exception e) {
            manager.getTransaction().rollback();
            throw e;
        } finally {
            manager.close();
        }
    }
}

