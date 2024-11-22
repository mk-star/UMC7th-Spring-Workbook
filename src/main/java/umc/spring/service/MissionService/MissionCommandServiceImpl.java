package umc.spring.service.MissionService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.apiPayload.code.exception.handler.FoodCategoryHandler;
import umc.spring.apiPayload.code.exception.handler.MemberHandler;
import umc.spring.apiPayload.code.exception.handler.MissionHandler;
import umc.spring.apiPayload.code.exception.handler.StoreHandler;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.converter.MemberMissionConverter;
import umc.spring.converter.MissionConverter;
import umc.spring.domain.FoodCategory;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.Store;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.repository.MemberRepository.MemberRepository;
import umc.spring.repository.MissionRepository.MemberMissionRepository;
import umc.spring.repository.MissionRepository.MissionRepository;
import umc.spring.repository.StoreRepository.StoreRepository;
import umc.spring.web.dto.Mission.MissionRequestDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MissionCommandServiceImpl implements MissionCommandService{
    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public Mission addMissionToStore(MissionRequestDTO.AddMissionDTO request) {
        Mission newMission = MissionConverter.toMission(request);
        Store store = storeRepository.findById(request.getStoreId()).orElseThrow(() -> new StoreHandler(ErrorStatus.STORE_NOT_FOUND));
        newMission.setStore(store);
        Mission savedMission = missionRepository.save(newMission);

        List<Member> memberList = memberRepository.findAll();
        List<MemberMission> memberMissionList = memberList.stream()
                .map(member -> MemberMissionConverter.toMemberMission(member, savedMission)).toList();

        memberMissionRepository.saveAll(memberMissionList);
        return savedMission;
    }

    @Override
    @Transactional
    public MemberMission updateMissionStatus(MissionRequestDTO.UpdateMissionStatusDTO request) {
        Member member = memberRepository.findById(request.getMemberId()).orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
        Mission mission = missionRepository.findById(request.getMissionId()).orElseThrow(() -> new MissionHandler(ErrorStatus.MISSION_NOT_FOUND));
        MemberMission memberMission = memberMissionRepository.findByMemberAndMission(member, mission).orElseThrow(() -> new MissionHandler(ErrorStatus.MEMBER_MISSION_NOT_FOUND));
        memberMission.setStatus(MissionStatus.IN_PROGRESS);
        return memberMission;
    }
}
