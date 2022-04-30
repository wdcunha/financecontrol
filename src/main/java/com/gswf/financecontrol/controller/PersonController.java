package com.gswf.financecontrol.controller;

import java.util.List;

import com.gswf.financecontrol.model.Person;
import com.gswf.financecontrol.repository.PersonRepo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/person")
@AllArgsConstructor
public class PersonController {
    
    private final PersonRepo personRepo;

    @RequestMapping()
    public List<Person> getAllPerson() {
        return personRepo.findAll();
    }
}
