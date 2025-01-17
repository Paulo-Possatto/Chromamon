package com.chromamon.transformer;

import org.springframework.boot.SpringApplication;

public class TestTransformerApplication {

	public static void main(String[] args) {
		SpringApplication.from(TransformerApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
