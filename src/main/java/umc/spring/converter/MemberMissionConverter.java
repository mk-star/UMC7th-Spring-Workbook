package umc.spring.converter;

import org.springframework.data.domain.Page;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.web.dto.Mission.MissionResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

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

    public static MissionResponseDTO.MissionPreViewDTO missionPreViewDTO(MemberMission mission){
        return MissionResponseDTO.MissionPreViewDTO.builder()
                .reward(mission.getMission().getReward())
                .status(MissionStatus.IN_PROGRESS)
                .storeName(mission.getMission().getStore().getName())
                .price(mission.getMission().getPrice())
                .build();
    }

    public static MissionResponseDTO.MissionPreViewListDTO missionPreViewListDTO(Page<MemberMission> missionList) {
        List<MissionResponseDTO.MissionPreViewDTO> missionPreViewDTOList = missionList.stream()
                .map(MemberMissionConverter::missionPreViewDTO).collect(Collectors.toList());

        return MissionResponseDTO.MissionPreViewListDTO.builder()
                .isLast(missionList.isLast())
                .isFirst(missionList.isFirst())
                .totalPage(missionList.getTotalPages())
                .totalElements(missionList.getTotalElements())
                .listSize(missionPreViewDTOList.size())
                .missionList(missionPreViewDTOList)
                .build();
    }
}
