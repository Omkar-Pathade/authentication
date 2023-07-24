package com.example.authentication.service;

import com.example.authentication.entity.Token;
import com.example.authentication.entity.User;
import com.example.authentication.model.UserModel;

import java.util.Optional;

public interface UserService {
    public User registerUser(UserModel userModel);

    public void saveTokenForUser(String token, User user);

    public String validateUserToken(String token);

    public Token resendVerificationToken(String token);

    User getUserByEmail(String email);

    void createPasswordResetTokenForUser(User user, String token);

    String validatePasswordResetToken(String token);

    Optional<User> getUserByPasswordResetToken(String token);

    void changePassword(User user, String newPassword);
}
