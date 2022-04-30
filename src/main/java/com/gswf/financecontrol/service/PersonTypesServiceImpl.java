package com.gswf.financecontrol.service;

import java.util.List;

import javax.annotation.Resource;

import com.gswf.financecontrol.model.PersonTypes;
import com.gswf.financecontrol.repository.PersonTypesRepo;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PersonTypesServiceImpl implements PersonTypesService {
      
    @Resource
    private PersonTypesRepo personTypeRepo;
  
    @Override
    public List<PersonTypes> getAllPersonTypes() {
        return personTypeRepo.findAll();        
    }
  
    @Override
    public List<PersonTypes> saveAllTypes(List<PersonTypes> personTypes) {
        return personTypeRepo.saveAll(personTypes);        
    }
  
    @Override
    public void deleteAllPersonTypes() {
        personTypeRepo.deleteAll();        
    }
}
