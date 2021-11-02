package com.skogul.spring.streamrabbit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Consumer;

@SpringBootApplication
public class StreamRabbitApplication {

	public static void main(String[] args) {
		SpringApplication.run(StreamRabbitApplication.class, args);
	}

	@Bean
	public Consumer<Person> log() {
		return person -> {
			System.out.println("Received: " + person);
		};
	}

	public static class Person {
		private String name;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String toString() {
			return this.name;
		}
	}

}
