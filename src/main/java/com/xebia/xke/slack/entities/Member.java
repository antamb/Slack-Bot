package com.xebia.xke.slack.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Member {

  private String id;
  private String name;
  private String realName;
  private Profile profile;

  @JsonProperty(value = "team_id")
  private String teamId;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getTeamId() {
    return teamId;
  }

  public void setTeamId(String teamId) {
    this.teamId = teamId;
  }

  public String getRealName() {
    return realName;
  }

  public void setRealName(String realName) {
    this.realName = realName;
  }

  public Profile getProfile() {
    return profile;
  }

  public void setProfile(Profile profile) {
    this.profile = profile;
  }
}
