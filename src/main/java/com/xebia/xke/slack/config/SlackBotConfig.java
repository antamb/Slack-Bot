package com.xebia.xke.slack.config;

import com.xebia.xke.slack.service.BotService;
import com.xebia.xke.slack.http.web.api.client.SlackWebApi;
import com.xebia.xke.slack.http.web.api.client.SlackWebClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SlackBotConfig {

  @Bean("BotService")
  public BotService botService() {
    return new BotService();
  }

  @Bean("SlackWebApi")
  public SlackWebApi slackWebApi(@Value("${slackWebApiUrl}") String url) {
    return new SlackWebClient(url);
  }
}
