package com.example.authentication.service;

import com.example.authentication.entity.User;
import com.example.authentication.model.UserModel;

public interface UserService {
    User registerUser(UserModel userModel);

    void saveTokenForUser(String token, User user);
}
