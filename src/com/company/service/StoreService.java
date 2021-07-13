package com.company.service;

import com.company.exception.SaveDataException;
import com.company.exception.WrongIdException;
import com.company.models.Store;

import java.util.Collection;

public interface StoreService {

    void addStore(String name,String description) throws SaveDataException;

    void deleteStore(int id) throws WrongIdException, SaveDataException;

    void updateStore(String name, String description, int id) throws WrongIdException, SaveDataException;

    Collection<Store> getStoreList();

}
