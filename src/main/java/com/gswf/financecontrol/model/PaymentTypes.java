package com.gswf.financecontrol.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "payment_types")
public class PaymentTypes implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @GeneratedValue(strategy = GenerationType.AUTO, generator = "pay_type_seq")
    @GenericGenerator(name = "pay_type_seq", strategy = "native")
    @Id
    private Long id;

    @Column(length = 10, nullable = false)
    private String description;
        
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "purchased")
    @JsonIgnore()
    private Purchases purchased;
}
