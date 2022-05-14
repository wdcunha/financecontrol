package com.gswf.financecontrol.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import org.hibernate.annotations.GenericGenerator;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "purchases")
public class Purchases implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "product_seq")
    @GenericGenerator(name = "purchases_seq", strategy = "native")
    private Long id;

    @Column(length = 6, nullable = false)
    private Double totalPrice;
    @JsonFormat(pattern = "dd/MM/yyyy")
    @JsonSerialize(using = LocalDateSerializer.class)
    @Column(length = 8, nullable = false)
    private LocalDate occurenceDate;
    @Column(length = 255, nullable = true)
    private String notes;

    @OneToMany(cascade=CascadeType.MERGE, mappedBy = "pk.purchase")
    private List<PurchaseProduct> purchaseProducts = new ArrayList<>();

    @OneToMany(cascade=CascadeType.MERGE, targetEntity=PaymentTypes.class, fetch=FetchType.EAGER, mappedBy = "purchased")
    private List<PaymentTypes> paymentsx = new ArrayList<>();

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "store")
    private Person store;

    public Purchases() {
    }

    public Purchases(Double totalPrice, LocalDate occurenceDate, String notes, List<PurchaseProduct> purchaseProducts, List<PaymentTypes> payment, Person store) {
        this.totalPrice = totalPrice;
        this.occurenceDate = occurenceDate;
        this.notes = notes;
        this.purchaseProducts = purchaseProducts;
        this.paymentsx = payment;
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

    @JsonManagedReference
    public List<PurchaseProduct> getPurchaseProducts() {
        return this.purchaseProducts;
    }

    public void setPurchaseProducts(List<PurchaseProduct> purchaseProducts) {
        this.purchaseProducts = purchaseProducts;
    }

    public List<PaymentTypes> getPaymentsx() {
        return this.paymentsx;
    }

    public void setPaymentsx(List<PaymentTypes> paymentsx) {
        this.paymentsx = paymentsx;
    }

    public Person getStore() {
        return this.store;
    }

    public void setStore(Person store) {
        this.store = store;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Purchases)) {
            return false;
        }
        Purchases purchases = (Purchases) o;
        return Objects.equals(id, purchases.id) 
        && Objects.equals(totalPrice, purchases.totalPrice) 
        && Objects.equals(occurenceDate, purchases.occurenceDate) 
        && Objects.equals(notes, purchases.notes) 
        && Objects.equals(purchaseProducts, purchases.purchaseProducts) 
        && Objects.equals(paymentsx, purchases.paymentsx) 
        && Objects.equals(store, purchases.store);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, totalPrice, occurenceDate, notes, purchaseProducts, paymentsx, store);
    }

    @Override
    public String toString() {
        return "{" +
            " purchaseId='" + getId() + "'" +
            ", totalPrice='" + getTotalPrice() + "'" +
            ", occurenceDate='" + getOccurenceDate() + "'" +
            ", notes='" + getNotes() + "'" +
            ", products='" + getPurchaseProducts() + "'" +
            ", payment='" + getPaymentsx() + "'" +
            ", store='" + getStore() + "'" +
            "}";
    }
}
