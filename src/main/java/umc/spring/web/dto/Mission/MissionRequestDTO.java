package umc.spring.web.dto.Mission;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.validation.annotation.ExistMember;
import umc.spring.validation.annotation.ExistMission;
import umc.spring.validation.annotation.ExistStore;
import umc.spring.validation.annotation.MissionAlreadyChallenged;

import java.time.LocalDate;

public class MissionRequestDTO {
    @Getter
    public static class AddMissionDTO {
        @ExistStore
        Long storeId;
        @NotBlank
        String mission_spec;
        @NotNull
        LocalDate deadline;
        @NotNull
        Integer price;
        @NotNull
        Integer reward;
    }

    @Getter
    public static class UpdateMissionStatusDTO {
        @ExistMember
        Long memberId;
        @ExistMission
        Long missionId;
    }
}
