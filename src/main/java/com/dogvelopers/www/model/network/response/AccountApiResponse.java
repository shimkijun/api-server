package com.dogvelopers.www.model.network.response;

import com.dogvelopers.www.enumclass.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountApiResponse {

    private Long id;

    private String userId;

    private String username;

    private UserRole userRole;

}
