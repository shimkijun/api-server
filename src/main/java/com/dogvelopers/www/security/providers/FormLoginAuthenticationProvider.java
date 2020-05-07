package com.dogvelopers.www.security.providers;

import com.dogvelopers.www.exception.InvalidJwtException;
import com.dogvelopers.www.model.entity.Account;
import com.dogvelopers.www.model.network.Header;
import com.dogvelopers.www.repository.AccountRepository;
import com.dogvelopers.www.security.AccountContext;
import com.dogvelopers.www.security.tokens.PostAuthorizationToken;
import com.dogvelopers.www.security.tokens.PreAuthorizationToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
@Slf4j
public class FormLoginAuthenticationProvider implements AuthenticationProvider {

    private final PasswordEncoder passwordEncoder;
    private final AccountRepository accountRepository;

    public FormLoginAuthenticationProvider(PasswordEncoder passwordEncoder, AccountRepository accountRepository) {
        this.passwordEncoder = passwordEncoder;
        this.accountRepository = accountRepository;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        PreAuthorizationToken token = (PreAuthorizationToken)authentication;

        String username = token.getUsername();
        String password = token.getUserPassword();

        Account account = accountRepository.findByUserId(username).orElseThrow(() -> new InvalidJwtException("이메일이 존재하지 않습니다."));

        if(isCorrectPassword(password, account)) {
            return PostAuthorizationToken.getTokenFromAccountContext(AccountContext.fromAccountModel(account));
        }

        throw new InvalidJwtException("비밀번호가 틀립니다");
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return PreAuthorizationToken.class.isAssignableFrom(aClass);
    }

    private boolean isCorrectPassword(String password, Account account) {
        return passwordEncoder.matches(password,account.getPassword());
    }
}
