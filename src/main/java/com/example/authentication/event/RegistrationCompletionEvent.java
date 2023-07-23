package com.example.authentication.event;

import com.example.authentication.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class RegistrationCompletionEvent extends ApplicationEvent {
    private User user;
    private String applicationUrl;
    public RegistrationCompletionEvent(User user, String applicationUrl) {
        super(user);
        this.user = user;
        this.applicationUrl = applicationUrl;

    }
}
