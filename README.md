# Cron Parser

## Description

This is a Spring Boot command line application that parses a cron string and expands each field to show the times at which it will run.

## Usage

1. Build the project using Maven:
    ```
    mvn clean install -Dmaven.test.skip=true
    ```

#2. Run the application with a cron expression:
    ```
    java -jar target/cron-expression.jar
    
    */15 0 1,15 * 1-5 /sys/bin/find
    ```

Modify the input accordingly, only correct cron will be parsed.
3. The output will be formatted as a table with the field name taking the first 14 columns and the times as a space-separated list following it:
    ```
    minute        0 15 30 45
    hour          0
    day of month  1 15
    month         1 2 3 4 5 6 7 8 9 10 11 12
    day of week   1 2 3 4 5
    command       /sys/bin/find
    ```

## Changes Made

- Created a Spring Boot application that takes a cron expression as a command line argument.
- Implemented `CronParserService` to parse and expand the cron expression.
- Added unit tests for `CronParserService` to ensure correct functionality.
- Created a README file with instructions on how to build and run the project.

## Unit Tests

Unit tests are located in `src/test/java/com/example/cronparser/service/CronParserServiceTests.java`. To run the tests, use the following command:
