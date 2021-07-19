package com.company.service.impl;

import com.company.exception.WrongIdException;
import com.company.models.DTO.StoreDTO;
import com.company.models.DTO.mapper.StoreMapper;
import com.company.models.Store;
import com.company.repository.StoreDAO;
import com.company.service.StoreService;
import com.company.utils.impl.SequenceGenerator;

import java.util.*;
import java.util.stream.Collectors;

public class StoreServiceImpl implements StoreService {

    private final StoreDAO storeDao;

    public StoreServiceImpl(StoreDAO storeDao){
        this.storeDao = storeDao;
    }


    @Override
    public void add(StoreDTO storeDTO){
        Store store = StoreMapper.STORE_MAPPER.storeDTOtoStore(storeDTO);
        store.setId(SequenceGenerator.getFreeStoreId(storeDao.readAll()));
        storeDao.add(store);
    }

    @Override
    public void delete(int id) throws WrongIdException{
        if(storeDao.remove(id) == null)
            throw new WrongIdException(id);
    }

    @Override
    public void update(StoreDTO storeDTO) throws WrongIdException{
        Store updatedStore = StoreMapper.STORE_MAPPER.storeDTOtoStore(storeDTO);
        Store store = storeDao.getById(updatedStore.getId());
        if (store == null)
            throw new WrongIdException(updatedStore.getId());
        store.setName(updatedStore.getName());
        store.setDescription(updatedStore.getDescription());
    }

    @Override
    public Collection<StoreDTO> getStoreList() {
        return storeDao.readAll().stream()
                .map(StoreMapper.STORE_MAPPER::storeToStoreDTO)
                .collect(Collectors.toList());
    }


}
