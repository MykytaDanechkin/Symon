package com.mykyda.symon.security.service;

import com.mykyda.symon.security.database.entity.Role;
import com.mykyda.symon.security.database.entity.User;
import com.mykyda.symon.security.dto.LoginDto;
import com.mykyda.symon.security.dto.RegistrationDefaultDto;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    @Transactional
    public ResponseEntity<?> login(LoginDto loginDto, HttpServletResponse response) {
        var user = userService.findByEmail(loginDto.getEmail());
        if (user != null) {
            try {
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
                jwtService.setToken(user, response);
                return ResponseEntity.ok("Success!");
            } catch (BadCredentialsException e) {
                return new ResponseEntity<>("Incorrect password or email", HttpStatus.UNAUTHORIZED);
            }
        } else {
            return new ResponseEntity<>("Incorrect password or email", HttpStatus.NOT_FOUND);
        }
    }

    @Transactional
    public ResponseEntity<String> register(RegistrationDefaultDto rdd) {
        if (userService.findByEmail(rdd.getEmail()) == null) {
            var user = User.builder()
                    .email(rdd.getEmail())
                    .password(passwordEncoder.encode(rdd.getPassword()))
                    .role(Role.USER)
                    .username(rdd.getUsername())
                    .avatar("https://symonappprofilepicturebucket.s3.eu-north-1.amazonaws.com/user.png")
                    .build();
            userService.save(user);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("email already in use", HttpStatus.CONFLICT);
        }
    }
}
