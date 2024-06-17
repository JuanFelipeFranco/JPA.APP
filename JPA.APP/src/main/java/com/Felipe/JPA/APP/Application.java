package com.Felipe.JPA.APP;

import com.Felipe.JPA.APP.entities.Person;
import com.Felipe.JPA.APP.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private PersonRepository personRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override //va correr en consola.
	public void run(String... args) throws Exception {
//		findOne();
//		create();
		update();
	}
	@Transactional
	public void update(){
		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese id de la persona: ");
		Long id = scanner.nextLong();

		Optional<Person> optionalPerson = personRepository.findById(id);

		/*optionalPerson.ifPresent(person -> {
			System.out.println(person);
			System.out.println("Ingrese el lenguaje de programacion: ");
			String programmingLanguage = scanner.next();
			person.setProgramingLanguage(programmingLanguage);
			Person personDb = personRepository.save(person);
			System.out.println(personDb);
		});*/

		if(optionalPerson.isPresent()){
			Person person = optionalPerson.orElseThrow();
			System.out.println(person);
			System.out.println("Ingrese el lenguaje de programacion: ");
			String programmingLanguage = scanner.next();
			person.setProgramingLanguage(programmingLanguage);
			Person personDb = personRepository.save(person);
			System.out.println(personDb);
		}else {
			System.out.println("Usuario no esta presente no existe");
		}

		scanner.close();
	}
	@Transactional
	public void create(){
		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese nombre: ");
		String name = scanner.next();
		System.out.println("Ingrese apellido: ");
		String lastname = scanner.next();
		System.out.println("Ingrese Lenguaje de programacion: ");
		String programmingLanguage = scanner.next();
		scanner.close();

		Person person = new Person(null,name,lastname,programmingLanguage);

		Person personNew = personRepository.save(person);
		System.out.println(personNew);

		personRepository.findById(personNew.getId()).ifPresent(System.out::println);
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

		personRepository.findOne(1L).ifPresent(System.out::println);

		personRepository.findOneName("Pepe").ifPresent(System.out::println);

		personRepository.findOneLikeName("ri").ifPresent(System.out::println);

		personRepository.findByNameContaining("se").ifPresent(System.out::println);


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
