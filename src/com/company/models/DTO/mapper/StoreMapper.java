package com.company.models.DTO.mapper;

import com.company.models.DTO.StoreDTO;
import com.company.models.Store;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StoreMapper {

    StoreMapper STORE_MAPPER = Mappers.getMapper(StoreMapper.class);

    StoreDTO storeToStoreDTO(Store store);

    Store storeDTOtoStore(StoreDTO storeDTO);

}
