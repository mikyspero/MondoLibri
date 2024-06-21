package com.azienda.shop.businessLogic;

public interface ServiceInterface<T> {
    public T retrieveById(Integer id) throws Exception;
    public T updateElement(T toBeUpdated) throws Exception;
    public void delete(T toBeDeleted) throws Exception;
    public T insert(T toBeInserted) throws Exception;
}
