package com.skogul.spring.streamrabbit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Function;

@SpringBootApplication
public class StreamRabbitApplication {

	public static void main(String[] args) {
		SpringApplication.run(StreamRabbitApplication.class, args);
	}

	@Bean
	public Function<Person, Person> processor() {
		return person -> {
			System.out.println("Received: " + person);
			person.setProcessed(true);
			return person;
		};
	}

	public static class Person {
		private String name;
		private Boolean processed;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Boolean getProcessed() {
			return processed;
		}

		public void setProcessed(Boolean processed) {
			this.processed = processed;
		}

		public String toString() {
			return this.name;
		}
	}

}
