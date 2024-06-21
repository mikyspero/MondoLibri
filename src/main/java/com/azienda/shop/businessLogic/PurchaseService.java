package com.azienda.shop.businessLogic;

import com.azienda.shop.dao.PurchaseDAO;
import com.azienda.shop.model.Purchase;
import org.hibernate.service.Service;

import javax.persistence.EntityManager;

public class PurchaseService extends AbstractService<Purchase> {
    public PurchaseService(EntityManager manager, PurchaseDAO dao) {
        super(manager, dao);
    }
}
