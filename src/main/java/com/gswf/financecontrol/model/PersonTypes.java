package com.gswf.financecontrol.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
@Table(name = "person_types")
public class PersonTypes implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @GeneratedValue(strategy = GenerationType.AUTO, generator = "type_seq")
    @GenericGenerator(name = "type_seq", strategy = "native")
    @Id
    private Long id;

    @Column(length = 10, nullable = false)
    private String description;

    @OneToMany(fetch=FetchType.LAZY, mappedBy = "type")
    @JsonIgnore
    private List<Person> personType;    
}
