package com.xebia.xke.slack.http.web.api.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.xebia.xke.slack.entities.Member;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UsersResponse {
  private List<Member> members;

  public List<Member> getMembers() {
    return members;
  }

  public void setMembers(List<Member> members) {
    this.members = members;
  }
}
