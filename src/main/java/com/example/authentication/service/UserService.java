package com.example.authentication.service;

import com.example.authentication.entity.Token;
import com.example.authentication.entity.User;
import com.example.authentication.model.UserModel;

public interface UserService {
    public User registerUser(UserModel userModel);

    public void saveTokenForUser(String token, User user);

    public String validateUserToken(String token);

    public Token resendVerificationToken(String token);
}
