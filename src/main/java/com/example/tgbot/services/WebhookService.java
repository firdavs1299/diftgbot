package com.example.tgbot.services;

import com.example.tgbot.bot.MyBot;
import com.example.tgbot.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import reactor.netty.udp.UdpServer;

import java.util.ArrayList;
import java.util.List;

@Service


public class WebhookService {

    @Autowired
    MyBot myBot;

//    public void receiveWebhook(Update update, ArrayList<Users> users) {
//        System.out.println("receive webhook");
//        if(update.getMessage().getChatId().toString().equals("-1001789232837"))
//        {
//            System.out.println("message from group");
//            //send to exact user
//            if(update.getMessage().getText().split("/")[0].equals("@dif_support_bot ")) {
//                String chat_id = update.getMessage().getText().split("/")[1];
//                SendMessage sendMessage = new SendMessage();
//                sendMessage.setText(update.getMessage().getText().split("/")[2]);
//                sendMessage.setChatId(chat_id);
//                try {
//                    myBot.execute(sendMessage);
//                } catch (TelegramApiException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            //send to every one
//            else{
//                for(var user: users){
//                    SendMessage sendMessage = new SendMessage();
//                    sendMessage.setText(update.getMessage().getText());
//                    sendMessage.setChatId(user.getChat_id());
//                    try {
//                        myBot.execute(sendMessage);
//                    } catch (TelegramApiException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//            }
//        }
//        else{
//            //if authorized
//                //send to group
//            if(isAuthorized(String.valueOf(update.getMessage().getChatId()),users)){
//                System.out.println("Authorized");
//                Users users1 = getUser(String.valueOf(update.getMessage().getChatId()),users);
//                System.out.println(update.getMessage().getText().split("/")[0]);
//
//                if(users1!=null){
//                        String message = (users1.getUsername()==null?"student":users1.getUsername())+"\n"+users1.getNumber()+"\n"+(users1.getFirstName()==null?"":users1.getFirstName())+" "+(users1.getLastName()==null?"":users1.getLastName())+"\n"+update.getMessage().getText();
//
//                        //otherwise send the message to every admins and super
//
//                        SendMessage sendMessage = new SendMessage();
//                        sendMessage.setText(message);
//                        sendMessage.setChatId("-1001789232837");
//                        InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
//                        inlineKeyboardButton.setText("Reply");
//                        inlineKeyboardButton.setSwitchInlineQueryCurrentChat("/"+update.getMessage().getChatId()+"/ ");
//                        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
//                        inlineKeyboardMarkup.setKeyboard(List.of(List.of(inlineKeyboardButton)));
//                        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
//                        try {
//                            myBot.execute(sendMessage);
//                        } catch (TelegramApiException e) {
//                            e.printStackTrace();
//                        }
//
//
//                }
//            }
//
//            //else
//                //authorize
//            else {
//                System.out.println("Not authorized");
//                if(update.getMessage()!=null){
//                    if(update.getMessage().getContact()!=null){
//                        Users users1 = checkTheUser(update,users);
//                        String message="Вы авторизованы";
//                        if(users1.getRole().equals("student")){
//                            message ="Можете задавать свой вопрос";
//                        }
//                        SendMessage sendMessage = new SendMessage();
//                        sendMessage.setChatId(users1.getChat_id());
//                        sendMessage.setText(message);
//                        sendMessage.setReplyMarkup(new ReplyKeyboardRemove(true));
//                        try {
//                            myBot.execute(sendMessage);
//                        } catch (TelegramApiException e) {
//                            e.printStackTrace();
//                        }
//                        //registerUser
//                    }
//                    else{
//                        System.out.println("send the phone please");
//                        KeyboardButton contact = new KeyboardButton();
//                        contact.setRequestContact(true);
//                        contact.setText("Отправить свой контакт");
//                        KeyboardRow row = new KeyboardRow();
//                        row.add(contact);
//                        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
//                        replyKeyboardMarkup.setKeyboard(List.of(row));
//                        replyKeyboardMarkup.setResizeKeyboard(true);
//                        try{
//                            SendMessage sendMessage = new SendMessage();
//                            sendMessage.setChatId(update.getMessage().getChatId().toString());
//                            sendMessage.setText("Пожалуйста отправьте свой номер телефона");
//                            sendMessage.setReplyMarkup(replyKeyboardMarkup);
//                            myBot.execute(sendMessage);
//                        }
//                        catch (Exception e){
//                            System.out.println(e.getMessage());
//                        }
//                        //please send phone number
//                        //add keyboard
//                    }
//                }
//            }
//        }
//
//    }
//    private boolean isAuthorized(String chat_id, ArrayList<Users> users){
//        boolean authorized = false;
//        for(var user: users){
//            if(chat_id.equals(user.getChat_id())){
//                return true;
//            }
//        }
//        return authorized;
//    }
//
//    private Users checkTheUser(Update update, ArrayList<Users> users1){
//        System.out.println("check user");
//        String phone = "";
//        if(update.getMessage().getContact().getPhoneNumber().length() == 13)
//            phone = update.getMessage().getContact().getPhoneNumber().substring(1);
//        else phone = update.getMessage().getContact().getPhoneNumber();
//        Users users = userDAO.getUser(phone);
//        if(users.getRole() == null){
//            System.out.println("Save user test");
//            userDAO.saveUser(update.getMessage().getChatId().toString(),
//                        phone,
//                        update.getMessage().getFrom().getId().toString(),
//                        update.getMessage().getFrom().getUserName(),
//                        update.getMessage().getContact().getFirstName(),
//                        update.getMessage().getContact().getLastName(),
//                        "student"
//                    );
//            users.setRole("student");
//            users.setFromUserId(update.getMessage().getFrom().getId().toString());
//            users.setUsername(update.getMessage().getFrom().getUserName());
//            users.setChat_id(update.getMessage().getChatId().toString());
//            users.setNumber(phone);
//            users.setFirstName(update.getMessage().getContact().getFirstName());
//            users.setLastName(update.getMessage().getContact().getLastName());
//        }
//        else {
//            userDAO.updateUser(update.getMessage().getChatId().toString(),
//                    phone,
//                    update.getMessage().getFrom().getId().toString(),
//                    update.getMessage().getFrom().getUserName(),
//                    update.getMessage().getContact().getFirstName(),
//                    update.getMessage().getContact().getLastName());
//            users.setFromUserId(update.getMessage().getFrom().getId().toString());
//            users.setUsername(update.getMessage().getFrom().getUserName());
//            users.setChat_id(update.getMessage().getChatId().toString());
//            users.setNumber(phone);
//            users.setFirstName(update.getMessage().getContact().getFirstName());
//            users.setLastName(update.getMessage().getContact().getLastName());
//        }
//        users1.add(users);
//        return  users;
//    }
//
//    private Users getUser(String chat_id, ArrayList<Users> users){
//        for(var user: users){
//            if(user.getChat_id().equals(chat_id))
//                return user;
//        }
//        return null;
//    }
//

}
