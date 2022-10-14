package com.simbioseventures.crud.repository;

import com.simbioseventures.crud.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    List<Person> findAllByOrderByIdAsc();
}