package ru.geekbrains.chat.common;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MessageLibrary {

    /*
     * /auth|request|login|password
     * /auth|accept|nickname
     * /auth|denied
     * /broadcast|msg
     *
     * /msg_format_error|msg
     * */

    public static final String AUTH_TYPE = "auth";
    public static final String COMMON_MESSAGE_TYPE = "msg";

    public static String getAuthRequestMessage(String login, String password) {
        Map<String, String> message = new HashMap<>();
        message.put("type", AUTH_TYPE);
        message.put("login", login);
        message.put("password", password);
        return turnToJSON(message);
    }

    public static String getAuthAcceptMessage(String nickname) {
        Map<String, Object> objectJSON = new HashMap<>();
        objectJSON.put("type", AUTH_TYPE);
        objectJSON.put("success", Boolean.TRUE);
        objectJSON.put("message", String.format("Welcome back, %s%n", nickname));

        return turnToJSON(objectJSON);
    }

    public static String getAuthDeniedMessage(String message) {
        Map<String, Object> objectJSON = new HashMap<>();
        objectJSON.put("type", AUTH_TYPE);
        objectJSON.put("success", Boolean.FALSE);
        objectJSON.put("message", message);

        return turnToJSON(objectJSON);
    }

    public static String getCommonMessage(String login, String userMessage) {
        Map<String, String> objectJSON = new HashMap<>();
        objectJSON.put("type", COMMON_MESSAGE_TYPE);
        objectJSON.put("login", login);
        objectJSON.put("message", userMessage);

        return turnToJSON(objectJSON);
    }

    public static Map<String, Object> decodeMessage(String message) {
        Map<String, Object> result;
        ObjectMapper mapper = new ObjectMapper();
        try {
            result = mapper.readValue(message, new TypeReference<HashMap<String, Object>>() {});
        } catch (JsonProcessingException e) {
            result = null;
        }
        return result;
    }

    private static String turnToJSON(Object message) {
        ObjectMapper mapper = new ObjectMapper();
        String messageBody;
        try {
            messageBody = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(message);
        } catch (JsonProcessingException e) {
            messageBody = null;
        }
        return messageBody;
    }

}
