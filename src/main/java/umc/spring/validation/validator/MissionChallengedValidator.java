package umc.spring.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.service.MissionService.MissionQueryService;
import umc.spring.validation.annotation.MissionAlreadyChallenged;
import umc.spring.web.dto.Mission.MissionRequestDTO;

@Component
@RequiredArgsConstructor
public class MissionChallengedValidator implements ConstraintValidator<MissionAlreadyChallenged, MissionRequestDTO.UpdateMissionStatusDTO> {
    private final MissionQueryService missionQueryService;

    @Override
    public void initialize(MissionAlreadyChallenged constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(MissionRequestDTO.UpdateMissionStatusDTO value, ConstraintValidatorContext context) {
        boolean isValid = !missionQueryService.findByMemberIdAndMissionIdAndStatus(value.getMemberId(), value.getMissionId(), MissionStatus.IN_PROGRESS);

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.MISSION_ALREADY_CHALLENGED.toString()).addConstraintViolation();
        }
        return isValid;
    }
}