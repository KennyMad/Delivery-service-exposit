package com.company.service;

import com.company.exception.SaveDataException;
import com.company.exception.WrongIdException;
import com.company.models.DTO.StoreDTO;
import com.company.models.Store;

import java.util.Collection;

public interface StoreService {

    void add(StoreDTO storeDTO);

    void delete(int id) throws WrongIdException;

    void update(StoreDTO storeDTO) throws WrongIdException;

    Collection<StoreDTO> getStoreList();

}
