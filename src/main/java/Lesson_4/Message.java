package Lesson_4;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class Message {
    private String login;
    private Instant instant;
    private String message;

    public Message(String login, String message) {
        this.login = login;
        this.instant = Instant.now();
        this.message = message;
    }

    @Override
    public String toString() {
        LocalDateTime localDT = LocalDateTime.ofInstant(instant, ZoneOffset.ofHours(3));
        LocalDateTime localDTNow = LocalDateTime.ofInstant(Instant.now(), ZoneOffset.ofHours(3));
        String formatString;
        if (localDT.getDayOfYear() == localDTNow.getDayOfYear()) formatString = "HH:mm:ss";
        else formatString = "dd.MM.yyyy HH:mm:ss";
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern(formatString);
        return localDT.format(fmt) + ": " + login + ": " + message;
    }
}
