package com.dogvelopers.www.repository;

import com.dogvelopers.www.enumclass.UserRole;
import com.dogvelopers.www.model.entity.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    void create(){
        Account account = Account.builder()
                .userId("skj2@naver.com")
                .password(passwordEncoder.encode("1234"))
                .username("tester02")
                .userRole(UserRole.USER)
                .build();

        accountRepository.save(account);
    }

    @Test
    void read(){
        Optional<Account> account = accountRepository.findById(2L);
        account.ifPresent(selectUser ->{
            System.out.println("user : "+selectUser);
            System.out.println("email : "+selectUser.getUserId());
        });
    }

    @Test
    @Transactional
    void update(){
        Optional<Account> account = accountRepository.findById(2L);
        account.ifPresent(selectUser ->{
            selectUser.setPassword(passwordEncoder.encode("2222"));
            selectUser.setUpdatedAt(LocalDateTime.now());
            selectUser.setUpdatedBy("update method()");
            accountRepository.save(selectUser);
        });
    }

    @Test
    @Transactional
    void delete(){
        Optional<Account> account = accountRepository.findById(2L);

        assertTrue(account.isPresent());

        account.ifPresent(selectUser ->{
            accountRepository.delete(selectUser);
        });

        Optional<Account> deleteUser = accountRepository.findById(2L);

        assertFalse(deleteUser.isPresent());

    }

}