package umc.spring.service.StoreService;

import umc.spring.domain.Store;
import umc.spring.web.dto.Store.StoreRequestDTO;

import java.util.List;

public interface StoreCommandService {
    Store addStoreToRegion(StoreRequestDTO.addStoreDTO request);
}
