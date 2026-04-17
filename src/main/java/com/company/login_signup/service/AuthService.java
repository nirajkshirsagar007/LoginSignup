package com.company.login_signup.service;
import com.company.login_signup.dto.SignupRequest;
import com.company.login_signup.entity.User;
import com.company.login_signup.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    public String signup(SignupRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        User user = User.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .password(request.getPassword())   // hash later in PR4
                .role("USER")
                .build();

        userRepository.save(user);

        return "User registered successfully";
    }
}