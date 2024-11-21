package umc.spring.service.MissionService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.apiPayload.code.exception.handler.StoreHandler;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.converter.MissionConverter;
import umc.spring.domain.Mission;
import umc.spring.domain.Store;
import umc.spring.repository.MissionRepository.MissionRepository;
import umc.spring.repository.StoreRepository.StoreRepository;
import umc.spring.web.dto.Mission.MissionRequestDTO;

@Service
@RequiredArgsConstructor
public class MissionCommandServiceImpl implements MissionCommandService{
    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;

    @Override
    @Transactional
    public Mission addMissionToStore(MissionRequestDTO.AddMissionDTO request) {
        Mission newMission = MissionConverter.toMission(request);
        Store store = storeRepository.findById(request.getStoreId()).orElseThrow(() -> new StoreHandler(ErrorStatus.STORE_NOT_FOUND));
        newMission.setStore(store);
        return missionRepository.save(newMission);
    }
}
