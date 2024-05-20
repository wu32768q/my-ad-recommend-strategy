package org.example.bstest.demos.bishe.utils;

import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class TimeUtils {

    public static String getCurrentTimeWithScheme() {
        LocalDateTime localDateTime = LocalDateTime.now(Clock.systemDefaultZone());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return  localDateTime.format(formatter);
    }
}
