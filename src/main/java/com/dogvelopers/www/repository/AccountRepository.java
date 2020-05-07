package com.dogvelopers.www.repository;

import com.dogvelopers.www.enumclass.SocialProviders;
import com.dogvelopers.www.model.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account,Long> {

    Optional<Account> findByUserId(String userId);

    Optional<Account> findBySocialId(String socialId);

    Optional<Account> findBySocialIdAndSocialProviders(Long socialId, SocialProviders socialProvider);
}
