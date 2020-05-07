package com.dogvelopers.www.model.network.response;

import com.dogvelopers.www.enumclass.SocialProviders;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SocialLoginResponse {

    @JsonProperty("provider")
    private SocialProviders providers;

    @JsonProperty("token")
    private String token;

}
