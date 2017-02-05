package com.xebia.xke.slack.http.web.api;

import feign.Feign;
import feign.gson.GsonDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractFeignClient {

  protected final Logger logger = LoggerFactory.getLogger(getClass());

  protected <T> T buildFeignClient(Class<T> api, String url) {
    logger.info("Build feign client with url {}", url);
    return Feign.builder().decoder(new GsonDecoder()).target(api, url);
  }
}
