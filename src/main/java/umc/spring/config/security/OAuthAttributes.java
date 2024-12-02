package umc.spring.config.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;
import java.util.HashMap;

@Getter
@AllArgsConstructor
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String nickname;
    private String email;

    // 해당 로그인인 서비스가 kakao인지 google인지 구분하여, 알맞게 매핑을 해주도록 합니다.
    // 여기서 registrationId는 OAuth2 로그인을 처리한 서비스 명("google","kakao","naver"..)이 되고,
    // userNameAttributeName은 해당 서비스의 map의 키값이 되는 값이됩니다. {google="sub", kakao="id", naver="response"}
    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        if (registrationId.equals("kakao")) {
            return ofKakao(userNameAttributeName, attributes);
        }
        return ofNaver(userNameAttributeName, attributes);
    }

    private static OAuthAttributes ofKakao(String userNameAttributeName, Map<String, Object> attributes) {
        Map<String, Object> kakao_account = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> profile = (Map<String, Object>) kakao_account.get("profile");
        String email = profile.get("nickname") + "@kakao.com";   // 마찬가지로 profile(nickname, image_url.. 등) 정보가 담긴 값을 꺼낸다.

        Map<String, Object> modifiedAttributes = new HashMap<>(attributes);
        modifiedAttributes.put("email", email);

        return new OAuthAttributes(modifiedAttributes,
                userNameAttributeName,
                (String) profile.get("nickname"),
                email);
    }

    private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");    // 네이버에서 받은 데이터에서 프로필 정보다 담긴 response 값을 꺼낸다.
        String email = response.get("name") + "@naver.com";

        Map<String, Object> modifiedAttributes = new HashMap<>(attributes);
        modifiedAttributes.put("email", email);

        return new OAuthAttributes(modifiedAttributes,
                userNameAttributeName,
                (String) response.get("name"),
                email);
    }
}
