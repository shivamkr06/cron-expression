package com.example.cron.expression;


import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.cron.expression.service.CronParserService;

@SpringBootApplication
public class CronExpressrionApplication implements CommandLineRunner {
	
	@Autowired
    private CronParserService cronParserService;

	public static void main(String[] args) {
		SpringApplication.run(CronExpressrionApplication.class, args);
	}
	
	@Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter cron expression (or type 'exit' to quit): ");
            String cronExpression = scanner.nextLine();
            if ("exit".equalsIgnoreCase(cronExpression)) {
                break;
            }
            try {
                String result = cronParserService.parse(cronExpression);
                System.out.println(result);
            } catch (IllegalArgumentException e) {
                System.err.println("Invalid cron expression: " + e.getMessage());
            }
        }
        scanner.close();
        System.out.print("System exit successful !!");
        System.exit(1);
    }

	
}
