package com.dogvelopers.www.controller;

import com.dogvelopers.www.enumclass.UserRole;
import com.dogvelopers.www.model.entity.Account;
import com.dogvelopers.www.model.network.Header;
import com.dogvelopers.www.model.network.request.AccountApiRequest;
import com.dogvelopers.www.model.network.response.AccountApiResponse;
import com.dogvelopers.www.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerTest {

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext wac;

    @MockBean
    private AccountService accountService;

    @BeforeEach
    void beforeEach(){
        mvc = MockMvcBuilders
                .webAppContextSetup(wac)
                .addFilter(((request, response, chain) -> {
                    request.setCharacterEncoding("UTF-8");
                    response.setCharacterEncoding("UTF-8");
                    chain.doFilter(request,response);
                }))
                .alwaysDo(print())
                .build();
    }

    @Test
    void successAccount() throws Exception {

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

        given(accountService.create(accountApiRequest)).willReturn(Header.OK(mockAccount));

        mvc.perform(post("/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"userId\":\"test@naver.com\",\"username\":\"test\",\"password\":\"1234\"}"))
                .andExpect(status().isOk())
                .andDo(print());

        verify(accountService).create(any());
    }

    @Test
    void failureAccount() throws Exception {

        mvc.perform(post("/signup")
                .content("{\"userId\":\"test@naver.com\",\"username\":\"test\",\"password\":\"1234\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultCode").value("NO"))
                .andExpect(jsonPath("$.description").value("NO"))
                .andDo(print());
    }

}