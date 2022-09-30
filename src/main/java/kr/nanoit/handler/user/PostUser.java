package kr.nanoit.handler.user;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import com.sun.net.httpserver.HttpExchange;
import kr.nanoit.db.impl.userservice.UserService;
import kr.nanoit.exception.CreateFailedException;
import kr.nanoit.exception.DtoReadException;
import kr.nanoit.exception.PostException;
import kr.nanoit.object.dto.UserDto;
import kr.nanoit.object.entity.UserEntity;
import kr.nanoit.utils.Mapper;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import static kr.nanoit.handler.common.HandlerUtil.badRequest;
import static kr.nanoit.handler.common.HandlerUtil.responseOk;
import static kr.nanoit.handler.common.Validation.internalServerError;
import static kr.nanoit.handler.common.Validation.requestedValidate;
import static kr.nanoit.utils.GlobalVariable.HEADER_CONTENT_TYPE;

@Slf4j
public final class PostUser {
    private final UserService userService;

    public PostUser(UserService userService) {
        this.userService = userService;
    }


    public void handle(HttpExchange exchange) {

        try {
            // debugging
//            ExchangeRawPrinter.print(exchange);

            if (!exchange.getRequestHeaders().containsKey(HEADER_CONTENT_TYPE)) {
                throw new PostException("not found: Content-Type Header");
            }

            if (!exchange.getRequestHeaders().get(HEADER_CONTENT_TYPE).get(0).equalsIgnoreCase("application/json")) {
                throw new PostException("accept Content-Type: application/json");
            }

            String body = CharStreams.toString(new InputStreamReader(exchange.getRequestBody(), Charsets.UTF_8));
            UserDto userDto = getRead(body);
            if (userDto == null) {
                throw new DtoReadException("parse failed");
            }
            if (userDto.getId() != 0) {
                throw new DtoReadException("The id value should not be requested");
            }

            if (userDto.getUsername() == null) {
                throw new DtoReadException("not found: user.username");
            }

            if (userDto.getPassword() == null) {
                throw new DtoReadException("not found: user.password");
            }

            if (userDto.getEmail() == null) {
                throw new DtoReadException("not found: user.email");
            }
            if (requestedValidate(userDto.getEmail()) == false) {
                throw new PostException("The requested e-mail doesn't fit the format");
            }

            UserEntity userEntity = userService.save(userDto.toEntity());
            responseOk(exchange, Mapper.writePretty(userEntity.toDto()).getBytes(StandardCharsets.UTF_8));
        } catch (PostException e) {
            badRequest(exchange, e.getReason());
        } catch (CreateFailedException e) {
            badRequest(exchange, e.getReason());
        } catch (DtoReadException e) {
            badRequest(exchange, e.getReason());
        } catch (Exception e) {
            log.error("POST /user handler error occurred", e);
            internalServerError(exchange, "Unknown Error");
        } finally {
            exchange.close();
        }
    }

    private UserDto getRead(String body) {
        try {
            return Mapper.read(body, UserDto.class);
        } catch (Exception e) {
            log.error("Json Read error", e);
            return null;
        }
    }
}
