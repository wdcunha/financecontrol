package com.gswf.financecontrol.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
@Table(name = "sales")
public class Sales implements Serializable {

    private static final long serialVersionUID = 1L;

    @GeneratedValue(strategy = GenerationType.AUTO, generator = "sales-seq")
    @GenericGenerator(name = "sales-seq", strategy = "native")
    @Id
    private long id;

    @Column(length = 8, nullable = false)
    private int quantity;
    @Column(length = 6, nullable = false)
    private Double totalPrice;
    @JsonFormat(pattern = "dd/MM/yyyy")
    @JsonSerialize(using = LocalDateSerializer.class)
    @Column(length = 8, nullable = false)
    private LocalDate saleDate;
    @Column(length = 255, nullable = true)
    private String notes;
    
    @ManyToOne
    private Person client;
    @ManyToOne
    private Product productSale;
}
