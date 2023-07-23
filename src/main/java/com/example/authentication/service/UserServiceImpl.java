package com.example.authentication.service;

import com.example.authentication.config.Constants;
import com.example.authentication.entity.Token;
import com.example.authentication.entity.User;
import com.example.authentication.model.UserModel;
import com.example.authentication.repository.TokenRepository;
import com.example.authentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenRepository verificationTokenRepository;

    @Override
    public User registerUser(UserModel userModel) {
        User user = new User();
        user.setFirstName(userModel.getFirstName());
        user.setLastName(userModel.getLastName());
        user.setEmail(userModel.getEmail());
        user.setPassword(passwordEncoder.encode(userModel.getPassword()));
        user.setRole(Constants.USER);
        userRepository.save(user);
        return user;
    }

    @Override
    public void saveTokenForUser(String token, User user) {
        Token verificationToken = new Token(token,user);
        verificationTokenRepository.save(verificationToken);
    }
}
