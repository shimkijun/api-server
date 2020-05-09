package com.dogvelopers.www.service;

import com.dogvelopers.www.enumclass.UserRole;
import com.dogvelopers.www.interfaces.CrudInterface;
import com.dogvelopers.www.model.entity.Account;
import com.dogvelopers.www.model.network.Header;
import com.dogvelopers.www.model.network.request.AccountApiRequest;
import com.dogvelopers.www.model.network.response.AccountApiResponse;
import com.dogvelopers.www.repository.AccountRepository;
import com.dogvelopers.www.security.tokens.PostAuthorizationToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@Transactional
public class AccountService implements CrudInterface<AccountApiRequest,AccountApiResponse>{

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public Header<AccountApiResponse> create(AccountApiRequest request) {

        Optional<Account> existed = accountRepository.findByUserId(request.getUserId());

        if(existed.isPresent()){
            return Header.ERROR("이미 사용 중인 메일입니다.");
        }

        String bcryptPassword = passwordEncoder.encode(request.getPassword());

        Account account = Account.builder()
                .userId(request.getUserId())
                .username(request.getUsername())
                .password(bcryptPassword)
                .userRole(UserRole.USER)
                .build();

        Account newAccount = accountRepository.save(account);

        return Header.OK("회원가입에 성공하셨습니다.",response(newAccount));
    }

    @Override
    public Header<AccountApiResponse> read(Long id) {
        return accountRepository.findById(id)
                .map(user -> response(user))
                .map(accountApiResponse -> Header.OK("OK",accountApiResponse))
                .orElseGet(()->Header.ERROR("사용자가 존재하지 않습니다."));
    }

    @Override
    public Header<AccountApiResponse> update(AccountApiRequest accountApiRequest) {
        // 1. id -> user 데이터 찾기
        Optional<Account> getAccount = accountRepository.findByUserId(accountApiRequest.getUserId());
        return getAccount.map(account ->{
            log.error(accountApiRequest.getPassword());
            String bcryptPassword = passwordEncoder.encode(accountApiRequest.getPassword());
            // 2. update
            account.setUserId(getAccount.get().getUserId())
                    .setUsername(accountApiRequest.getUsername())
                    .setPassword(bcryptPassword);
            return account;
        })
        .map(account -> accountRepository.save(account))
        .map(account -> response(account))
        .map(accountApiResponse -> Header.OK("수정 되었습니다.",accountApiResponse))
        .orElseGet(() -> Header.ERROR("수정 실패하였습니다"));
    }

    @Override
    public Header delete(Long id) {
        return null;
    }


    private AccountApiResponse response(Account account){
        AccountApiResponse userApiResponse = AccountApiResponse.builder()
                .id(account.getId())
                .userId(account.getUserId())
                .username(account.getUsername())
                .userRole(account.getUserRole())
                .build();
        return userApiResponse;
    }

}
