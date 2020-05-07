package com.dogvelopers.www.model.network.request;

import com.dogvelopers.www.enumclass.UserRole;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountApiRequest {

    @JsonProperty("userId")
    private String userId;

    @JsonProperty("password")
    private String password;

    private String username;
}
