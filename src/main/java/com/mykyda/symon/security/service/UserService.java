package com.mykyda.symon.security.service;

import com.mykyda.symon.api.service.ProfileService;
import com.mykyda.symon.security.database.entity.User;
import com.mykyda.symon.security.database.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final ProfileService profileService;

    @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Failed to retrieve user " + username));
    }

    @Transactional
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    @Transactional
    public User save(User userToSave) {
        var savedUser = userRepository.save(userToSave);
        profileService.createFromUser(savedUser);
        return savedUser;
    }

    @Transactional
    public void delete(Long id){
        userRepository.deleteById(id);
    }
}
