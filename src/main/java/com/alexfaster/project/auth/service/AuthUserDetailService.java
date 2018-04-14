package com.alexfaster.project.auth.service;

import com.alexfaster.project.auth.model.GaleraPrinciple;
import com.alexfaster.project.model.Account;
import com.alexfaster.project.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthUserDetailService implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Autowired
    public AuthUserDetailService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final Account account = accountRepository.findAccountByLogin(email);
        if (account == null) {
            throw new UsernameNotFoundException("User with following email doesn't exist");
        }
        return new GaleraPrinciple(account);
    }
}
