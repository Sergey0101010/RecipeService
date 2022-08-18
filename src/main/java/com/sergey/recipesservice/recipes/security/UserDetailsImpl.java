package com.sergey.recipesservice.recipes.security;

import com.sergey.recipesservice.recipes.databaselayer.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;

public class UserDetailsImpl implements UserDetails {

    private static final GrantedAuthority GRANTED_AUTHORITY = new SimpleGrantedAuthority("USER");
    private final String email;
    private final String password;

    private final List<GrantedAuthority> rolesAndAuthorities;


    public UserDetailsImpl(User user) {
        email = user.getEmail();
        password = user.getPassword();
        rolesAndAuthorities = List.of(GRANTED_AUTHORITY);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return rolesAndAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
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
