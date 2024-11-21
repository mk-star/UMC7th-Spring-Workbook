package umc.spring.web.dto.Store;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

public class StoreRequestDTO {
    @Getter
    public static class addStoreDTO {
        @NotNull
        Long regionId;
        @NotBlank
        String name;
        @Size(min = 5, max = 12)
        String address;
    }
}
