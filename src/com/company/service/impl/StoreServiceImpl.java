package com.company.service.impl;

import com.company.exception.SaveDataException;
import com.company.exception.WrongIdException;
import com.company.models.Store;
import com.company.repository.StoreDAO;
import com.company.service.StoreService;

import java.util.*;

public class StoreServiceImpl implements StoreService {

    private final StoreDAO storeDao;

    public StoreServiceImpl(StoreDAO storeDao){
        this.storeDao = storeDao;
    }


    @Override
    public void add(String name, String description){
        Store store = new Store();
        store.setId(storeDao.getFreeStoreId());
        store.setProductList(new HashMap<>());
        store.setName(name);
        store.setDescription(description);
        storeDao.add(store);
    }

    @Override
    public void delete(int id) throws WrongIdException{
        if(storeDao.remove(id) == null)
            throw new WrongIdException(id);
    }

    @Override
    public void update(String name, String description, int id) throws WrongIdException{
        Store store = storeDao.getById(id);
        if (store == null)
            throw new WrongIdException(id);
        store.setName(name);
        store.setDescription(description);
    }

    @Override
    public Collection<Store> getStoreList() {
        return storeDao.readAll();
    }


}
