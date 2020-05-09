package com.dogvelopers.www.controller;

import com.dogvelopers.www.interfaces.CrudInterface;
import com.dogvelopers.www.model.network.Header;
import com.dogvelopers.www.model.network.request.AccountApiRequest;
import com.dogvelopers.www.model.network.response.AccountApiResponse;
import com.dogvelopers.www.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class AccountController implements CrudInterface<AccountApiRequest,AccountApiResponse> {

    @Autowired
    private AccountService accountService;

    @Override
    @PostMapping("/signup")
    public Header<AccountApiResponse> create(@RequestBody AccountApiRequest request) {
        log.error("{}",request);
        return accountService.create(request);
    }

    @Override
    @GetMapping("/api/user/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public Header<AccountApiResponse> read(@PathVariable Long id) {
        return accountService.read(id);
    }

    @Override
    @PutMapping("/api/user")
    @PreAuthorize("hasRole('ROLE_USER')")
    public Header<AccountApiResponse> update(@RequestBody AccountApiRequest request) {
        return accountService.update(request);
    }

    @Override
    public Header delete(Long id) {
        return null;
    }
}
