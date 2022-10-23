package com.example.tgbot.services;

import com.example.tgbot.bot.MyBot;
import com.example.tgbot.entity.Application;
import com.example.tgbot.entity.Users;
import com.vdurmont.emoji.EmojiParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

@Service
public class BotService {
    String adminChatId = "-1001886697941";
    @Autowired
    MyBot myBot;
    public void webhook(Update update, ArrayList<Users> users) throws TelegramApiException {
        if(update.getMessage()!= null && update.getMessage().getText()!=null) {
            //authorized or not
            //student or not
            if(isAuthorized(String.valueOf(update.getMessage().getChatId()),users)){
                Users users1 = getUser(update.getMessage().getChatId().toString(),users);
                if(users1.getSession().equals("getPhone")){

                    users1.setNumber(update.getMessage().getText());
                    users1.setRole("student");
                    users1.setSession("start");
                    KeyboardButton keyboardButton = new KeyboardButton();
                    keyboardButton.setText("Murojat");
                    KeyboardRow keyboardRow = new KeyboardRow();
                    keyboardRow.add(keyboardButton);
                    ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
                    replyKeyboardMarkup.setKeyboard(List.of(keyboardRow));
                    replyKeyboardMarkup.setResizeKeyboard(true);
                    SendMessage sendMessage = new SendMessage();
                    sendMessage.setReplyMarkup(replyKeyboardMarkup);
                    sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
                    sendMessage.setText("Murojatingizni boshlang");
                    myBot.execute(sendMessage);

                }
                else if(users1.getSession().equals("getApplication")){
                    System.out.println("get Application session from admin");
                    System.out.println(update.getMessage().getReplyToMessage().getText().split("#")[0]+" this is chatId id in");
                    //Users user = getUserByApplication(update.getMessage().getReplyToMessage().getMessageId(),users);
                    SendMessage sendMessage = new SendMessage();
                    sendMessage.setText(update.getMessage().getText());

                    sendMessage.setChatId(update.getMessage().getReplyToMessage().getText().split("#")[0]);
                    myBot.execute(sendMessage);


                }
                else if(users1.getSession().equals("start")){
                    System.out.println("Start Session");
                    if(update.getMessage().getText().equals("Murojat")){
                        Application application = new Application();
                        application.setChatId(String.valueOf(update.getMessage().getChatId()));
                        application.setUsernumber(users1.getNumber());
                        users1.setApplication(application);

                        users1.setSession("getFIO");
                        SendMessage sendMessage = new SendMessage();

                        String person = EmojiParser.parseToUnicode(":bust_in_silhouette:");

                        sendMessage.setText("Iltimos F.I.Sh kiriting"+person);
                        sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
                        sendMessage.setReplyMarkup(new ReplyKeyboardRemove(true));
                        myBot.execute(sendMessage);
                    }
                }
                else if(users1.getSession().equals("getFIO")){
                    users1.setSession("getGroup");
                    users1.getApplication().setUsername(update.getMessage().getText());
                    SendMessage sendMessage = new SendMessage();
                    String group = EmojiParser.parseToUnicode(":busts_in_silhouette:");

                    sendMessage.setText("Iltimos guruhingizni kiriting"+group);
                    sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
                    sendMessage.setReplyMarkup(new ReplyKeyboardRemove(true));
                    myBot.execute(sendMessage);
                }
                else if(users1.getSession().equals("getGroup")){
                    users1.setSession("getText");
                    users1.getApplication().setUserGroup(update.getMessage().getText());
                    SendMessage sendMessage = new SendMessage();

                    String memo = EmojiParser.parseToUnicode(":memo:");
                    sendMessage.setText("Iltimos murojatingizni kiriting"+memo);
                    sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
                    sendMessage.setReplyMarkup(new ReplyKeyboardRemove(true));
                    myBot.execute(sendMessage);
                }
                else if(users1.getSession().equals("getText")){
                    users1.setSession("approve");
                    users1.getApplication().setText(update.getMessage().getText());
                    SendMessage sendMessage = new SendMessage();
                    String phone = EmojiParser.parseToUnicode(":iphone:");
                    String group = EmojiParser.parseToUnicode(":busts_in_silhouette:");
                    String application = EmojiParser.parseToUnicode(":memo:");
                    String person = EmojiParser.parseToUnicode(":bust_in_silhouette:");

                    String text = "Iltimos murojatingizni tasdiqlang\n"+
                            person+"F.I.Sh: "+users1.getApplication().getUsername()+"\n"+phone+
                            "Telefon raqam: "+users1.getApplication().getUsernumber()+
                            "\n"+group+"Guruh: "+users1.getApplication().getUserGroup()+"\n"+
                            application+"Murojat: "+users1.getApplication().getText();
                    sendMessage.setText(text);
                    sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
                    KeyboardButton approve = new KeyboardButton();
                    approve.setText("Tasdiqlash");
                    KeyboardButton cancel = new KeyboardButton();
                    cancel.setText("Bekor qilish");
                    KeyboardRow row1 = new KeyboardRow();
                    row1.add(approve);
                    KeyboardRow row2 = new KeyboardRow();
                    row2.add(cancel);
                    ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(List.of(row1, row2));
                    replyKeyboardMarkup.setResizeKeyboard(true);

                    sendMessage.setReplyMarkup(replyKeyboardMarkup);
                    myBot.execute(sendMessage);
                }
                else if(users1.getSession().equals("approve")){
                    if(update.getMessage().getText().equals("Tasdiqlash")){
                        String text = "Savolingiz yuborildi iltimos javobini kuting";
                        users1.setSession("start");
                        SendMessage sendMessage = new SendMessage();
                        sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
                        sendMessage.setText(text);
                        KeyboardButton keyboardButton = new KeyboardButton();
                        keyboardButton.setText("Murojat");
                        KeyboardRow keyboardRow = new KeyboardRow();
                        keyboardRow.add(keyboardButton);
                        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
                        replyKeyboardMarkup.setKeyboard(List.of(keyboardRow));
                        replyKeyboardMarkup.setResizeKeyboard(true);

                        sendMessage.setReplyMarkup(replyKeyboardMarkup);
                        myBot.execute(sendMessage);
                        //send to admins

                        SendMessage sendMessage1 = new SendMessage();
                        sendMessage1.setChatId(adminChatId);
                        String phone = EmojiParser.parseToUnicode(":iphone:");
                        String group = EmojiParser.parseToUnicode(":busts_in_silhouette:");
                        String person = EmojiParser.parseToUnicode(":bust_in_silhouette:");
                        String memo = EmojiParser.parseToUnicode(":memo:");
                        String application = users1.getChat_id()+"#\n"+person+"F.I.Sh: "+users1.getApplication().getUsername()+"\n"+
                                phone+"Telefon raqam: "+users1.getApplication().getUsernumber()+
                                "\n"+group+"Guruh: "+users1.getApplication().getUserGroup()+"\n"+
                                memo+"Murojat: "+users1.getApplication().getText();
                        sendMessage1.setText(application);
                        int messageId = myBot.execute(sendMessage1).getMessageId();
                        System.out.println("this is message id in admins chat "+messageId);
                        users1.getApplication().setMessageId(String.valueOf(messageId));




                    }
                    else if(update.getMessage().getText().equals("Bekor qilish")){
                        String text = "Murojatingiz bekor qilindi. Yangi murojat yoborishingiz mumkin";
                        users1.setSession("start");
                        users1.setApplication(new Application());
                        SendMessage sendMessage = new SendMessage();
                        sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
                        sendMessage.setText(text);
                        KeyboardButton keyboardButton = new KeyboardButton();
                        keyboardButton.setText("Murojat");
                        KeyboardRow keyboardRow = new KeyboardRow();
                        keyboardRow.add(keyboardButton);
                        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
                        replyKeyboardMarkup.setKeyboard(List.of(keyboardRow));
                        replyKeyboardMarkup.setResizeKeyboard(true);

                        sendMessage.setReplyMarkup(replyKeyboardMarkup);
                        myBot.execute(sendMessage);
                    }
                }
            }
            else if(update.getMessage().getChatId().toString().equals(adminChatId)){
                SendMessage sendMessage = new SendMessage();
                sendMessage.setText(update.getMessage().getText());

                sendMessage.setChatId(update.getMessage().getReplyToMessage().getText().split("#")[0]);
                myBot.execute(sendMessage);
            }
            else {
                Users users1 = new Users();
                users1.setChat_id(String.valueOf(update.getMessage().getChatId()));
                users1.setSession("getPhone");
                users.add(users1);
                SendMessage sendMessage = new SendMessage();
                sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
                String phone = EmojiParser.parseToUnicode(":iphone:");
                sendMessage.setText("Iltimos telefon raqamingizni yuboring"+phone);
                sendMessage.setReplyMarkup(new ReplyKeyboardRemove(true));
                myBot.execute(sendMessage);
            }
        }
    }


    private boolean isAuthorized(String chat_id, ArrayList<Users> users){
        boolean authorized = false;
        for(var user: users){
            if(chat_id.equals(user.getChat_id())){
                return true;
            }
        }
        return authorized;
    }

    private Users getUser(String chat_id, ArrayList<Users> users){
        for(var user: users){
            if(user.getChat_id().equals(chat_id))
                return user;
        }
        return null;
    }

    private Users getUserByApplication(Integer messageId, ArrayList<Users> users){
        if(messageId!=null){
            for(var user:users){
                if(user.getApplication().getMessageId().equals(String.valueOf(messageId)))
                    System.out.println("user found "+user.getApplication().getMessageId());
                    return user;
            }
            return null;
        }
        else return null;
    }
    public boolean hasDocs(Update update) {
        System.out.println("has doc");
        if(update.getMessage()!=null){
            if(update.getMessage().getText()==null && update.getMessage().getContact()==null){
                System.out.println("return true");
                return true;
            }
            return false;
        }
        return false;
    }
}
