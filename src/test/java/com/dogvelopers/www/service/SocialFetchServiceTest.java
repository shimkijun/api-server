package com.dogvelopers.www.service;

import com.dogvelopers.www.enumclass.SocialProviders;
import com.dogvelopers.www.model.network.response.SocialLoginResponse;
import com.dogvelopers.www.model.social.SocialUserProperty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;


@SpringBootTest
class SocialFetchServiceTest {

    private SocialFetchService pord = new SocialFetchService();

    private SocialLoginResponse res;

    @BeforeEach
    void setUp(){
        this.res = new SocialLoginResponse(SocialProviders.KAKAO,"iXsYvnZCshloB3H_xSokpmgGL3vOlc8q3GQxpgopcNIAAAFx3Wn9vA");
    }

    void restTemplateTest(){
        String a = new RestTemplate().getForObject("http://www.naver.com",String.class);
        System.out.println(a);
    }

    @Test
    void service_fetchinfo(){
        SocialUserProperty property = pord.getSocialUserProperty(this.res);

        System.out.println(property);
    }

}