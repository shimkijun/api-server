package com.dogvelopers.www.security.tokens;

import com.dogvelopers.www.model.network.response.SocialLoginResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class SocialPreAuthorizationToken  extends UsernamePasswordAuthenticationToken {

    public SocialPreAuthorizationToken(SocialLoginResponse res) {
        super(res.getProviders(),res);
    }

    public SocialLoginResponse getResponse(){
        return (SocialLoginResponse)super.getCredentials();
    }
}
