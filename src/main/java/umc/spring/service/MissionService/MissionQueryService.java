package umc.spring.service.MissionService;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import umc.spring.domain.Mission;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;

import java.util.Optional;

public interface MissionQueryService {
    Page<Mission> findMissionByMemberIdAndStatus(Long memberId, MissionStatus status, Long lastMissionId, Pageable pageable);
    int findCompletedMissionCountByMemberIdAndStatus(Long memberId, MissionStatus status);
    Page<Mission> findNotStartedMissionByMemberIdAndStatusAndRegionName(Long memberId, MissionStatus status, String regionName, Long lastMissionId, Pageable pageable);
}
