package umc.spring.service.ReviewService;

import org.springframework.web.multipart.MultipartFile;
import umc.spring.domain.Review;
import umc.spring.web.dto.Review.ReviewRequestDTO;

public interface ReviewCommandService {
    Review addReviewToStore(ReviewRequestDTO.addReviewDTO request, MultipartFile reviewPicture);
}
