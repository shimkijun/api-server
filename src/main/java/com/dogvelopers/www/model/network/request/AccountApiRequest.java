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

    private Long id;

    private String userId;

    private String password;

    private String username;
}
