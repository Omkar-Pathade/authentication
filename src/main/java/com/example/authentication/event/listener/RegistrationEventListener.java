package com.example.authentication.event.listener;

import com.example.authentication.entity.User;
import com.example.authentication.event.RegistrationCompletionEvent;
import com.example.authentication.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class RegistrationEventListener implements ApplicationListener<RegistrationCompletionEvent> {

    @Autowired
    private UserService userService;
    @Override
    public void onApplicationEvent(RegistrationCompletionEvent event) {
        //create verification Token for user with link
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        userService.saveTokenForUser(token,user);

        String url = event.getApplicationUrl()
                +"verifyRegistration?token="
                +token;
        log.info("click the link to verify account {}",url);
    }
}
