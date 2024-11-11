package umc.spring.repository.MissionRepository;

import org.springframework.data.domain.Page;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;

import org.springframework.data.domain.Pageable;

public interface MissionRepositoryCustom {
    Page<MemberMission> dynamicQueryWithBooleanBuilder(Long memberId, MissionStatus status, Long lastMissionId, Pageable pageable);
}