package umc.spring.service.MissionService;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;

import java.util.Optional;

public interface MissionQueryService {
    Optional<MemberMission> findMemberMission(Long id);
    Page<MemberMission> findMissionByMemberIdAndStatus(Long memberId, MissionStatus status, Long lastMissionId, Pageable pageable);
    int findCompletedMissionCountByMemberIdAndStatus(Long memberId, MissionStatus status);
}
