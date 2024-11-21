package umc.spring.converter;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import umc.spring.domain.Review;
import umc.spring.web.dto.Review.ReviewRequestDTO;
import umc.spring.web.dto.Review.ReviewResponseDTO;

import java.time.LocalDateTime;

public class ReviewConverter {
    public static ReviewResponseDTO.addReviewResultDTO toAddReviewResultDTO(Review review) {
        return ReviewResponseDTO.addReviewResultDTO.builder()
                .reviewId(review.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }
    public static Review toReview(ReviewRequestDTO.addReviewDTO request) {
        return Review.builder()
                .body(request.getBody())
                .score(request.getScore())
                .build();
    }
}
