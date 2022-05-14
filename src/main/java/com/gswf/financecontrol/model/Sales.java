package com.gswf.financecontrol.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Data
@AllArgsConstructor
@NoArgsConstructor
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
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "client")
    private Person client;

    @OneToMany(cascade=CascadeType.MERGE, fetch=FetchType.EAGER, mappedBy = "saled")
    private List<Product> products = new ArrayList<>();
}
