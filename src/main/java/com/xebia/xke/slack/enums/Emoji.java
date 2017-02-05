package com.xebia.xke.slack.enums;

public enum Emoji {
  CROSS(":x:"),
  BLANK(":blank:"),
  CERCLE(":o:");

  private CharSequence code;
  Emoji(CharSequence code) {
    this.code = code;
  }

  public CharSequence getCode() {
    return code;
  }
}
