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
import java.util.Map;

public class StoreDAOImpl implements StoreDAO {

    private Map<Integer, Store> storeMap;

    private final FileService fileService;

    public StoreDAOImpl(FileService fileService){
        this.fileService = fileService;
    }

    @Override
    public void initialize(){
        try {
            storeMap = (HashMap) fileService.load(Constants.STORE_FILE, new TypeToken<HashMap<Integer, Store>>() {
            }.getType());
        }
        catch (FileNotFoundException exception){
            storeMap = new HashMap<>();
        }
        catch (LoadDataException loadException){
            storeMap = new HashMap<>();
            loadException.printStackTrace();
        }
    }

    @Override
    public void save() throws SaveDataException {
        fileService.save(Constants.STORE_FILE, storeMap);
    }

    @Override
    public Collection readAll() {
        return storeMap.values();
    }

    @Override
    public Store getById(int id){
        return storeMap.get(id);
    }

    @Override
    public Store remove(int id) {
        return storeMap.remove(id);
    }

    @Override
    public void add(Store store) {
        storeMap.put(store.getId(),store);
    }

    @Override
    public int getFreeStoreId() {
        int id = storeMap.size();
        while (storeMap.containsKey(id))
            id++;
        return id;
    }

    @Override
    public int getFreeProductId(int storeId) {
        int id = storeMap.get(storeId).getProductList().size();
        while (storeMap.get(storeId).getProductList().containsKey(id))
            id++;
        return id;
    }
}
