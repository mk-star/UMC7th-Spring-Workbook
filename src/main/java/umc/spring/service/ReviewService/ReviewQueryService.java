package umc.spring.service.ReviewService;

import umc.spring.domain.Review;

import java.time.LocalDate;
import java.util.Optional;

public interface ReviewQueryService {
    void insertReview(Long memberId, Long storeId, String body, float score);
}
