package com.example.authentication.controller;

import com.example.authentication.config.Constants;
import com.example.authentication.entity.Token;
import com.example.authentication.entity.User;
import com.example.authentication.event.RegistrationCompletionEvent;
import com.example.authentication.model.UserModel;
import com.example.authentication.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class RegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @PostMapping("/register")
    public String registerUser(@RequestBody  UserModel userModel, final HttpServletRequest request){
        User user = userService.registerUser(userModel);
        publisher.publishEvent(new RegistrationCompletionEvent(user,
                applicationUrl(request)));
        return "Success";
    }

    @GetMapping("/verifyRegistration")
    public String verifyRegistration(@RequestParam("token") String token){
        String result = userService.validateUserToken(token);
        if(result.equalsIgnoreCase(Constants.VALID)){
            return "User Verified successfully";
        }
        return Constants.BAD_USER;
    }

    @GetMapping("/resendverificationtoken")
    public String resendVerificationToken(@RequestParam("token") String token,HttpServletRequest request){
        Token newToken = userService.resendVerificationToken(token);
        User user = newToken.getUser();
        resendVerificationTokenToUser(user,applicationUrl(request),newToken);
        return "Token Re-sent";
    }

    private void resendVerificationTokenToUser(User user, String applicationUrl, Token newToken) {

        String url = applicationUrl
                +"verifyRegistration?token="
                +newToken.getToken();
        log.info("click the link to verify account {}",url);
    }

    private String applicationUrl(HttpServletRequest request) {
        return "http://"
                +request.getServerName()
                +":"
                +request.getServerPort()
                +"/"
                +request.getContextPath();
    }
}
