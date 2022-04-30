package com.gswf.financecontrol.service;

import java.util.List;

import com.gswf.financecontrol.model.PersonTypes;

import org.springframework.validation.annotation.Validated;

@Validated
public interface PersonTypesService {
      
    List<PersonTypes> getAllPersonTypes();

    List<PersonTypes> saveAllTypes(List<PersonTypes> personTypes);

    void deleteAllPersonTypes();
}
