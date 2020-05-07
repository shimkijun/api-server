package com.dogvelopers.www.repository;

import com.dogvelopers.www.model.network.response.SocialLoginResponse;
import com.dogvelopers.www.model.social.SocialUserProperty;

public interface SocialFetchRepository {
    SocialUserProperty getSocialUserProperty(SocialLoginResponse res);
}
