package umc.spring.service.MissionService;

import umc.spring.domain.Mission;
import umc.spring.web.dto.Mission.MissionRequestDTO;

public interface MissionCommandService {
    Mission addMissionToStore(MissionRequestDTO.AddMissionDTO request);
}
