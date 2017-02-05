package com.xebia.xke.slack.entities;

import com.xebia.xke.slack.enums.Emoji;

public class Player {

  private String name;
  private Emoji emoji;
  private boolean isABot;

  public Player(String name, boolean isBot) {
    this.name = name;
    this.isABot = isBot;
  }

  public boolean isABot() {
    return isABot;
  }

  public String getName() {
    return name;
  }

  public Emoji getEmoji() {
    return emoji;
  }

  public void setEmoji(Emoji emoji) {
    this.emoji = emoji;
  }
}
