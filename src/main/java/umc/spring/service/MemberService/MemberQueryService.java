package umc.spring.service.MemberService;

import org.springframework.data.domain.Page;
import umc.spring.domain.Member;
import umc.spring.domain.mapping.MemberMission;

public interface MemberQueryService {
    Member findMemberById(Long id);
    boolean isMemberExist(Long value);
    Page<MemberMission> getMissionList(Long memberId, Integer page);
}
