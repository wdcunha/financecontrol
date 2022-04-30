package com.gswf.financecontrol.service;

import java.util.List;

import javax.annotation.Resource;

import com.gswf.financecontrol.model.Person;
import com.gswf.financecontrol.repository.PersonRepo;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PersonServiceImpl implements PersonService {
      
    @Resource
    private PersonRepo personRepo;
  
    @Override
    public List<Person> getAllPeople() {
        return personRepo.findAll();        
    }
  
    @Override
    public List<Person> saveAllPeople(List<Person> people) {
        return personRepo.saveAll(people);        
    }
  
    @Override
    public void deleteAllPeople() {
        personRepo.deleteAll();        
    }
}
