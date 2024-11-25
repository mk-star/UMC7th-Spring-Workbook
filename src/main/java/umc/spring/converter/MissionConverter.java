package umc.spring.converter;

import umc.spring.domain.Mission;
import umc.spring.web.dto.Mission.MissionRequestDTO;
import umc.spring.web.dto.Mission.MissionResponseDTO;

import java.time.LocalDateTime;


public class MissionConverter {
    public static MissionResponseDTO.AddMissionResultDTO toAddMissionResultDTO(Mission mission) {
        return MissionResponseDTO.AddMissionResultDTO.builder()
                .missionId(mission.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Mission toMission(MissionRequestDTO.AddMissionDTO request) {
        return Mission.builder()
                .missionSpec(request.getMission_spec())
                .deadline(request.getDeadline())
                .price(request.getPrice())
                .reward(request.getReward())
                .build();
    }

}
