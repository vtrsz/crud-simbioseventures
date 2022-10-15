package com.simbioseventures.crud.service;

import com.simbioseventures.crud.CrudApplicationTests;
import com.simbioseventures.crud.entity.Person;
import com.simbioseventures.crud.repository.PersonRepository;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@AutoConfigureMockMvc
public class PersonServiceTest extends CrudApplicationTests {
    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;

    @AfterEach
    void resetMocking() {
        Mockito.reset(personRepository);
    }

    LocalDate date = LocalDate.now();

    @Test
    public void insertPerson() {
        Person person = new Person(1, "John Doe", "john@doe.com", date);

        Mockito.when(personRepository.save(person)).thenReturn(person);

        personService.insertPerson(person);

        verify(personRepository, times(1)).save(person);
    }

    @Test
    public void getPerson() {
        Person person = new Person(1, "John Doe", "john@doe.com", date);

        Mockito.when(personRepository.findById(person.getId())).thenReturn(Optional.of(person));

        personService.getPerson(person.getId());

        verify(personRepository, times(1)).findById(person.getId());
    }

    @Test
    public void updatePerson() {
        Person person = new Person(1, "Not John Doe", "notjohn@doe.com", date);

        Mockito.when(personRepository.findById(person.getId())).thenReturn(Optional.of(person));
        Mockito.when(personRepository.save(person)).thenReturn(person);

        personService.updatePerson(person.getId(), person);

        verify(personRepository, times(1)).findById(person.getId());
        verify(personRepository, times(1)).save(person);
    }

    @Test
    public void deletePerson() {
        Person person = new Person(1, "John Doe", "john@doe.com", date);

        Mockito.when(personRepository.findById(person.getId())).thenReturn(Optional.of(person));

        personService.deletePerson(person.getId());

        verify(personRepository, times(1)).deleteById(person.getId());
    }

    @Test
    public void getPeople() {
        Person person = new Person(1, "John Doe", "john@doe.com", date);
        Person person2 = new Person(1, "Not John Doe", "notjohn@doe.com", date);

        List<Person> listOfPeople = new ArrayList<>();
        listOfPeople.add(person);
        listOfPeople.add(person2);

        Mockito.when(personRepository.findAllByOrderByIdAsc()).thenReturn(listOfPeople);

        personService.getPeople();

        verify(personRepository, times(1)).findAllByOrderByIdAsc();
    }

    @Test
    public void getResponseEntityStatusCode200() {
        Person person = new Person(1, "John Doe", "john@doe.com", date);

        ResponseEntity<Person> response = personService.getResponseEntityStatusCode200(person);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(person, response.getBody());
    }
}
