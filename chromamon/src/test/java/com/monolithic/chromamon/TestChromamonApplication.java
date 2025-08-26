package com.monolithic.chromamon;

import org.springframework.boot.SpringApplication;

public class TestChromamonApplication {

   public static void main(String[] args) {
      SpringApplication.from(Application::main).with(TestcontainersConfiguration.class).run(args);
   }

}
