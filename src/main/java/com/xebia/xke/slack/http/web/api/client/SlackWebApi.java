package com.xebia.xke.slack.http.web.api.client;

import com.xebia.xke.slack.http.web.api.entities.DefaultResponse;
import com.xebia.xke.slack.http.web.api.entities.UsersResponse;
import feign.Param;
import feign.RequestLine;

public interface SlackWebApi {

  @RequestLine("GET /users.list?token={token}")
  UsersResponse getMembers(@Param("token") String token);
}
