package com.dogvelopers.www.service;

import com.dogvelopers.www.enumclass.UserRole;
import com.dogvelopers.www.model.network.request.AccountApiRequest;
import com.dogvelopers.www.model.network.response.AccountApiResponse;
import com.dogvelopers.www.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@SpringBootTest
class AccountServiceTest {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    void createAccount(){
        AccountApiRequest accountApiRequest =
                AccountApiRequest.builder()
                        .userId("tester@example.com")
                        .password("1234")
                        .username("tester")
                        .build();

        AccountApiResponse mockAccount =
                AccountApiResponse.builder()
                        .userId("tester@example.com")
                        .username("tester")
                        .userRole(UserRole.USER)
                        .build();
        accountService.create(accountApiRequest);
        verify(accountRepository).save(any());
    }

}