package com.Felipe.JPA.APP;

import com.Felipe.JPA.APP.dto.PersonDto;
import com.Felipe.JPA.APP.entities.Person;
import com.Felipe.JPA.APP.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.Arrays;
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
//		delete2();
//		subQueries();
	}

	@Transactional(readOnly = true)
	public void whereIn() {
		System.out.println("================== consulta where in ==================");
		List<Person> persons = personRepository.getPersonsByIds(Arrays.asList(1L, 2L, 5L, 7L));
		persons.forEach(System.out::println);
	}

	@Transactional(readOnly = true)
	public void subQueries() {
		System.out.println("================== consulta por el nombre mas corto y su largo ==================");
		List<Object[]> registers = personRepository.getShorterName();
		registers.forEach(reg -> {
			String name = (String) reg[0];
			Integer length = (Integer) reg[1];
			System.out.println("name=" + name + ", length=" + length);
		});

		System.out.println("================== consulta pra obtener el ultimo registro de persona ==================");
		Optional<Person> optionalPerson = personRepository.getLastRegistration();
		optionalPerson.ifPresent(System.out::println);
	}

	@Transactional(readOnly = true)
	public void queriesFunctionAggregation() {


		System.out.println("================== consulta con el total de registros de la tabla persona ==================");
		Long count = personRepository.getTotalPerson();
		System.out.println(count);

		System.out.println("================== consulta con el valor minimo del id ==================");
		Long min = personRepository.getMinId();
		System.out.println(min);

		System.out.println("================== consulta con el valor maximo del id");
		Long max = personRepository.getMaxId();
		System.out.println(max);

		System.out.println("================== consulta con el nombre y su largo ==================");
		List<Object[]> regs = personRepository.getPersonNameLength();
		regs.forEach(reg -> {
			String name = (String) reg[0];
			Integer length = (Integer) reg[1];
			System.out.println("name=" + name + ", length=" + length);
		});

		System.out.println("================== consulta con el nombre mas corto ==================");
		Integer minLengthName = personRepository.getMinLengthName();
		System.out.println(minLengthName);

		System.out.println("================== consulta con el nombre mas largo ==================");
		Integer maxLengthName = personRepository.getMaxLengthName();
		System.out.println(maxLengthName);

		System.out.println("================== consultas resumen de funciones de agregacion min, max, sum, avg, count ==================");
		Object[] resumeReg = (Object[]) personRepository.getResumeAggregationFunction();
		System.out.println(
				"min=" + resumeReg[0] +
						", max=" + resumeReg[1] +
						", sum=" + resumeReg[2] +
						", avg=" + resumeReg[3] +
						", count=" + resumeReg[4]);
	}

	@Transactional(readOnly=true)
	public void personalizedQueriesBetween() {
		System.out.println("================== consultas por rangos ==================");
		List<Person> persons = personRepository.findByIdBetweenOrderByNameAsc(2L, 5L);
		persons.forEach(System.out::println);

		persons = personRepository.findByNameBetweenOrderByNameDescLastnameDesc("J", "Q");
		persons.forEach(System.out::println);

		persons = personRepository.findAllByOrderByNameAscLastnameDesc();
		persons.forEach(System.out::println);

	}
	@Transactional(readOnly = true)
	public void personalizedQueriesConcatUpperAndLowerCase() {
		System.out.println("================== consulta nombres y apellidos de personas ==================");
		List<String> names = personRepository.findAllFullNameConcat();
		names.forEach(System.out::println);

		System.out.println("================== consulta nombres y apellidos mayuscula ==================");
		names = personRepository.findAllFullNameConcatUpper();
		names.forEach(System.out::println);

		System.out.println("================== consulta nombres y apellidos minuscula ==================");
		names = personRepository.findAllFullNameConcatLower();
		names.forEach(System.out::println);
		System.out.println("================== consulta personalizada persona upper y lower case ==================");
		List<Object[]> regs = personRepository.findAllPersonDataListCase();
		regs.forEach(reg -> System.out.println("id="+ reg[0] + ", nombre=" + reg[1] + ", apellido=" + reg[2]+ ", lenguaje="+reg[3]));

	}

	@Transactional(readOnly = true)
	public void personalizedQueriesDistinct() {
		System.out.println("================== consultas con nombres de personas ==================");
		List<String> names = personRepository.findAllNames();
		names.forEach(System.out::println);

		System.out.println("==================  consultas con nombres unicos de personas ==================");
		names = personRepository.findAllNamesDistinct();
		names.forEach(System.out::println);

		System.out.println("================== consulta con lenguaje de programacion unicas ==================");
		List<String> languages = personRepository.findAllProgrammingLanguageDistinct();
		languages.forEach(System.out::println);

		System.out.println("================== consulta con total de lenguajes de programacion unicas ==================");
		Long totalLanguage = personRepository.findAllProgrammingLanguageDistinctCount();
		System.out.println("total de lenguajes de programacion: " + totalLanguage);

	}
	@Transactional(readOnly = true)
	public void personalizeQueries2() {
		System.out.println("================== consulta por objeto persona y lenguaje de programacion ==================");
		List<Object[]> personsRegs = personRepository.findAllMixPerson();

		personsRegs.forEach(reg -> {
			System.out.println("programmingLanguage=" + reg[1] + ", person=" + reg[0]);
		});

		System.out.println("consulta que puebla y devuelve objeto entity de una instancia personalizada");
		List<Person> persons = personRepository.findAllObjectPersonPersonalized();
		persons.forEach(System.out::println);

		System.out.println("consulta que puebla y devuelve objeto dto de una clase personalizada");
		List<PersonDto> personsDto = personRepository.findAllPersonDto();
		personsDto.forEach(System.out::println);
	}
	@Transactional(readOnly = true)
	public void personalizedQueries() {

		Scanner scanner = new Scanner(System.in);

		System.out.println("================== consulta solo el nombre por el id ==================");
		System.out.println("Ingrese el id:");
		Long id = scanner.nextLong();
		scanner.close();

		System.out.println("===== mostrando solo el nombre =====");
		String name = personRepository.getNameById(id);
		System.out.println(name);

		System.out.println("===== mostrando solo el id =====");
		Long idDb = personRepository.getIdById(id);
		System.out.println(idDb);

		System.out.println("===== mostrando nombre completo con concat =====");
		String fullName = personRepository.getFullNameById(id);
		System.out.println(fullName);

		System.out.println("===== consulta por campos personalizados por el id =====");
		Optional<Object> optionalReg = personRepository.obtenerPersonDataById(id);
		if (optionalReg.isPresent()) {
			Object[] personReg = (Object[]) optionalReg.orElseThrow();
			System.out.println("id="+ personReg[0] + ", nombre=" + personReg[1] + ", apellido=" + personReg[2]+ ", lenguaje="+personReg[3]);
		}

		System.out.println("===== consulta por campos personalizados lista ======");
		List<Object[]> regs = personRepository.obtenerPersonDataList();
		regs.forEach(reg -> System.out.println("id="+ reg[0] + ", nombre=" + reg[1] + ", apellido=" + reg[2]+ ", lenguaje="+reg[3]));
	}
	@Transactional
	public void delete2() {
		personRepository.findAll().forEach(System.out::println);

		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese id de la persona: ");
		Long id = scanner.nextLong();

		Optional<Person> optionalPerson = personRepository.findById(id);

		optionalPerson.ifPresentOrElse(person -> personRepository.delete(person),()-> System.out.println("Lo sentimos no existe la persona con ese id"));

		personRepository.findAll().forEach(System.out::println);

		scanner.close();
	}
	@Transactional
	public void delete() {
		personRepository.findAll().forEach(System.out::println);

		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese id de la persona: ");
		Long id = scanner.nextLong();
		personRepository.deleteById(id);

		personRepository.findAll().forEach(System.out::println);

		scanner.close();
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
