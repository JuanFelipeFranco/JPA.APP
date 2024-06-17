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
//		List<Person> persons = (List<Person>) personRepository.findAll();
//		List<Person> persons = (List<Person>) personRepository.findByProgrammingLanguage("Java");

		List<Person> persons = (List<Person>) personRepository.findByProgrammingLanguageAndName("Java","Andres");
		persons.stream().forEach(person -> {System.out.println(person);});

/*		List <Object[]> personValues = personRepository.obtenerPersonData("Python","Pepe");
		personValues.stream().forEach(person -> {System.out.println(person[0] + " ES EXPERTO EN "+person[1]);});*/

		List <Object[]> personValues = personRepository.obtenerPersonData("Python","Pepe");
		personValues.stream().forEach(person -> {System.out.println(person[0] + " ES EXPERTO EN "+person[1]);});


	}
}
