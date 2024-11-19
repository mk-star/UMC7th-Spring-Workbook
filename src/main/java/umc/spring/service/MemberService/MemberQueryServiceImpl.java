package umc.spring.service.MemberService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.Member;
import umc.spring.repository.MemberRepository.MemberRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberQueryServiceImpl implements MemberQueryService{
    private final MemberRepository memberRepository;

    @Override
    public Member findMemberById(Long id) {
        Member member = memberRepository.dynamicQueryWithBooleanBuilder(id);
        System.out.println("Member: " + member);
        return member;
    }
}
