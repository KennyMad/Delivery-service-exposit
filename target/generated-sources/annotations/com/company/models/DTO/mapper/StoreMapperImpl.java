package com.company.models.DTO.mapper;

import com.company.models.DTO.StoreDTO;
import com.company.models.Store;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-07-20T00:48:58+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_281 (Oracle Corporation)"
)
public class StoreMapperImpl implements StoreMapper {

    @Override
    public StoreDTO storeToStoreDTO(Store store) {
        if ( store == null ) {
            return null;
        }

        StoreDTO storeDTO = new StoreDTO();

        storeDTO.setId( store.getId() );
        List<Integer> list = store.getProductListIds();
        if ( list != null ) {
            storeDTO.setProductListIds( new ArrayList<Integer>( list ) );
        }
        storeDTO.setName( store.getName() );
        storeDTO.setDescription( store.getDescription() );

        return storeDTO;
    }

    @Override
    public Store storeDTOtoStore(StoreDTO storeDTO) {
        if ( storeDTO == null ) {
            return null;
        }

        Store store = new Store();

        store.setId( storeDTO.getId() );
        List<Integer> list = storeDTO.getProductListIds();
        if ( list != null ) {
            store.setProductListIds( new ArrayList<Integer>( list ) );
        }
        store.setName( storeDTO.getName() );
        store.setDescription( storeDTO.getDescription() );

        return store;
    }
}
