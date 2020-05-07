package com.dogvelopers.www.security.tokens;

import com.dogvelopers.www.enumclass.SocialProviders;
import com.dogvelopers.www.model.network.request.AccountApiRequest;
import com.dogvelopers.www.model.network.response.SocialLoginResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class PreAuthorizationToken extends UsernamePasswordAuthenticationToken {

    private PreAuthorizationToken(String username, String password){
        super(username,password);
    }

    public PreAuthorizationToken(AccountApiRequest request){
        this(request.getUserId(),request.getPassword());
    }

    private PreAuthorizationToken(SocialProviders providers, SocialLoginResponse res){
        super(providers,res);
    }

    public PreAuthorizationToken(SocialLoginResponse res){
        this(res.getProviders(),res);
    }

    public String getUsername(){
        return (String)super.getPrincipal();
    }

    public String getUserPassword(){
        return (String)super.getCredentials();
    }
}
