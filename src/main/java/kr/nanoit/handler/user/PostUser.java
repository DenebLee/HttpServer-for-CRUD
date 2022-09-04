package kr.nanoit.handler.user;

import static kr.nanoit.extension.Variable.APPLICATION_JSON_CHARSET_UTF_8;
import static kr.nanoit.extension.Variable.CHARSET;
import static kr.nanoit.extension.Variable.HEADER_CONTENT_TYPE;
import static kr.nanoit.extension.Variable.STATUS_OK;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import kr.nanoit.extension.RelatedBody;
import kr.nanoit.user.UserService;
import org.json.JSONArray;

import javax.json.Json;
import javax.json.JsonArray;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;



/*
 * 클래스 한개는 하나의 일만 한다.
 */
public final class PostUser {

  private PostUser() {
  }

  public static void handle(HttpExchange exchange) {
    try {
      // print
      InputStream inputStream = exchange.getRequestBody();
      System.out.println(RelatedBody.parseBody(new BufferedReader(new InputStreamReader(inputStream, CHARSET))));

      // response
      Headers headers = exchange.getResponseHeaders();
      headers.add(HEADER_CONTENT_TYPE, APPLICATION_JSON_CHARSET_UTF_8);
      exchange.sendResponseHeaders(STATUS_OK, 0);

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      exchange.close();
    }
  }



}
