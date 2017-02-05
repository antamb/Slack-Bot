package com.xebia.xke.slack.enums;

import java.util.Arrays;
import java.util.Optional;

public enum Level {
  EASY(1, "1. Facile"),
  MEDIUM(2, "2. Moyen"),
  HARD(3, "3. Guru"),
  SECOND_PLAYER(-1, "4. Jouer avec un ami");

  private int depth;
  private String description;
  Level(int depth, String description) {
    this.depth = depth;
    this.description = description;
  }

  public int getDepth() {
    return depth;
  }

  public void setDepth(int depth) {
    this.depth = depth;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public static Level getLevel(int depth) {
    Optional<Level> optional = Arrays.stream(Level.values()).filter(level -> level.getDepth() == depth).findFirst();
    return optional.get();
  }
}
