package umc.spring.service.ReviewService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import umc.spring.repository.ReviewRepository.ReviewRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewQueryServiceImpl implements ReviewQueryService{
    private final ReviewRepository reviewRepository;

    @Override
    public void insertReview(Long memberId, Long storeId, String body, float score) {
        reviewRepository.dynamicQueryWithBooleanBuilder(memberId, storeId, body, score);
    }
}
