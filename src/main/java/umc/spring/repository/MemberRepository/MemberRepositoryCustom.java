package umc.spring.repository.MemberRepository;

import umc.spring.domain.Member;

public interface MemberRepositoryCustom {
    Member dynamicQueryWithBooleanBuilder(Long id);
}
