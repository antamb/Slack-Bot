package com.xebia.xke.slack.http.web.api.client;

import com.xebia.xke.slack.http.web.api.AbstractFeignClient;
import com.xebia.xke.slack.http.web.api.entities.UsersResponse;

public class SlackWebClient extends AbstractFeignClient implements SlackWebApi {
	private SlackWebApi slackWebApi;

	public SlackWebClient(String url) {
		slackWebApi = buildFeignClient(SlackWebApi.class, url);
	}

	@Override
	public UsersResponse getMembers(String token) {
		return slackWebApi.getMembers(token);
	}
}
