package umc.spring.service.StoreService;

import umc.spring.domain.Store;
import umc.spring.web.dto.Store.StoreRequestDTO;

public interface StoreCommandService {
    Store addStoreToRegion(StoreRequestDTO.addStore request);
}
