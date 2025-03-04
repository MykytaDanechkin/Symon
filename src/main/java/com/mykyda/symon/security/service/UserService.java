package com.mykyda.symon.security.service;

import com.mykyda.symon.api.service.MediaService;
import com.mykyda.symon.api.service.ProfileService;
import com.mykyda.symon.security.database.entity.User;
import com.mykyda.symon.security.database.repository.UserRepository;
import com.mykyda.symon.security.dto.UserEditDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final MediaService mediaService;

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
        return savedUser;
    }

    @Transactional
    public User patch(UserEditDTO patchData) {
        if (patchData.getAvatar() != null) {
            var newUrl = mediaService.reUploadProfileImage(patchData.getOldAvatar(),patchData.getAvatar());
            return userRepository.save(User.builder()
                    .id(patchData.getId())
                    .avatar(newUrl)
                    .username(patchData.getUsername())
                    .build());
        } else {
            return userRepository.save(User.builder()
                    .id(patchData.getId())
                    .username(patchData.getUsername())
                    .build());
        }
    }

    @Transactional
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
