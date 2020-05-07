package com.dogvelopers.www.controller;

import com.dogvelopers.www.enumclass.UserRole;
import com.dogvelopers.www.interfaces.CrudInterface;
import com.dogvelopers.www.model.entity.Account;
import com.dogvelopers.www.model.network.Header;
import com.dogvelopers.www.model.network.request.AccountApiRequest;
import com.dogvelopers.www.model.network.response.AccountApiResponse;
import com.dogvelopers.www.service.AccountService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class AccountController implements CrudInterface<AccountApiRequest,AccountApiResponse> {

    @Autowired
    private AccountService accountService;

    @GetMapping("/signin")
    public Header signin(){
        return Header.OK();
    }


    @Override
    @PostMapping("/signup")
    public Header<AccountApiResponse> create(@RequestBody AccountApiRequest request) {
        log.error("{}",request);
        return accountService.create(request);
    }

    @Override
    public Header<AccountApiResponse> read(Long id) {
        return null;
    }

    @Override
    public Header<AccountApiResponse> update(AccountApiRequest request) {
        return null;
    }

    @Override
    public Header delete(Long id) {
        return null;
    }
}
