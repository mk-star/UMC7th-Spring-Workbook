package umc.spring.web.dto.Store;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class StoreResponseDTO {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class addStoreResultDTO {
        Long storeId;
        LocalDateTime createdAt;
    }
}
