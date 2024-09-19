package com.raqami.universe.Quiz_MicroTwo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class QuizMicroTwoApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuizMicroTwoApplication.class, args);
	}

}
