package com.cancodevery.ecom.user;

import com.cancodevery.ecom.Role.Roles;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class UserDetail implements UserDetails {



    private String email;
    private String password;

    private List<GrantedAuthority> roles;

    public UserDetail(User user) {
        this.email=user.getEmail();
        this.password=user.getPassword();
        this.roles= List.of(new SimpleGrantedAuthority(user.getRoles().name()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        log.info("roles are {}",roles);
        return roles;
    }

    @Override
    public String getPassword() {
        log.info("password is {}",password);
        return password;
    }

    @Override
    public String getUsername() {
        log.info("email is {}",email);
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
