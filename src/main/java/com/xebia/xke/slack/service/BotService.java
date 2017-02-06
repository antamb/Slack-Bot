package com.xebia.xke.slack.service;

import static com.xebia.xke.slack.enums.GameStatus.ABOUT_TO_PLAY;
import static com.xebia.xke.slack.enums.GameStatus.NOT_PLAYING;
import static com.xebia.xke.slack.enums.GameStatus.PLAYING;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.util.StringUtils;

import com.xebia.xke.slack.entities.Coordinate;
import com.xebia.xke.slack.entities.Member;
import com.xebia.xke.slack.entities.Player;
import com.xebia.xke.slack.enums.Emoji;
import com.xebia.xke.slack.enums.GameStatus;
import com.xebia.xke.slack.enums.Level;
import com.xebia.xke.slack.enums.Theme;

import me.ramswaroop.jbot.core.slack.models.Event;
import me.ramswaroop.jbot.core.slack.models.Message;

public class BotService {

	public static GameStatus GAME_STATUS = NOT_PLAYING;

	private Game game;
	private Map<String, Member> memberMap;

	public void setMembers(List<Member> members) {
		this.memberMap = members.stream()
				.collect(Collectors.toMap(Member::getId, Function.identity()));
	}

	public Member getMemberById(String id) {
		return memberMap.get(id);
	}

	public Message requestNewGame(String userName) {
		GAME_STATUS = ABOUT_TO_PLAY;
		return new Message("what's up " + userName + ", wanna play ?");
	}

	public Message initiateGame() {
		StringJoiner joiner = new StringJoiner("\n");
		Arrays.stream(Level.values())
				.forEach(level -> joiner.add(level.getDescription()));

		return new Message("Well, then make a choice: \n" + joiner.toString());
	}

	public Message startGame(Event event, String botName) {
		int depth = Integer.valueOf(event.getText());
		Level level = Level.getLevel(depth);
		Player botPlayer = new Player(botName, true);
		Player player = new Player(getMemberById(event.getUserId()).getName(),
				true);
		player.setEmoji(Emoji.CROSS);

		game = new Game(level, Theme.DEFAULT);
		game.start(botPlayer, player);
		GAME_STATUS = PLAYING;

		return new Message("Your turn! let's play, ! ("
				+ game.getPlayer().getEmoji().getCode() + ") ! \n\n"
				+ game.getBoard().toString());
	}

	public Message play(Event event) {
		String[] move = event.getText().split(":");
		Coordinate coordinate = new Coordinate(Integer.valueOf(move[0]),
				Integer.valueOf(move[1]));
		String message = game.play(coordinate);
		if (!StringUtils.isEmpty(message)) {
			GAME_STATUS = NOT_PLAYING;
		} else {
			message = "Your turn! Let's play choose a row:column ! ("
					+ game.getPlayer().getEmoji().getCode() + ") !";
		}

		return new Message(message + "\n\n" + game.getBoard().toString());
	}
}
