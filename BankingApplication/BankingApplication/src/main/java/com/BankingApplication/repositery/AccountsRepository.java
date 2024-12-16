package com.BankingApplication.repositery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.BankingApplication.entity.Account;

@Repository
public interface AccountsRepository extends JpaRepository<Account, Long>{

}
