package com.xebia.xke.slack.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.xebia.xke.slack.http.web.api.client.SlackWebApi;

import me.ramswaroop.jbot.core.slack.Bot;

public class TicTacToeBot extends Bot {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(TicTacToeBot.class);

	@Value("${slackBotToken}")
	private String slackToken;

	private BotService botService;
	private SlackWebApi slackWebApi;

	@Autowired
	public TicTacToeBot(BotService botService, SlackWebApi slackWebApi) {
		this.botService = botService;
		this.slackWebApi = slackWebApi;
	}

	@Override
	public String getSlackToken() {
		return slackToken;
	}

	@Override
	public Bot getSlackBot() {
		return this;
	}

}
