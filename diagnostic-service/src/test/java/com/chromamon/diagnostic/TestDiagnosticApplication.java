package com.chromamon.diagnostic;

import org.springframework.boot.SpringApplication;

public class TestDiagnosticApplication {

	public static void main(String[] args) {
		SpringApplication.from(DiagnosticApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
