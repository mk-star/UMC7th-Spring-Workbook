package umc.spring.repository.ReviewRepository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc.spring.domain.*;
import umc.spring.service.MemberService.MemberQueryService;
import umc.spring.service.StoreService.StoreQueryService;

import java.time.LocalDate;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;
    private final EntityManager em;
    private final QReview review = QReview.review;
    private final QMember member = QMember.member;
    private final QStore store = QStore.store;

    @Override
    public void dynamicQueryWithBooleanBuilder(Long memberId, Long storeId, String body, float score) {

        Member memberEntity = jpaQueryFactory.selectFrom(member)
                .where(member.id.eq(memberId))
                .fetchOne();

        Store storeEntity = jpaQueryFactory.selectFrom(store)
                .where(store.id.eq(storeId))
                .fetchOne();

        Review reviewEntity = Review.builder()
                .member(memberEntity)
                .store(storeEntity)
                .body(body)
                .score(score)
                .build();

        em.persist(reviewEntity);
    }
}
