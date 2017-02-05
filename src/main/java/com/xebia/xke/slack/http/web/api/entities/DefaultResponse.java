package com.xebia.xke.slack.http.web.api.entities;

public class DefaultResponse {
  private String ok;
  private String error;
  private String stuff;
  private String warning;

  public String getOk() {
    return ok;
  }

  public void setOk(String ok) {
    this.ok = ok;
  }

  public String getError() {
    return error;
  }

  public void setError(String error) {
    this.error = error;
  }

  public String getStuff() {
    return stuff;
  }

  public void setStuff(String stuff) {
    this.stuff = stuff;
  }

  public String getWarning() {
    return warning;
  }

  public void setWarning(String warning) {
    this.warning = warning;
  }
}
