package com.example.cron.expression.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CronParserServiceTests {

    private final CronParserService cronParserService = new CronParserService();

    @Test
    void testParse() {
        String cronExpression = "*/15 0 1,15 * 1-5 /usr/bin/find";
        String expected = 
                "minute        0 15 30 45\n" +
                "hour          0\n" +
                "day of month  1 15\n" +
                "month         1 2 3 4 5 6 7 8 9 10 11 12\n" +
                "day of week   1 2 3 4 5\n" +
                "command       /usr/bin/find";
        
        String result = cronParserService.parse(cronExpression);
        assertEquals(expected, result);
    }

    @Test
    void testInvalidCronExpression() {
        String invalidCronExpression = "*/15 0 1,15 * 1-5";
        assertThrows(IllegalArgumentException.class, () -> cronParserService.parse(invalidCronExpression));
    }
}
