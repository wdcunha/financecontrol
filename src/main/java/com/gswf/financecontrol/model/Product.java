package com.gswf.financecontrol.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Data
@Entity
@Table(name = "product")
public class Product {

    @GeneratedValue(strategy = GenerationType.AUTO, generator = "product_seq")
    @GenericGenerator(name = "product_seq", strategy = "native")
    @Id
    private Long id;
    
    @Column(length = 50, nullable = false)
    private String description;
    @Column(length = 10, nullable = false)
    private String size;
    @Column(length = 6, nullable = false)
    private Double price;
    @Column(length = 8, nullable = false)
    private int quantity;
    @Column(length = 255, nullable = true)
    private String notes;

    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy = "productSale")
    @JsonIgnore
    private Set<Sales> sales;
}
