package com.mykyda.symon.security.service;

import com.mykyda.symon.security.database.entity.Role;
import com.mykyda.symon.security.database.entity.User;
import com.mykyda.symon.security.database.repository.UserRepository;
import com.mykyda.symon.security.dto.RegistrationDefaultDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username){
        return userRepository.findByEmail(username)
                .map(user -> new org.springframework.security.core.userdetails.User(
                        user.getEmail(),
                        user.getPassword(),
                        Collections.singleton(user.getRole())))
                .orElseThrow(() -> new UsernameNotFoundException("Failed to retrieve user " + username));
    }

    public ResponseEntity<String> register(RegistrationDefaultDto rdd){
        if (userRepository.findByEmail(rdd.getEmail()).isEmpty()) {
            var user = User.builder()
                    .email(rdd.getEmail())
                    .password(rdd.getPassword())
                    .role(Role.USER)
                    .username(rdd.getUsername())
                    .build();
            userRepository.save(user);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("email already in use",HttpStatus.CONFLICT);
        }
    }
}
