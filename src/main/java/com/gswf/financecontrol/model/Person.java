package com.gswf.financecontrol.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Data
@Entity
@Table(name = "person")
public class Person {

    @GeneratedValue(strategy = GenerationType.AUTO, generator = "person-seq")
    @GenericGenerator(name = "person-seq", strategy = "native")
    @Id
    private long id;
    
    @Column(length = 20, nullable = true, name = "num_document")
    private String numDoc;
    @Column(length = 40, nullable = false)
    private String name;
    @Column(length = 100, nullable = true)
    private String address;
    @Column(length = 20, nullable = true)
    private String phone;
    @Column(length = 50, nullable = true)
    private String email;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "type")
    @JsonManagedReference
    private PersonTypes type;
    
    @OneToMany(mappedBy = "store")
    @JsonIgnore
    private List<Purchases> purchases;

    @OneToMany(mappedBy = "client")
    @JsonIgnore
    private List<Sales> sales;
}
