package com.simbioseventures.crud.controller;

import com.simbioseventures.crud.entity.Person;
import com.simbioseventures.crud.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class PersonController {
    @Autowired
    private PersonService personService;

    @PostMapping("/pessoa") // Obrigatório que o endpoint seja /pessoa
    public ResponseEntity<Person> registerNewPerson(@RequestBody Person person) {
        Person savedPerson = personService.insertPerson(person);
        return new ResponseEntity<Person>(savedPerson, HttpStatus.CREATED);
    }

    @GetMapping("/pessoa/{id}") // Obrigatório que o endpoint seja /pessoa/{id}
    public ResponseEntity<Person> getPersonById(@PathVariable final Integer id) {
        Person searchedPerson = personService.getPerson(id);
        return personService.getResponseEntityStatusCode200(searchedPerson);
    }

    @PutMapping("/pessoa/{id}") // Obrigatório que o endpoint seja /pessoa/{id}
    public ResponseEntity<Person> updatePersonById(@PathVariable final Integer id, @RequestBody Person person) {
        Person updatedPerson = personService.updatePerson(id, person);
        return personService.getResponseEntityStatusCode200(updatedPerson);
    }

    @DeleteMapping("/pessoa/{id}") // Obrigatório que o endpoint seja /pessoa/{id}
    public ResponseEntity<Person> deletePersonById(@PathVariable final Integer id) {
        Person deletedPerson = personService.deletePerson(id);
        return personService.getResponseEntityStatusCode200(deletedPerson);
    }

    @GetMapping("/pessoas") // Obrigatório que o endpoint seja /pessoas
    public ResponseEntity<List<Person>> getAllPeople() {
        List<Person> people = personService.getPeople();
        return new ResponseEntity<List<Person>>(people, HttpStatus.OK);
    }
}