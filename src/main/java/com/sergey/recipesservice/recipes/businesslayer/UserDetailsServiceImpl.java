package com.sergey.recipesservice.recipes.businesslayer;

import com.sergey.recipesservice.recipes.databaselayer.User;
import com.sergey.recipesservice.recipes.persistence.UserRepository;
import com.sergey.recipesservice.recipes.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(userEmail);

        if (user == null) {
            throw new UsernameNotFoundException("Not found: " + userEmail);
        } else {
            return new UserDetailsImpl(user);
        }
    }
}

