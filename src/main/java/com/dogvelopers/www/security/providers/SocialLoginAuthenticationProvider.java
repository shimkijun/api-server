package com.dogvelopers.www.security.providers;

import com.dogvelopers.www.enumclass.SocialProviders;
import com.dogvelopers.www.enumclass.UserRole;
import com.dogvelopers.www.model.entity.Account;
import com.dogvelopers.www.model.network.response.SocialLoginResponse;
import com.dogvelopers.www.model.social.SocialUserProperty;
import com.dogvelopers.www.repository.AccountRepository;
import com.dogvelopers.www.security.AccountContext;
import com.dogvelopers.www.security.tokens.PostAuthorizationToken;
import com.dogvelopers.www.security.tokens.SocialPreAuthorizationToken;
import com.dogvelopers.www.repository.SocialFetchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SocialLoginAuthenticationProvider implements AuthenticationProvider {

    private final AccountRepository accountRepository;

    @Qualifier("socialFetchServiceProd")
    private final SocialFetchRepository service;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        SocialPreAuthorizationToken token = (SocialPreAuthorizationToken)authentication;
        SocialLoginResponse res = token.getResponse();

        return PostAuthorizationToken.getTokenFromAccountContext(AccountContext.fromAccountModel(getAccount(res)));
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return SocialPreAuthorizationToken.class.isAssignableFrom(authentication);
    }

    private Account getAccount(SocialLoginResponse res){
        SocialUserProperty property = service.getSocialUserProperty(res);
        String userId = property.getUserId();
        SocialProviders provider = res.getProviders();
        Account newAccount = Account.builder()
                .id(null)
                .username(property.getUserNickname())
                .userId("SOCIAL_USER")
                .password(String.valueOf(UUID.randomUUID().getMostSignificantBits()))
                .userRole(UserRole.USER)
                .socialId(Long.valueOf(property.getUserId()))
                .socialProviders(provider)
                .profileHref(property.getProfileHref())
                .build();
        return accountRepository.findBySocialIdAndSocialProviders(Long.valueOf(userId), provider)
                .orElseGet(() -> accountRepository.save(newAccount));

    }
}
