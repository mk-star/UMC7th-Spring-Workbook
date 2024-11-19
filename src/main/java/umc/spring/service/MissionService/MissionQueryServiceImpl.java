package umc.spring.service.MissionService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.domain.Mission;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.repository.MissionRepository.MissionRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionQueryServiceImpl implements MissionQueryService {
    private final MissionRepository missionRepository;

    @Override
    public Page<Mission> findMissionByMemberIdAndStatus(Long memberId, MissionStatus status, Long lastMissionId, Pageable pageable) {
        Page<Mission> filteredMemberMissions = missionRepository.findMissionsByMemberIdAndStatus(memberId, status, lastMissionId, pageable);
        filteredMemberMissions.forEach(memberMission -> System.out.println("Mission: " + memberMission));
        return filteredMemberMissions;
    }

    @Override
    public int findCompletedMissionCountByMemberIdAndStatus(Long memberId, MissionStatus status) {
        int count = missionRepository.findCompletedMissionCountByMemberIdAndStatus(memberId, status);
        System.out.println("Completed Mission Count: " + count);
        return count;
    }

    @Override
    public Page<Mission> findNotStartedMissionByMemberIdAndStatusAndRegionName(Long memberId, MissionStatus status, String regionName, Long lastMissionId, Pageable pageable) {
        Page<Mission> filteredMemberMissions = missionRepository.findNotStartedMissionByMemberIdAndStatusAndRegionName(memberId, status, regionName, lastMissionId, pageable);
        filteredMemberMissions.forEach(memberMission -> System.out.println("Mission: " + memberMission));
        return filteredMemberMissions;
    }
}
