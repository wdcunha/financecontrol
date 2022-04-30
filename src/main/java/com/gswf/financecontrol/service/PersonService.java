package com.gswf.financecontrol.service;

import java.util.List;

import com.gswf.financecontrol.model.Person;

import org.springframework.validation.annotation.Validated;

@Validated
public interface PersonService {
      
    List<Person> getAllPeople();

    List<Person> saveAllPeople(List<Person> people);

    void deleteAllPeople();
}
