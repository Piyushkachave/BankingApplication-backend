package com.BankingApplication.serivce.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.BankingApplication.Mapper.AccountMapper;
import com.BankingApplication.dto.AccountDto;
import com.BankingApplication.entity.Account;
import com.BankingApplication.repositery.AccountsRepository;
import com.BankingApplication.serivce.AccountService;

@Service
public class AccountServiceimpl implements AccountService{
     
	private AccountsRepository accountRepository;
	
	public AccountServiceimpl(AccountsRepository accountRepository) {
		super();
		this.accountRepository = accountRepository;
	}
	

	@Override
	public AccountDto createAccount(AccountDto accountDto) {
	
		 Account account=AccountMapper.mapToAccount(accountDto);
		 Account savedAccount= accountRepository.save(account);
		 
		return AccountMapper.mapToAccountDto(savedAccount);
	}


	@Override
	public AccountDto getAccountById(Long id) {
		// TODO Auto-generated method stub
		Account account= accountRepository.findById(id).orElseThrow(()->new RuntimeException("Account does not exist"));
		return AccountMapper.mapToAccountDto(account);
	}


	@Override
	public AccountDto deposit(Long id, double amount) {
		// TODO Auto-generated method stub
		Account account= accountRepository.findById(id).orElseThrow(()->new RuntimeException("Account does not exist"));
		double totalBalance=account.getBalance()+amount;
		account.setBalance(totalBalance);
		Account saveAccount=accountRepository.save(account);
		
		return AccountMapper.mapToAccountDto(saveAccount);
	}


	@Override
	public AccountDto withdraw(Long id, double amount) {
		// TODO Auto-generated method stub
		Account account= accountRepository.findById(id).orElseThrow(()->new RuntimeException("Account does not exist"));
		if(account.getBalance()<amount)
		{
			throw new RuntimeException("Insufficient Balance");
			
		}
		double totalBalance=account.getBalance()-amount;
		account.setBalance(totalBalance);
		Account savedAccount=accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}


	@Override
	public List<AccountDto> getAllAcccounts() {
		// TODO Auto-generated method stub
		return accountRepository.findAll().stream().map((account)->AccountMapper.mapToAccountDto(account)).collect(Collectors.toList());
		
		
	}


	@Override
	public void deleteAccount(Long id) {
		// TODO Auto-generated method stub
		Account account= accountRepository.findById(id).orElseThrow(()->new RuntimeException("Account does not exist"));
		accountRepository.delete(account);
	}

}
