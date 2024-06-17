package com.Felipe.JPA.APP;

import com.Felipe.JPA.APP.entities.Person;
import com.Felipe.JPA.APP.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private PersonRepository personRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override //va correr en consola.
	public void run(String... args) throws Exception {
		findOne();
	}
	public void findOne(){
		//primer manera
//		Person person = personRepository.findById(2L).orElseThrow();
//		System.out.println(person);

		//2manera
//		Person person = null;
//		Optional<Person> optionalPerson = personRepository.findById(8L);
//		if(optionalPerson.isPresent()){
//			person = optionalPerson.get();
//		}
//		System.out.println(person);

		//3manera
		personRepository.findById(8L).ifPresent(person -> System.out.println(person));

	}
	public void list(){
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
