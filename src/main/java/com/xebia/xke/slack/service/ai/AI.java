package com.xebia.xke.slack.service.ai;

import com.xebia.xke.slack.entities.Board;
import com.xebia.xke.slack.enums.Level;
import com.xebia.xke.slack.enums.Theme;

public abstract class AI {
  protected Level level;
  protected Board board;
  protected CharSequence maxEmoji;
  protected CharSequence minEmoji;

  public AI(Level level, Board board) {
    this.level = level;
    this.board = board;
  }

  public void setEmoji(CharSequence emoji) {
    this.maxEmoji = emoji;
    minEmoji = (maxEmoji == Theme.DEFAULT.getPlayer1Emoji()) ?
        Theme.DEFAULT.getPlayer2Emoji() : Theme.DEFAULT.getPlayer1Emoji();
  }

  abstract public int[] getNextMove();
}
