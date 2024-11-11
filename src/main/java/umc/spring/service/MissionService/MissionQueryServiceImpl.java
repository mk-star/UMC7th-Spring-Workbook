package umc.spring.service.MissionService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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
    public Optional<MemberMission> findMemberMission(Long id) {
        return missionRepository.findById(id);
    }

    @Override
    public Page<MemberMission> findMissionByMemberIdAndStatus(Long memberId, MissionStatus status, Long lastMissionId, Pageable pageable) {
        Page<MemberMission> filteredMemberMissions = missionRepository.findMissionsByMemberIdAndStatus(memberId, status, lastMissionId, pageable);

        filteredMemberMissions.forEach(memberMission -> System.out.println("MemberMission: " + memberMission));
        return filteredMemberMissions;
    }

    @Override
    public int findCompletedMissionCountByMemberIdAndStatus(Long memberId, MissionStatus status) {
        int count = missionRepository.findCompletedMissionCountByMemberIdAndStatus(memberId, status);
        System.out.println("Completed Mission Count: " + count);
        return count;
    }
}
