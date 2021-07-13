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
    public void addStore(String name, String description) throws SaveDataException {
        Store store = new Store();
        store.setId(storeDao.getFreeStoreId());
        store.setProductList(new HashMap<>());
        store.setName(name);
        store.setDescription(description);
        storeDao.add(store);

        storeDao.save();
    }

    @Override
    public void deleteStore(int id) throws WrongIdException, SaveDataException {
        storeDao.remove(id);

        storeDao.save();
    }

    @Override
    public void updateStore(String name, String description, int id) throws WrongIdException, SaveDataException {
        Store store = storeDao.read(id);
        store.setName(name);
        store.setDescription(description);

        storeDao.save();
    }

    @Override
    public Collection<Store> getStoreList() {
        return storeDao.readAll();
    }


}
