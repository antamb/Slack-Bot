package com.xebia.xke.slack.service;

import static com.xebia.xke.slack.enums.GameStatus.ABOUT_TO_PLAY;
import static com.xebia.xke.slack.enums.GameStatus.NOT_PLAYING;
import static com.xebia.xke.slack.enums.GameStatus.PLAYING;

import java.util.regex.Matcher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import com.xebia.xke.slack.http.web.api.client.SlackWebApi;
import com.xebia.xke.slack.http.web.api.entities.UsersResponse;

import me.ramswaroop.jbot.core.slack.Bot;
import me.ramswaroop.jbot.core.slack.Controller;
import me.ramswaroop.jbot.core.slack.EventType;
import me.ramswaroop.jbot.core.slack.models.Event;
import me.ramswaroop.jbot.core.slack.models.Message;

@Component
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

	@Override
	public void afterConnectionEstablished(WebSocketSession session) {
		super.afterConnectionEstablished(session);
		UsersResponse usersResponse = slackWebApi.getMembers(slackToken);
		botService.setMembers(usersResponse.getMembers());
	}

	@Controller(events = EventType.MESSAGE)
	public void onReceiveMessage(WebSocketSession session, Event event,
			Matcher matcher) {
		reply(session, event, new Message("Message from: "
				+ botService.getMemberById(event.getUserId()).getRealName()));
	}

	@Controller(events = EventType.DIRECT_MESSAGE, pattern = "^yes$")
	public void onReceiveAnswer(WebSocketSession session, Event event,
			Matcher matcher) {
		if (BotService.GAME_STATUS == ABOUT_TO_PLAY) {
			reply(session, event, botService.initiateGame());
		}
	}
	@Controller(events = EventType.DIRECT_MESSAGE, pattern = "^(\\d+)$")
	public void onReceiveChoice(WebSocketSession session, Event event,
			Matcher matcher) {
		if (BotService.GAME_STATUS == ABOUT_TO_PLAY) {
			reply(session, event, botService.startGame(event,
					slackService.getCurrentUser().getName()));
		}
	}

	@Controller(events = EventType.DIRECT_MESSAGE, pattern = "^(\\d+):(\\d+)$")
	public void onReceiveMove(WebSocketSession session, Event event,
			Matcher matcher) {
		if (BotService.GAME_STATUS == PLAYING) {
			reply(session, event, botService.play(event));
		}
	}

	@Controller(events = EventType.DIRECT_MENTION)
	public void onDirectMention(WebSocketSession session, Event event,
			Matcher matcher) {
		if (BotService.GAME_STATUS == NOT_PLAYING) {
			reply(session, event, botService.requestNewGame(
					botService.getMemberById(event.getUserId()).getName()));
		}
	}
}
