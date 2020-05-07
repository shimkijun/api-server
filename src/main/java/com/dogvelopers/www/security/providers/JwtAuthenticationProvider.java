package com.dogvelopers.www.security.providers;

import com.dogvelopers.www.security.AccountContext;
import com.dogvelopers.www.security.jwt.JwtDecoder;
import com.dogvelopers.www.security.tokens.JwtPreProcessingToken;
import com.dogvelopers.www.security.tokens.PostAuthorizationToken;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationProvider implements AuthenticationProvider {
    private final JwtDecoder jwtDecoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String token = (String)authentication.getPrincipal();
        AccountContext context = jwtDecoder.decodeJwt(token);
        return PostAuthorizationToken.getTokenFromAccountContext(context);

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtPreProcessingToken.class.isAssignableFrom(authentication);
    }
}
