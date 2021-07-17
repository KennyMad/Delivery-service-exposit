package com.company.repository.impl;

import com.company.constants.Constants;
import com.company.exception.LoadDataException;
import com.company.exception.SaveDataException;
import com.company.exception.WrongIdException;
import com.company.models.Store;
import com.company.repository.StoreDAO;
import com.company.service.FileService;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StoreDAOImpl implements StoreDAO {

    private List<Store> storeList;

    private final FileService fileService;

    public StoreDAOImpl(FileService fileService){
        this.fileService = fileService;
    }

    @Override
    public void initialize(){
    }

    @Override
    public void save() throws SaveDataException {
    }

    @Override
    public Collection readAll() {
        return storeList;
    }

    @Override
    public Store getById(int id){
        return storeList.stream().filter(s -> s.getId() == id).limit(1).findFirst().get();
    }

    @Override
    public Store remove(int id) {
        Store store = getById(id);
        if (store != null)
            storeList.remove(store);
        return store;
    }

    @Override
    public void add(Store store) {
        storeList.add(store);
    }

    @Override
    public int getFreeStoreId() {
        return 0;
    }

    @Override
    public int getFreeProductId(int storeId) {
        return 0;
    }
}
