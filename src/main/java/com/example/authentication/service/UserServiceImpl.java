package com.example.authentication.service;

import com.example.authentication.config.Constants;
import com.example.authentication.entity.Token;
import com.example.authentication.entity.User;
import com.example.authentication.model.UserModel;
import com.example.authentication.repository.TokenRepository;
import com.example.authentication.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.UUID;

@Slf4j
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
        log.info(user.toString());
        System.out.println(user.toString());
        userRepository.save(user);

        return user;
    }

    @Override
    public void saveTokenForUser(String token, User user) {
        Token verificationToken = new Token(token,user);
        verificationTokenRepository.save(verificationToken);
    }

    @Override
    public String validateUserToken(String token) {
        Token verificationToken = verificationTokenRepository.findByToken(token);
        if(verificationToken==null){
            return Constants.INVALID;
        }
        User user = verificationToken.getUser();
        Calendar cal = Calendar.getInstance();
        if(verificationToken.getExpirationTime().getTime()-cal.getTime().getTime()<0){
            verificationTokenRepository.delete(verificationToken);
            return Constants.EXPIRED;
        }
        user.setEnabled(true);
        userRepository.save(user);
        return Constants.VALID;
    }

    @Override
    public Token resendVerificationToken(String token) {
        Token verificationToken = verificationTokenRepository.findByToken(token);
        verificationToken.setToken(UUID.randomUUID().toString());
        verificationTokenRepository.save(verificationToken);
        return verificationToken;
    }
}
