package com.xebia.xke.slack.service;

import com.xebia.xke.slack.entities.Board;
import com.xebia.xke.slack.entities.Coordinate;
import com.xebia.xke.slack.entities.Player;
import com.xebia.xke.slack.enums.Emoji;
import com.xebia.xke.slack.enums.Level;
import com.xebia.xke.slack.enums.Theme;
import com.xebia.xke.slack.service.ai.AI;
import com.xebia.xke.slack.service.ai.AIPlayer;

public class Game {

  private AI ai;
  private Level level;
  private Board board;
  private Theme theme;
  private Player player;
  private Player botPlayer;
  private boolean isPlaying;
  private boolean isAboutToPlay;

  public Game(Level level, Theme theme) {
    this.level = level;
    this.theme = theme;
    this.board = new Board(3, 3, theme);
  }

  public boolean isPlaying() {
    return isPlaying;
  }

  public void setPlaying(boolean playing) {
    isPlaying = playing;
  }

  public boolean isAboutToPlay() {
    return isAboutToPlay;
  }

  public void setAboutToPlay(boolean aboutToPlay) {
    isAboutToPlay = aboutToPlay;
  }

  public Board getBoard() {
    return board;
  }

  public Player getPlayer() {
    return player;
  }

  public void setPlayer(Player player) {
    this.player = player;
  }

  public void start(Player player1, Player player2) {
    if (level.equals(Level.SECOND_PLAYER)) {

    } else {
      this.player = player2;
      ai = new AIPlayer(level, board);
      ai.setEmoji(theme.getPlayer1Emoji());
      botPlayer = player1;
      botPlayer.setEmoji(Emoji.CERCLE);
      int[] nextMove = ai.getNextMove();
      board.getGrid()[nextMove[0]][nextMove[1]] = botPlayer.getEmoji().getCode();
    }
  }

  public String play(Coordinate move) {

    if (board.getGrid()[move.getX()][move.getY()].equals(theme.getBlankEmoji())) {
      board.getGrid()[move.getX()][move.getY()] = player.getEmoji().getCode();
      if (Game.hasWon(player.getEmoji().getCode(), board)) {
        return  "Yeah! Congratulation, you won!";
      }

      int[] nextMove = ai.getNextMove();
      if (board.getGrid()[nextMove[0]][nextMove[1]].equals(theme.getBlankEmoji())) {
        board.getGrid()[nextMove[0]][nextMove[1]] = botPlayer.getEmoji().getCode();
        if (Game.hasWon(botPlayer.getEmoji().getCode(), board)) {
          return "Oh oh! Sorry loserrr! GAME OVER!";
        }
      }
    }

    return "";
  }

  private static int[] winningPatterns = {
      0b111000000, 0b000111000, 0b000000111,
      0b100100100, 0b010010010, 0b001001001,
      0b100010001, 0b001010100
  };

  public static boolean hasWon(CharSequence thePlayer, Board board) {
    int pattern = 0b000000000;
    for (int row = 0; row < board.getWidth(); ++row) {
      for (int col = 0; col < board.getHeight(); ++col) {
        if (board.getGrid()[row][col] == thePlayer) {
          pattern |= (1 << (row * board.getHeight() + col));
        }
      }
    }
    for (int winningPattern : winningPatterns) {
      if ((pattern & winningPattern) == winningPattern) return true;
    }
    return false;
  }
}
