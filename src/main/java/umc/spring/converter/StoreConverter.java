package umc.spring.converter;

import umc.spring.domain.Store;
import umc.spring.web.dto.Store.StoreRequestDTO;
import umc.spring.web.dto.Store.StoreResponseDTO;

public class StoreConverter {
    public static StoreResponseDTO.addStoreResultDTO toAddStoreResultDTO(Store store) {
        return StoreResponseDTO.addStoreResultDTO.builder()
                .storeId(store.getId())
                .createdAt(store.getCreatedAt())
                .build();
    }

    public static Store toStore(StoreRequestDTO.addStoreDTO request) {
        return Store.builder()
                .name(request.getName())
                .address(request.getAddress())
                .build();
    }
}
