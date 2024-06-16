package com.Felipe.JPA.APP.repositories;

import com.Felipe.JPA.APP.entities.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {

}
