package com.company.service;

import com.company.exception.SaveDataException;
import com.company.exception.WrongIdException;
import com.company.models.Store;

import java.util.Collection;

public interface StoreService {

    void add(String name,String description);

    void delete(int id) throws WrongIdException;

    void update(String name, String description, int id) throws WrongIdException;

    Collection<Store> getStoreList();

}
