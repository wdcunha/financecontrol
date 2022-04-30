package com.gswf.financecontrol.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
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
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "purchases")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="purchaseProducts")
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
    @JoinColumn(name = "store_id")
    private Person store;

    public Purchases() {
    }

    public Purchases(Long id, Double totalPrice, LocalDate occurenceDate, String notes, List<PurchaseProduct> purchaseProducts, Set<PurchasePayment> purchasePayments, Person store) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.occurenceDate = occurenceDate;
        this.notes = notes;
        this.purchaseProducts = purchaseProducts;
        this.purchasePayments = purchasePayments;
        this.store = store;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTotalPrice() {
        return this.totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDate getOccurenceDate() {
        return this.occurenceDate;
    }

    public void setOccurenceDate(LocalDate occurenceDate) {
        this.occurenceDate = occurenceDate;
    }

    public String getNotes() {
        return this.notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<PurchaseProduct> getPurchaseProducts() {
        return this.purchaseProducts;
    }

    public void setPurchaseProducts(List<PurchaseProduct> purchaseProducts) {
        this.purchaseProducts = purchaseProducts;
    }

    public Set<PurchasePayment> getPurchasePayments() {
        return this.purchasePayments;
    }

    public void setPurchasePayments(Set<PurchasePayment> purchasePayments) {
        this.purchasePayments = purchasePayments;
    }

    public Person getStore() {
        return this.store;
    }

    public void setStore(Person store) {
        this.store = store;
    }

    public Purchases id(Long id) {
        setId(id);
        return this;
    }

    public Purchases totalPrice(Double totalPrice) {
        setTotalPrice(totalPrice);
        return this;
    }

    public Purchases occurenceDate(LocalDate occurenceDate) {
        setOccurenceDate(occurenceDate);
        return this;
    }

    public Purchases notes(String notes) {
        setNotes(notes);
        return this;
    }

    public Purchases purchaseProducts(List<PurchaseProduct> purchaseProducts) {
        setPurchaseProducts(purchaseProducts);
        return this;
    }

    public Purchases purchasePayments(Set<PurchasePayment> purchasePayments) {
        setPurchasePayments(purchasePayments);
        return this;
    }

    public Purchases store(Person store) {
        setStore(store);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Purchases)) {
            return false;
        }
        Purchases purchases = (Purchases) o;
        return Objects.equals(id, purchases.id) && Objects.equals(totalPrice, purchases.totalPrice) && Objects.equals(occurenceDate, purchases.occurenceDate) && Objects.equals(notes, purchases.notes) && Objects.equals(purchaseProducts, purchases.purchaseProducts) && Objects.equals(purchasePayments, purchases.purchasePayments) && Objects.equals(store, purchases.store);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, totalPrice, occurenceDate, notes, purchaseProducts, purchasePayments, store);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", totalPrice='" + getTotalPrice() + "'" +
            ", occurenceDate='" + getOccurenceDate() + "'" +
            ", notes='" + getNotes() + "'" +
            ", purchaseProducts='" + getPurchaseProducts() + "'" +
            ", purchasePayments='" + getPurchasePayments() + "'" +
            ", store='" + getStore() + "'" +
            "}";
    }
}
