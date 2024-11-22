package umc.spring.converter;

import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.web.dto.Mission.MissionResponseDTO;

public class MemberMissionConverter {
    public static MissionResponseDTO.UpdateMissionStatusResultDTO toUpdateMissionStatusResultDTO(MemberMission memberMission) {
        return MissionResponseDTO.UpdateMissionStatusResultDTO.builder()
                .missionId(memberMission.getId())
                .status(memberMission.getStatus())
                .updatedAt(memberMission.getUpdatedAt())
                .build();
    }
    public static MemberMission toMemberMission(Member member, Mission mission) {
        return MemberMission.builder()
                .mission(mission)
                .member(member)
                .status(MissionStatus.NOT_STARTED)
                .build();
    }
}
