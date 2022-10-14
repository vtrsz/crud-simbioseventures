package com.simbioseventures.crud.service;


import com.simbioseventures.crud.entity.Person;
import com.simbioseventures.crud.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    public Person insertPerson(Person person) {
        personRepository.save(person);
        return person;
    }

    public Person getPerson(Integer id) {
        Optional<Person> searchedPerson = personRepository.findById(id);
        if (searchedPerson.isEmpty()) {
            return null;
        }
        return searchedPerson.get();
    }

    public Person updatePerson(Integer id, Person person) {
        Person searchedPerson = getPerson(id);
        if (searchedPerson == null){
            return null;
        }
        searchedPerson.setName(person.getName());
        searchedPerson.setEmail(person.getEmail());
        searchedPerson.setBirthDate(person.getBirthDate());
        personRepository.save(searchedPerson);
        return searchedPerson;
    }

    public Person deletePerson(Integer id) {
        Person searchedPerson = getPerson(id);
        if (searchedPerson == null){
            return null;
        }

        personRepository.deleteById(id);
        return searchedPerson;
    }

    public List<Person> getPeople() {
        return personRepository.findAllByOrderByIdAsc();
    }

    public ResponseEntity<Person> getResponseEntityStatusCode200(Person person) {
        if (person == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Person>(person, HttpStatus.OK);
    }
}
