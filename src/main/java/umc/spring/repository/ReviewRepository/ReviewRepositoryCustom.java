package umc.spring.repository.ReviewRepository;

public interface ReviewRepositoryCustom {
    void dynamicQueryWithBooleanBuilder(Long memberId, Long storeId, String body, float score);
}
