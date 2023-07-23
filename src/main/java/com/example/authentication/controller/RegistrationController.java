package com.example.authentication.controller;

import com.example.authentication.entity.User;
import com.example.authentication.event.RegistrationCompletionEvent;
import com.example.authentication.model.UserModel;
import com.example.authentication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @PostMapping("/register")
    public String registerUser(@RequestBody  UserModel userModel){
        User user = userService.registerUser(userModel);
        publisher.publishEvent(new RegistrationCompletionEvent(user,""));
        return "Success";
    }
}
