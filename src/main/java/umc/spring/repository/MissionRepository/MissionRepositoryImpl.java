package umc.spring.repository.MissionRepository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Repository;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.domain.mapping.QMemberMission;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MissionRepositoryImpl implements MissionRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
    private final QMemberMission memberMission = QMemberMission.memberMission;

    @Override
    public Page<MemberMission> dynamicQueryWithBooleanBuilder(Long memberId, MissionStatus status, Long lastMissionId, Pageable pageable) {
        BooleanBuilder predicate = new BooleanBuilder();
        if(memberId != null) {
            predicate.and(memberMission.member.id.eq(memberId));
        }
        if(status != null) {
            predicate.and(memberMission.status.eq(status));
        }
        if(lastMissionId != null) {
            predicate.and(memberMission.id.lt(lastMissionId));
        }
        // 페이징된 데이터 조회
        List<MemberMission> content = jpaQueryFactory
                .selectFrom(memberMission)
                .where(predicate)
                .orderBy(memberMission.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        // 전체 데이터 개수 조회
        long total = jpaQueryFactory
                .selectFrom(memberMission)
                .where(predicate)
                .fetch().size();

        // PageImpl로 페이징된 결과를 생성하여 반환
        return new PageImpl<>(content, pageable, total);
    }
}
