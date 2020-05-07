package com.dogvelopers.www.service;

import com.dogvelopers.www.enumclass.UserRole;
import com.dogvelopers.www.interfaces.CrudInterface;
import com.dogvelopers.www.model.entity.Account;
import com.dogvelopers.www.model.network.Header;
import com.dogvelopers.www.model.network.request.AccountApiRequest;
import com.dogvelopers.www.model.network.response.AccountApiResponse;
import com.dogvelopers.www.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
