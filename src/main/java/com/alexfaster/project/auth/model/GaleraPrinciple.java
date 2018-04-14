package com.alexfaster.project.auth.model;

import com.alexfaster.project.model.Account;
import com.alexfaster.project.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GaleraPrinciple implements UserDetails {

    private final Account account;

    public GaleraPrinciple(final Account account) {
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }

    public User getUser() {
        return account.getUser();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        final List<GrantedAuthority> authorities = new ArrayList<>();
//        for (final Privilege privilege : user.getPrivileges()) {
//            authorities.add(new SimpleGrantedAuthority(privilege.getName()));
//        }
        return authorities;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public String getPassword() {
        return account.getPassword();
    }

    @Override
    public String getUsername() {
        return account.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
}
