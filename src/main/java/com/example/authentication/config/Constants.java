package com.example.authentication.config;

public class Constants {
    public static final String USER = "USER";
    public static final String ADMIN = "ADMIN";
    //Exp time as 10 Min;
    public static final int expirationTime = 10;
    
    public static final String[] WHITELIST_URL = {
            "/hello",
            "/register",
            "/verifyRegistration",
            "/resendverificationtoken",
            "/resetPassword",
            "/savePassword"
    };
    public static final String VALID = "VALID";
    public static final String INVALID = "INVALID";
    public static final String EXPIRED = "EXPIRED";
    public static final String BAD_USER = "BAD USER";

    public static final String INVALID_TOKEN = "INVALID TOKEN";

}
