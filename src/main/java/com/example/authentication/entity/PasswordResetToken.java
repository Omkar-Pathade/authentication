package com.example.authentication.entity;

import com.example.authentication.config.Constants;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class PasswordResetToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    private Date expirationTime;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "FK_USER_PASSWORD_TOKEN"))
    private User user;

    public PasswordResetToken(String token, User user) {
        this.token = token;
        this.user = user;
        this.expirationTime = calculateExpirationDate(Constants.expirationTime);
    }

    public PasswordResetToken(String token){
        super();
        this.token=token;
        this.expirationTime = calculateExpirationDate(Constants.expirationTime);
    }
    private Date calculateExpirationDate(int expirationTime) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(new Date().getTime());
        cal.add(cal.MINUTE, expirationTime);
        return new Date(cal.getTime().getTime());
    }
}
