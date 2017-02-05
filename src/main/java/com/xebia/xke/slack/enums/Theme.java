package com.xebia.xke.slack.enums;

public enum Theme {
  DEFAULT(":cross:",":cercle:",":blank:"),
  STAR_WARS("::","::",":blank:"),
  WOOT_WOOT("::","::",":blank:");

  private CharSequence blankEmoji;
  private CharSequence player1Emoji;
  private CharSequence player2Emoji;
  Theme(CharSequence player1Emoji, CharSequence player2Emoji, CharSequence blankEmoji) {
    this.blankEmoji = blankEmoji;
    this.player1Emoji = player1Emoji;
    this.player2Emoji = player2Emoji;
  }

  public CharSequence getPlayer1Emoji() {
    return player1Emoji;
  }

  public void setPlayer1Emoji(CharSequence player1Emoji) {
    this.player1Emoji = player1Emoji;
  }

  public CharSequence getPlayer2Emoji() {
    return player2Emoji;
  }

  public void setPlayer2Emoji(CharSequence player2Emoji) {
    this.player2Emoji = player2Emoji;
  }

  public CharSequence getBlankEmoji() {
    return blankEmoji;
  }

  public void setBlankEmoji(CharSequence blankEmoji) {
    this.blankEmoji = blankEmoji;
  }
}
