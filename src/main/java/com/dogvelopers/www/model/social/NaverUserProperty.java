package com.dogvelopers.www.model.social;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
public class NaverUserProperty implements SocialUserProperty{

    @JsonProperty("resultCode")
    private String resultCode;

    @JsonProperty("message")
    private String message;

    @JsonProperty("response")
    private Map<String,String> properties;

    @Override
    public String getUserId() {
        return properties.get("id");
    }

    @Override
    public String getUserNickname() {
        return properties.get("nickname");
    }

    @Override
    public String getProfileHref() {
        return properties.get("profile_image");
    }

    @Override
    public String getEmail() {
        return properties.get("email");
    }
}
