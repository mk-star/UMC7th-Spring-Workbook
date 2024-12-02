package umc.spring.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import umc.spring.domain.Member;
import umc.spring.domain.enums.Gender;
import umc.spring.domain.enums.Role;
import umc.spring.domain.enums.SocialType;
import umc.spring.repository.MemberRepository.MemberRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        // 현재 진행중인 서비스를 구분하기 위해 문자열로 받음. {registrationId='naver'} 이런 식으로 반환
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        SocialType socialType = getSocialType(registrationId);
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        // socialType에 따라 유저 정보를 통해 OAuthAttributes 객체 생성
        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        // 사용자 정보 저장 또는 업데이트
        Member member = saveOrUpdateUser(attributes, socialType);

        return new DefaultOAuth2User(
                oAuth2User.getAuthorities(), // 사용자의 권한
                attributes.getAttributes(), // 사용자의 속성
                "email"  // email을 Principal로 설정
        );
    }

    private Member saveOrUpdateUser(OAuthAttributes attributes, SocialType socialType) {
        Member member = memberRepository.findByEmail(attributes.getEmail())
                .orElse(Member.builder()
                        .email(attributes.getEmail())
                        .name(attributes.getNickname())
                        .password(passwordEncoder.encode("OAUTH_USER_" + UUID.randomUUID()))
                        .gender(Gender.NONE)  // 기본값 설정
                        .address("소셜 로그인")  // 기본값 설정
                        .specAddress("소셜 로그인")  // 기본값 설정
                        .socialType(socialType)
                        .role(Role.USER)
                        .build());

        return memberRepository.save(member);
    }

    private SocialType getSocialType(String registrationId) {
        switch (registrationId) {
            case "kakao":
                return SocialType.KAKAO;
            case "naver":
                return SocialType.NAVER;
            default:
                return SocialType.NONE;
        }
    }
}