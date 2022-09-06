package kr.nanoit.handler.user;

import static kr.nanoit.extension.Variable.APPLICATION_JSON_CHARSET_UTF_8;
import static kr.nanoit.extension.Variable.CHARSET;
import static kr.nanoit.extension.Variable.HEADER_CONTENT_TYPE;
import static kr.nanoit.extension.Variable.HTTP_OK;
import static kr.nanoit.utils.HandlerUtil.print;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

import java.io.*;
import java.util.List;
import java.util.Map;

import kr.nanoit.object.dto.UserDto;
import kr.nanoit.extension.QueryParsing;
import kr.nanoit.extension.RelatedBody;


/*
 * 클래스 한개는 하나의 일만 한다.
 */
public final class PostUser {

  private PostUser() {
  }

  public static void handle(HttpExchange exchange) {
    try {
      print(exchange);

      // response
      Map<String, List<String>> param = QueryParsing.splitQuery(exchange.getRequestURI().getRawQuery());
      System.out.println(param);

      UserDto userDto = new UserDto();
      userDto.setId(Long.parseLong(param.get("id").get(0)));
      userDto.setPassword(param.get("password").toString());
      userDto.setEmail(param.get("email").toString());


      Headers headers = exchange.getResponseHeaders();
      headers.add(HEADER_CONTENT_TYPE, APPLICATION_JSON_CHARSET_UTF_8);
      exchange.sendResponseHeaders(HTTP_OK, 0);
      OutputStream outputStream = exchange.getResponseBody();
      outputStream.write(RelatedBody.makeBody().getBytes());
      outputStream.flush();

      OutputStreamWriter outputStreamWriter = new OutputStreamWriter(exchange.getResponseBody(), CHARSET);
      outputStreamWriter.write(RelatedBody.makeBody());
      outputStreamWriter.close();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      exchange.close();
    }
  }



}
