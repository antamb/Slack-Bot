package com.xebia.xke.slack.entities;

import com.xebia.xke.slack.enums.Theme;

import java.util.StringJoiner;

public class Board {

  private static final String NEW_LINE = "\n";

  private int width;
  private int height;
  private Theme theme;
  private CharSequence[][] grid;

  public Board(int width, int height, Theme theme) {
    this.width = width;
    this.theme = theme;
    this.height = height;
    this.grid = new CharSequence[width][height];
    initialize();
  }

  public void initialize() {
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        grid[j][i] = theme.getBlankEmoji();
      }
    }
  }

  public CharSequence[][] getGrid() {
    return grid;
  }

  public void setGrid(CharSequence[][] grid) {
    this.grid = grid;
  }

  public int getWidth() {
    return width;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public int getHeight() {
    return height;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public Theme getTheme() {
    return theme;
  }

  public void setTheme(Theme theme) {
    this.theme = theme;
  }

  @Override
  public String toString() {
    StringJoiner joiner = new StringJoiner("");
    for (int i = 0; i < height; i++) {
      joiner.add("|");
      for (int j = 0; j < width; j++) {
        joiner.add(" " + grid[j][i]);
        joiner.add(" |");
      }
      joiner.add("\n");
    }
    return joiner.toString();
  }
}
