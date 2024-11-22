package umc.spring.web.dto.Review;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import umc.spring.validation.annotation.ExistMember;
import umc.spring.validation.annotation.ExistStore;

public class ReviewRequestDTO {
    @Getter
    public static class addReviewDTO {
        @ExistMember
        Long memberId;
        @ExistStore
        Long storeId;
        @NotBlank
        String body;
        @NotNull
        Float score;
    }
}
