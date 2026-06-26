package com.example.securefileshare.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.securefileshare.entity.User;
import com.example.securefileshare.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public long getUserCount() {
    return userRepository.count();
}

    public User login(String username, String password) {

    Optional<User> user = userRepository.findByUsername(username);

    if (user.isPresent()) {

        if (passwordEncoder.matches(password,
                user.get().getPassword())) {

            return user.get();
        }
    }

    return null;
}

}