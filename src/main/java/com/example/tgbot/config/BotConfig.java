package com.example.tgbot.config;

import com.example.tgbot.bot.MyBot;
//import com.example.tgbot.entity.entitytg.Update;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.bots.DefaultBotOptions;


@Data
@Configuration
@ConfigurationProperties(prefix = "telegrambot")
public class BotConfig {
    private String botPath;
    private String botUserName;
    private String botToken;

    private DefaultBotOptions.ProxyType proxyType;
    private String proxyHost;
    private int proxyPort;

    @Bean
    public MyBot mybot(){


//		defaultBotOptions.setProxyType(DefaultBotOptions.ProxyType.HTTP);
//		defaultBotOptions.setProxyHost("10.50.71.254");
//		defaultBotOptions.setProxyPort(3128);

		MyBot myBot = new MyBot();
		myBot.setBotPath(botPath);
		myBot.setBotToken(botToken);
		myBot.setBotUserName(botUserName);

        return myBot;
    }
//    @Bean
//    public Update update(){
//        return new Update();
//    }
}
