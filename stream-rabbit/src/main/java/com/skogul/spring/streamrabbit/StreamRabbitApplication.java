package com.skogul.spring.streamrabbit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Function;

@SpringBootApplication
public class StreamRabbitApplication {

	private static final Logger log = LoggerFactory.getLogger(StreamRabbitApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(StreamRabbitApplication.class, args);
	}

	@Bean
	public Function<Person, Person> processor() {
		return person -> {
			log.info("We get message");
			if (person.getName() == null) {
				throw new IllegalArgumentException("name cannot be null");
			}
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
