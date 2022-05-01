package com.gswf.financecontrol.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

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
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "purchases")
public class Purchases {

    @GeneratedValue(strategy = GenerationType.AUTO, generator = "purchases_seq")
    @GenericGenerator(name = "purchases_seq", strategy = "native")
    @Id
    private Long id;

    @Column(length = 6, nullable = false)
    private Double totalPrice;
    @JsonFormat(pattern = "dd/MM/yyyy")
    @JsonSerialize(using = LocalDateSerializer.class)
    @Column(length = 8, nullable = false)
    private LocalDate occurenceDate;
    @Column(length = 255, nullable = true)
    private String notes;

    @OneToMany(mappedBy = "pk.purchase")
    @JsonIgnore
    private List<PurchaseProduct> purchaseProducts = new ArrayList<>();

    @OneToMany(fetch=FetchType.LAZY, mappedBy = "purchase")
    @JsonIgnore
    private Set<PurchasePayment> purchasePayments = new LinkedHashSet<>();

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "store")
    @JsonManagedReference
    private Person store;
}
