package com.chromamon.result;

import org.springframework.boot.SpringApplication;

public class TestResultApplication {

	public static void main(String[] args) {
		SpringApplication.from(ResultApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
