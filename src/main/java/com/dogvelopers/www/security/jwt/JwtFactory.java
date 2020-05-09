package com.dogvelopers.www.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.dogvelopers.www.security.AccountContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class JwtFactory {

    private static String signinKey = "dogvelopers";

    public String generateToken(AccountContext context){
        String token = null;

        try{
            token = JWT.create()
                    .withIssuer(context.getAccount().getUserId())
                    .withClaim("ID",context.getAccount().getId())
                    .withClaim("USERNAME",context.getAccount().getUsername())
                    .withClaim("USER_ROLE",context.getAccount().getUserRole().getTitle())
                    .sign(generateAlgorithm());
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return token;
    }

    private Algorithm generateAlgorithm(){
        return Algorithm.HMAC256(signinKey);
    }

}
