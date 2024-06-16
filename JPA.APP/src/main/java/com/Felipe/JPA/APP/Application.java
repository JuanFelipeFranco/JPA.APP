package com.Felipe.JPA.APP;

import com.Felipe.JPA.APP.entities.Person;
import com.Felipe.JPA.APP.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private PersonRepository personRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override //va correr en consola.
	public void run(String... args) throws Exception {
		List<Person> persons = (List<Person>) personRepository.findAll();
		persons.stream().forEach(person -> System.out.println(person));
	}
}
