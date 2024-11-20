package umc.spring.service.StoreService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.apiPayload.code.exception.handler.RegionHandler;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.Region;
import umc.spring.domain.Store;
import umc.spring.repository.RegionRepository.RegionRepository;
import umc.spring.repository.StoreRepository.StoreRepository;
import umc.spring.web.dto.Store.StoreRequestDTO;

@Service
@RequiredArgsConstructor
public class StoreCommandServiceImpl implements StoreCommandService{
    private final StoreRepository storeRepository;
    private final RegionRepository regionRepository;

    @Override
    @Transactional
    public Store addStoreToRegion(StoreRequestDTO.addStore request) {
        Store newStore = StoreConverter.toStore(request);
        Region region = regionRepository.findById(request.getRegionId()).orElseThrow(() -> new RegionHandler(ErrorStatus.REGION_NOT_FOUND));
        newStore.setRegion(region);
        return storeRepository.save(newStore);
    }
}
