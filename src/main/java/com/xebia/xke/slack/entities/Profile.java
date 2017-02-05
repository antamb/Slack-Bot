package com.xebia.xke.slack.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Profile {

  private String email;

  @JsonProperty(value = "last_name")
  private String lastName;

  @JsonProperty(value = "first_name")
  private String firstName;

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
