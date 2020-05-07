package com.dogvelopers.www.enumclass;

import com.dogvelopers.www.model.social.KakaoUserProperty;
import com.dogvelopers.www.model.social.NaverUserProperty;
import com.dogvelopers.www.model.social.SocialUserProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum SocialProviders {

    KAKAO("https://kapi.kakao.com/v2/user/me", KakaoUserProperty.class),
    NAVER("https://openapi.naver.com/v1/nid/me", NaverUserProperty.class);

    private String userinfoEndpoint;
    private Class<? extends SocialUserProperty> propertyMetaclass;

    SocialProviders(String userinfoEndpoint, Class<? extends SocialUserProperty> propertyMetaclass) {
        this.userinfoEndpoint = userinfoEndpoint;
        this.propertyMetaclass = propertyMetaclass;
    }

    @JsonValue
    public String getProviderName() {
        return this.name();
    }
}
