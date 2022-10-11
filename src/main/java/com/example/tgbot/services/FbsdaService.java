//package com.example.tgbot.services;
//
//import com.example.tgbot.Repositories.CommentRepository;
//import com.example.tgbot.Repositories.IssueRepository;
//import com.example.tgbot.Repositories.ProjectRepository;
//import com.example.tgbot.Repositories.UserRepository;
//import com.example.tgbot.bot.MyBot;
//
//import com.example.tgbot.entity.*;
//
//import com.example.tgbot.entity.entitytg.Issues;
//import com.example.tgbot.entity.entitytg.User;
//import com.example.tgbot.model.ProjectBankModel;
//import com.example.tgbot.payload.*;
//import com.example.tgbot.payload.Result;
//import lombok.RequiredArgsConstructor;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestClientException;
//import org.springframework.web.client.RestTemplate;
//import org.telegram.telegrambots.meta.api.methods.groupadministration.ExportChatInviteLink;
//import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
//import org.telegram.telegrambots.meta.api.objects.ChatMember;
//import org.telegram.telegrambots.meta.api.objects.Update;
//import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
//import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
//import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
//import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
//import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
//import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
//
//
//import java.nio.channels.Channels;
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class FbsdaService {
//
////    private final RestTemplate restTemplate;
//    @Autowired
//    MyBot myBot;
//    @Autowired
//    UserRepository userRepository;
//    @Autowired
//    ProjectRepository projectRepository;
//    @Autowired
//    IssueRepository issueRepository;
//    @Autowired
//    WebhookService webhookService;
//    @Autowired
//    CommentRepository commentRepository;
//    public void sendToSupport(int user_id, int issue_id, String message) {
//        commentRepository.registerNewComment(user_id, issue_id, message);
////        ResponseBody responseBody = new ResponseBody();
////        responseBody.setComment(message);
////        responseBody.setIssue_id(issue_id);
////        responseBody.setChat_id(update.getMessage().getChatId());
////        RequestSupport requestSupport = new RequestSupport();
////
////        requestSupport.setMethodName("getTgComment");
////        requestSupport.setRequestId(String.valueOf(id));
////
////        requestSupport.setRequestBody(responseBody);
////
////        HttpHeaders httpHeaders = new HttpHeaders();
////        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
////
////        HttpEntity<RequestSupport> httpEntity = new HttpEntity<>(requestSupport, httpHeaders);
////
////        System.out.println(requestSupport.getExServiceUrl());
////        System.out.println(requestSupport.getRequestId());
////        System.out.println(requestSupport.getRequestBody());
////        try{
////            return new RestTemplate().postForObject(requestSupport.getExServiceUrl(),
////                    httpEntity, Result.class);
////        } catch (RestClientException e) {
////            e.printStackTrace();
////        }
////        return new Result(0,"msg", new Object());
//    }
//
//
//    public List<ProjectBankModel> getProjectList(String issue_type, int project_page) {
////        ResponseBody responseBody = new ResponseBody();
////        responseBody.setIssue_type(issue_type);
////        responseBody.setProject_page(project_page);
////        RequestSupport requestSupport = new RequestSupport();
////
////        requestSupport.setMethodName("get_Projects");
////        requestSupport.setRequestId(String.valueOf(id));
////
////        requestSupport.setRequestBody(responseBody);
//
////        HttpHeaders httpHeaders = new HttpHeaders();
////        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
////
////        HttpEntity<RequestSupport> httpEntity = new HttpEntity<>(requestSupport, httpHeaders);
////
////        System.out.println(requestSupport.getExServiceUrl());
////        System.out.println(requestSupport.getRequestId());
////        System.out.println(requestSupport.getRequestBody());
////        try{
////            return new RestTemplate().postForObject(requestSupport.getExServiceUrl(),
////                    httpEntity, ProjectsResult.class);
////        } catch (RestClientException e) {
////            e.printStackTrace();
////        }
////        return null;
//        List<String> list = new ArrayList<>();
//        Pageable pageable = PageRequest.of(project_page/2,2);
//        return projectRepository.myMethod(issue_type,pageable).getContent();
//    }
//
//    public String getName(String telegram_number, Long chat_id){
//        FBSD_R_USERS fbsd_r_users = userRepository.findByTELEGRAM_NUMBER(telegram_number);
//        if(fbsd_r_users!=null){
//            if(fbsd_r_users.getCHAT_ID()!=chat_id){
//                userRepository.changeChatIdByTelegramNumber(chat_id,telegram_number);
//            }
//            return fbsd_r_users.getNAME();
//        }
//        return "user not found";
//    }
//
//    public String getUserName(String telegram_number, String chat_id) {
////        ResponseBody responseBody = new ResponseBody();
////        responseBody.setChat_id(Integer.parseInt(chat_id));
//        if(telegram_number.length()>=12)
//        {
//            if(telegram_number.substring(0,4).equals("+998") || telegram_number.substring(0,3).equals("998"))
//                if(!telegram_number.substring(0,1).equals("+")){
//                    telegram_number = "+"+telegram_number;
//                    System.out.println(telegram_number);
//                }
//            return getName(telegram_number, Long.valueOf(chat_id));
////            responseBody.setTelegram_number(telegram_number);
//        }
//        else{
////            responseBody.setTelegram_number("telegram_number");
//            return "invalid number format";
//        }
////        RequestSupport requestSupport = new RequestSupport();
////        requestSupport.setMethodName("get_User_Name");
////        requestSupport.setRequestId("a"+String.valueOf(id));
////        requestSupport.setRequestBody(responseBody);
//
////        HttpHeaders httpHeaders = new HttpHeaders();
////        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
////
////        HttpEntity<RequestSupport> httpEntity = new HttpEntity<>(requestSupport, httpHeaders);
//
////        System.out.println(requestSupport.getExServiceUrl());
////        System.out.println(requestSupport.getRequestId());
////        System.out.println(requestSupport.getRequestBody());
////        try{
////            UserResult userResult = new RestTemplate().postForObject(requestSupport.getExServiceUrl(),
////                    httpEntity, UserResult.class);
////            if(userResult==null){
////                return null;
////            }
////            else return userResult;
////        } catch (RestClientException e) {
////            e.printStackTrace();
////        }
//
//    }
//
//
//    public List<Issues> getIssues(int project_id, int issue_page) {
////        ResponseBody responseBody = new ResponseBody();
////        responseBody.setProject_id(project_id);
////        responseBody.setIssue_page(issue_page);
////        RequestSupport requestSupport = new RequestSupport();
////        requestSupport.setMethodName("get_Issues");
////        requestSupport.setRequestId(String.valueOf(id));
////
////        requestSupport.setRequestBody(responseBody);
////
////        HttpHeaders httpHeaders = new HttpHeaders();
////        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
////
////        HttpEntity<RequestSupport> httpEntity = new HttpEntity<>(requestSupport, httpHeaders);
////
////        System.out.println(requestSupport.getExServiceUrl());
////        System.out.println(requestSupport.getRequestId());
////        System.out.println(requestSupport.getRequestBody());
////        try{
////            return new RestTemplate().postForObject(requestSupport.getExServiceUrl(),
////                    httpEntity, IssuesResults.class);
////        } catch (RestClientException e) {
////            e.printStackTrace();
////        }
////        return null;
//        List<String> list = new ArrayList<>();
//        Pageable pageable = PageRequest.of(issue_page/10,10);
//        return issueRepository.getIssues(Long.valueOf(project_id),pageable).getContent();
//
//    }
//
//    public Issues getIssue(int issue_id) {
//        return issueRepository.getIssue(Long.valueOf(issue_id));
////        ResponseBody responseBody = new ResponseBody();
////        responseBody.setIssue_id(issue_id);
////        RequestSupport requestSupport = new RequestSupport();
////        requestSupport.setMethodName("get_Issue_Information");
////        requestSupport.setRequestId(String.valueOf(id));
////        requestSupport.setRequestBody(responseBody);
////
////        HttpHeaders httpHeaders = new HttpHeaders();
////        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
////
////        HttpEntity<RequestSupport> httpEntity = new HttpEntity<>(requestSupport, httpHeaders);
////
////        System.out.println(requestSupport.getExServiceUrl());
////        System.out.println(requestSupport.getRequestId());
////        System.out.println(requestSupport.getRequestBody());
////        try{
////            return new RestTemplate().postForObject(requestSupport.getExServiceUrl(),
////                    httpEntity, IssueResult.class);
////        } catch (RestClientException e) {
////            e.printStackTrace();
////        }
////        return null;
//
//    }
//
//
//    public int validateMessage(Update update){
//        int id=0;
////        boolean correctMessage = false;
//        if(update.getMessage().getText().length()>28){
//            if("@firdavstesttestbot #".equals(update.getMessage().getText().substring(0,21))){
//                if(update.getMessage().getText().charAt(27)=='#'){
//                    id = Integer.parseInt(update.getMessage().getText().substring(21,27));
//                    System.out.println("id = "+id);
////                    correctMessage = true;
//                }
//            }
//        }
//        return id;
//    }
//
//    public void sendCommentToAll(Update update, String message, String id, ArrayList<Users> users) throws TelegramApiException {
//        String userName="";
//        for (var item: users)
//            if(item.getChat_id().equals(update.getMessage().getChatId().toString()))
//                userName= item.getUserName();
//        for(var item: users){
//            if(!item.getChat_id().equals(update.getMessage().getChatId().toString())){
//                sendCommentTochat(update, message, id, item,userName);
//            }
//        }
////        if("chooseIssueType".equals(item.getSession())){
////            sendCommentTochat(update, message, id, item,userName);
////        }
//
//    }
//
//    private void sendCommentTochat(Update update, String message, String id, Users users, String userName) throws TelegramApiException {
//        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
//        InlineKeyboardButton button = new InlineKeyboardButton("reply");
//        String newId = String.valueOf((1000000+Integer.parseInt(id)));
//        button.setSwitchInlineQueryCurrentChat("#" + newId.substring(1) + "# ");
////        button.setCallbackData(id);
//        List<InlineKeyboardButton> list = new ArrayList<>();
//        list.add(button);
//        List<List<InlineKeyboardButton>> lists = new ArrayList<>();
//        lists.add(list);
//        keyboardMarkup.setKeyboard(lists);
//        String sendtotg = userName + " add comment to Issue#" + id + "\n" + message;
//        myBot.execute(new SendMessage(users.getChat_id(), sendtotg).setReplyMarkup(keyboardMarkup).setParseMode("html"));
//    }
//
//    public boolean isAuthorized(String chat_id, ArrayList<Users> users){
//        for(var item: users){
//            if(item.getChat_id().equals(chat_id))
//                return true;
//        }
//        return false;
//    }
//
//    public void registerNewUser(String chat_id, String username, ArrayList<Users> users, String last_message_id, int user_id) throws TelegramApiException {
//        users.add(new Users(chat_id,username, "chooseIssueType", last_message_id, user_id));
//        KeyboardButton applications = new KeyboardButton();
//        KeyboardButton letters = new KeyboardButton();
//        KeyboardButton logout = new KeyboardButton();
//        applications.setText("Applications");
//        letters.setText("Letters");
//        logout.setText("Logout");
//        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
//        KeyboardRow keyboardRow1 = new KeyboardRow();
//        KeyboardRow keyboardRow2 = new KeyboardRow();
//        KeyboardRow keyboardRow3 = new KeyboardRow();
//        keyboardRow1.add(applications);
//        keyboardRow2.add(letters);
//        keyboardRow3.add(logout);
//        List<KeyboardRow> list = new ArrayList<>();
//        list.add(keyboardRow1);
//        list.add(keyboardRow2);
////        list.add(keyboardRow3);
//        replyKeyboardMarkup.setKeyboard(list);
//        replyKeyboardMarkup.setResizeKeyboard(true);
//        myBot.execute(new SendMessage(chat_id, "Вы авторизованы").setReplyMarkup(replyKeyboardMarkup));
//        myBot.execute(new SendMessage(chat_id, "Choose IssueType").setReplyMarkup(replyKeyboardMarkup));
//    }
//
//}
