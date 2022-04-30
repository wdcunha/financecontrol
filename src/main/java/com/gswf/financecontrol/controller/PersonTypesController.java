package com.gswf.financecontrol.controller;

import java.util.List;

import com.gswf.financecontrol.model.PersonTypes;
import com.gswf.financecontrol.repository.PersonTypesRepo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;


@RestController
@RequestMapping("/api/persontypes")
@AllArgsConstructor
public class PersonTypesController {
    
    private final PersonTypesRepo personTypesRepo;

    @GetMapping()
    public List<PersonTypes> getAllPersonTypes() {
        return personTypesRepo.findAll();
    }
    
}
