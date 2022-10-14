package com.simbioseventures.crud.controller;

import com.simbioseventures.crud.CrudApplicationTests;
import com.simbioseventures.crud.entity.Person;
import com.simbioseventures.crud.service.PersonService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public class PersonControllerTest extends CrudApplicationTests {
    private MockMvc mockMvc;

    @InjectMocks
    private PersonController personController;

    @Mock
    private PersonService personService;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(personController).build();
    }

    @AfterEach
    public void resetMocking() {
        Mockito.reset(personService);
    }

    LocalDate date = LocalDate.now();

    @Test
    public void shouldReturn201WhenRegisterNewPerson() throws Exception {
        Person person = new Person(1, "John Doe", "john@doe.com", date);

        Mockito.when(personService.insertPerson(person)).thenReturn(new ResponseEntity<Person>(person, HttpStatus.CREATED).getBody()); // colocar essa linha no teste de serviço

        ResultActions response = this.mockMvc.perform(MockMvcRequestBuilders.post("/pessoa")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(super.mapToJson(person)));

        response.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(person.getName()))
                .andExpect(jsonPath("$.email").value(person.getEmail()))
                .andExpect(jsonPath("$.birthDate").value(String.valueOf(date)));
    }

    @Test
    public void shouldReturn400WhenRegisterNewPerson() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/pessoa"))
                .andExpect(status().isBadRequest());
    }


    @Test
    public void shouldReturn200WhenGetPersonById() throws Exception {
        Person person = new Person(1, "John Doe", "john@doe.com", date);

        Mockito.when(personService.getPerson(1)).thenReturn(person);
        Mockito.when(personService.getResponseEntityStatusCode200(person)).thenReturn(new ResponseEntity<Person>(person, HttpStatus.OK));

        ResultActions response = this.mockMvc.perform(MockMvcRequestBuilders.get("/pessoa/{id}", 1));

        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(person.getName()))
                .andExpect(jsonPath("$.email").value(person.getEmail()))
                .andExpect(jsonPath("$.birthDate").value(String.valueOf(person.getBirthDate())));
    }

    @Test
    public void shouldReturn404WhenGetPersonById() throws Exception {
        Mockito.when(personService.getPerson(1)).thenReturn(null);
        Mockito.when(personService.getResponseEntityStatusCode200(null)).thenReturn(new ResponseEntity<Person>(HttpStatus.NOT_FOUND));

        ResultActions response = this.mockMvc.perform(MockMvcRequestBuilders.get("/pessoa/{id}", 1));

        response.andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldReturn200WhenUpdatePersonById() throws Exception {
        Person person = new Person(1, "Not John Doe", "notjohn@doe.com", date);

        Mockito.when(personService.updatePerson(1, person)).thenReturn(person);
        Mockito.when(personService.getResponseEntityStatusCode200(person)).thenReturn(new ResponseEntity<Person>(person, HttpStatus.OK));

        ResultActions response = this.mockMvc.perform(MockMvcRequestBuilders.put("/pessoa/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(super.mapToJson(person)));

        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(person.getName()))
                .andExpect(jsonPath("$.email").value(person.getEmail()))
                .andExpect(jsonPath("$.birthDate").value(String.valueOf(person.getBirthDate())));
    }

    @Test
    public void shouldReturn404WhenUpdatePersonById() throws Exception {
        Person person = new Person(1, "Not John Doe", "notjohn@doe.com", date);

        Mockito.when(personService.updatePerson(1, person)).thenReturn(null);
        Mockito.when(personService.getResponseEntityStatusCode200(null)).thenReturn(new ResponseEntity<>(HttpStatus.NOT_FOUND));

        ResultActions response = this.mockMvc.perform(MockMvcRequestBuilders.put("/pessoa/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(super.mapToJson(person)));

        response.andDo(print())
                .andExpect(status().isNotFound());
    }


    @Test
    public void shouldReturn200WhenDeletePersonById() throws Exception {
        Person person = new Person(1, "John Doe", "john@doe.com", date);

        Mockito.when(personService.deletePerson(1)).thenReturn(person);
        Mockito.when(personService.getResponseEntityStatusCode200(person)).thenReturn(new ResponseEntity<Person>(person, HttpStatus.OK));

        ResultActions response = this.mockMvc.perform(MockMvcRequestBuilders.delete("/pessoa/{id}", 1));

        response.andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturn404WhenDeletePersonById() throws Exception {
        Mockito.when(personService.deletePerson(1)).thenReturn(null);
        Mockito.when(personService.getResponseEntityStatusCode200(null)).thenReturn(new ResponseEntity<Person>(HttpStatus.NOT_FOUND));

        ResultActions response = this.mockMvc.perform(MockMvcRequestBuilders.delete("/pessoa/{id}", 1));

        response.andDo(print())
                .andExpect(status().isNotFound());
    }


    @Test
    public void shouldReturn200WhenGetPeople() throws Exception {
        Person person = new Person(1, "John Doe", "john@doe.com", date);
        Person person2 = new Person(1, "Not John Doe", "notjohn@doe.com", date);

        List<Person> listOfPeople = new ArrayList<>();
        listOfPeople.add(person);
        listOfPeople.add(person2);

        Mockito.when(personService.getPeople()).thenReturn(listOfPeople);

        ResultActions response = this.mockMvc.perform(MockMvcRequestBuilders.get("/pessoas"));

        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(listOfPeople.size()));
    }

}