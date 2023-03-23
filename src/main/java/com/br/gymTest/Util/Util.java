package com.br.gymTest.Util;

import com.br.gymTest.Usuario.model.User;
import com.br.gymTest.Usuario.model.dto.UserDTO;
import com.br.gymTest.exceptions.DefaultAbstractException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Date;
import java.util.Optional;

import static com.br.gymTest.exceptions.LogLevel.*;


@Service
@Slf4j
public class Util {

    public static void logger(String method, Class className, String domain,
                              DefaultAbstractException exception, Optional<Object> value){
        String message = MessageFormat.format(
                "{0} from class: [{1}] in domain {2} throwed: {3}\nPayload: {4}",
                method,
                className.getName(),
                domain.toUpperCase(),
                exception.getMessage(),
                value.orElse("null")
        );

        switch (exception.getLogLevel()) {
            case INFO:
                log.info(message);
                break;
            case WARN:
                log.warn(message);
                break;
            case ERROR:
                log.error(message);
                break;
            case DEBUG:
                log.debug(message);
        }
    }

    public static void logger(String method, Class className, String domain,
                              Exception exception) {
        logger(method, className, domain, new DefaultAbstractException(exception.getMessage()), Optional.empty());
    }

    public static User convertToUser (UserDTO userDTO){
        User user = new User();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setCpf(userDTO.getCpf());
        user.setPhone(userDTO.getPhone());
        user.setLastModifiedDate(new Date());
        return user;
    }

    public static UserDTO convertToUserDTO (User user){
        return new UserDTO(user);
    }

    public static Date convertDate(Date date) {
        return date;
        //TODO
    }

    public boolean isJSONValid(String jsonInString) {
        try {
            return new ObjectMapper().readTree(jsonInString) != null;
        } catch (IOException e) {
            return false;
        }
    }
}
