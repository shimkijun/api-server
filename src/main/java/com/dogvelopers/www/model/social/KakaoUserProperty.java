package com.dogvelopers.www.model.social;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
public class KakaoUserProperty implements SocialUserProperty{

    @JsonProperty("kaccount_email_verified")
    private Boolean verified;

    @JsonProperty("kaccount_email")
    private String userEmail;

    @JsonProperty("id")
    private Long userUniqueId;

    @JsonProperty("properties")
    private Map<String,String> userProperties;

    @Override
    public String getUserId() {
        return userUniqueId.toString();
    }

    @Override
    public String getUserNickname() {
        return userProperties.get("nickname");
    }

    @Override
    public String getProfileHref() {
        return userProperties.get("profile");
    }

    @Override
    public String getEmail() {
        return userEmail;
    }

}
