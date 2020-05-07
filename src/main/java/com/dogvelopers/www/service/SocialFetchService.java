package com.dogvelopers.www.service;

import com.dogvelopers.www.enumclass.SocialProviders;
import com.dogvelopers.www.model.network.response.SocialLoginResponse;
import com.dogvelopers.www.model.social.SocialUserProperty;
import com.dogvelopers.www.repository.SocialFetchRepository;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class SocialFetchService implements SocialFetchRepository {
    private static final String HEADER_PREFIX = "Bearer ";

    @Override
    public SocialUserProperty getSocialUserProperty(SocialLoginResponse res) {
        SocialProviders provider = res.getProviders();
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> entity = new HttpEntity<>("parameter",generateHeader(res.getToken()));
        return restTemplate.exchange(provider.getUserinfoEndpoint(), HttpMethod.GET, entity, provider.getPropertyMetaclass()).getBody();
    }

    private HttpHeaders generateHeader(String token){
        HttpHeaders headers = new HttpHeaders();

        headers.add("Authorization",generateHeaderContent(token));

        return headers;
    }

    private String generateHeaderContent(String token){
        StringBuilder sb = new StringBuilder();

        sb.append(HEADER_PREFIX);
        sb.append(token);

        return sb.toString();
    }
}
