package com.dogvelopers.www.security.tokens;

import com.dogvelopers.www.enumclass.UserRole;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class JwtPostProcessingToken extends UsernamePasswordAuthenticationToken {
    private JwtPostProcessingToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }

    public JwtPostProcessingToken(String username, UserRole role){
        super(username,"empty", parseAuthorities(role));
    }

    private static Collection<? extends  GrantedAuthority> parseAuthorities(UserRole role){
        return Arrays.asList(role).stream().map(r-> new SimpleGrantedAuthority(r.getTitle())).collect(Collectors.toList());
    }

    public String getUserId(){
        return (String)super.getPrincipal();
    }

    public String getPassword(){
        return (String)super.getCredentials();
    }
}
