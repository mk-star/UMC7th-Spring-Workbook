package umc.spring.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.MemberMissionConverter;
import umc.spring.converter.MissionConverter;
import umc.spring.domain.Mission;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.service.MissionService.MissionCommandService;
import umc.spring.validation.annotation.MissionAlreadyChallenged;
import umc.spring.web.dto.Mission.MissionRequestDTO;
import umc.spring.web.dto.Mission.MissionResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
@Validated
public class MissionRestController {
    private final MissionCommandService missionCommandService;

    @PostMapping("/")
    public ApiResponse<MissionResponseDTO.AddMissionResultDTO> addMission(@RequestBody @Valid MissionRequestDTO.AddMissionDTO request) {
        Mission mission = missionCommandService.addMissionToStore(request);
        return ApiResponse.onSuccess(MissionConverter.toAddMissionResultDTO(mission));
    }

    @PatchMapping("/status")
    public ApiResponse<MissionResponseDTO.UpdateMissionStatusResultDTO> updateMissionStatus(@RequestBody @Valid @MissionAlreadyChallenged  MissionRequestDTO.UpdateMissionStatusDTO request) {
        MemberMission memberMission = missionCommandService.updateMissionStatus(request);
        return ApiResponse.onSuccess(MemberMissionConverter.toUpdateMissionStatusResultDTO(memberMission));
    }
}
