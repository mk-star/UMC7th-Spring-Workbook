package umc.spring.service.MemberService;

import umc.spring.domain.Member;

public interface MemberQueryService {
    Member findMemberById(Long id);
}
