package com.example.cron.expression.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class CronParserService {

    public String parse(String cronExpression) {
        String[] parts = cronExpression.split("\\s+");
        if (parts.length != 6) {
            throw new IllegalArgumentException("Invalid cron expression format");
        }

        String minute = expandField(parts[0], 0, 59);
        String hour = expandField(parts[1], 0, 23);
        String dayOfMonth = expandField(parts[2], 1, 31);
        String month = expandField(parts[3], 1, 12);
        String dayOfWeek = expandField(parts[4], 0, 6);
        String command = parts[5];

        return String.format(
                "minute        %s\n" +
                "hour          %s\n" +
                "day of month  %s\n" +
                "month         %s\n" +
                "day of week   %s\n" +
                "command       %s",
                minute, hour, dayOfMonth, month, dayOfWeek, command);
    }

    private String expandField(String field, int min, int max) {
        List<Integer> values = new ArrayList<>();

        if (field.equals("*")) {
            values = IntStream.rangeClosed(min, max).boxed().collect(Collectors.toList());
        } else if (field.contains("/")) {
            String[] split = field.split("/");
            int start = split[0].equals("*") ? min : Integer.parseInt(split[0]);
            int step = Integer.parseInt(split[1]);
            for (int i = start; i <= max; i += step) {
                values.add(i);
            }
        } else if (field.contains("-")) {
            String[] split = field.split("-");
            int start = Integer.parseInt(split[0]);
            int end = Integer.parseInt(split[1]);
            values = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
        } else if (field.contains(",")) {
            values = Arrays.stream(field.split(",")).map(Integer::parseInt).collect(Collectors.toList());
        } else {
            values.add(Integer.parseInt(field));
        }

        return values.stream().map(String::valueOf).collect(Collectors.joining(" "));
    }
}
