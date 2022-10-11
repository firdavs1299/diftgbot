package com.example.tgbot.controllers;

import com.example.tgbot.bot.MyBot;


import com.example.tgbot.entity.Users;
import com.example.tgbot.services.BotService;
import com.example.tgbot.services.WebhookService;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import org.telegram.telegrambots.meta.api.methods.ActionType;

import org.telegram.telegrambots.meta.api.methods.send.SendChatAction;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/tg")
@Slf4j
public class TgApiController {


    @Autowired
    MyBot myBot;
    @Autowired
    WebhookService webhookService;
    @Autowired
    BotService botService;
//
    public ArrayList<Users> getUsers() {
        return users;
    }
//
    ArrayList<Users> users = new ArrayList<>();
//    @Autowired
//    FbsdaService fbsdaService;


    @PostMapping("/telegramm")
    public void tgwebhook(@RequestBody Update update) throws TelegramApiException, IOException {
//        System.out.println(update.toString());
        try {
            System.out.println(update.getMessage().getContact());
            if (update.getMessage() == null && update.getInlineQuery() == null && update.getCallbackQuery() == null && update.getEditedMessage() == null) {
                if (!users.isEmpty())
                    for (int i = 0; i < users.size(); i++) {
                        SendChatAction sendChatAction = new SendChatAction();
                        sendChatAction.setChatId(users.get(i).getChat_id());
                        sendChatAction.setAction(ActionType.TYPING);
                        try {
                            myBot.execute(sendChatAction);
                        } catch (Exception e) {
                            users.remove(i);
                            System.out.println("kirdi");
                            i--;
                        }
                    }
            }
            if(!botService.hasDocs(update)){
                System.out.println("receive webhook start");
                botService.webhook(update,users);
                System.out.println("receive webhook end");
            }

        }
        catch (Exception e){
            System.out.println("This is error"+e.getMessage());
        }
        //System.out.println(update.getMessage());
//        try {
//
//            botService.webhook(update,users);
//        }
//        catch (Exception e){
//            System.out.println(e.getMessage());
//        }

    }

}
