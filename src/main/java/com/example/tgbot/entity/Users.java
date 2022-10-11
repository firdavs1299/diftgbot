package com.example.tgbot.entity;

import lombok.Data;

@Data
public class Users {
    private String chat_id;
    private String number;
    private String session;
    private String role;
    private Application application;
}
