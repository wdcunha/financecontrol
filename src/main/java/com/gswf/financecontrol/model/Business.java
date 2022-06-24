package com.gswf.financecontrol.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import org.hibernate.annotations.GenericGenerator;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "business")
public class Business implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "business_seq")
    @GenericGenerator(name = "business_seq", strategy = "native")
    private Long id;

    @Column(length = 8, nullable = false)
    private LocalDate businessDate;
    @Column(length = 255, nullable = true)
    private String notes;

    @OneToMany(cascade=CascadeType.MERGE, mappedBy = "pk.business")
    private List<BusinessProduct> businessProducts = new ArrayList<>();

    @OneToMany(cascade=CascadeType.MERGE, mappedBy = "pk.business")
    private List<BusinessPayment> businessPayments = new ArrayList<>();

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "entity")
    private Person entity;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "business_type")
    private BusinessTypes businessType;

    public Business() {
    }

    public Business(LocalDate businessDate, String notes, 
                     List<BusinessProduct> businessProducts, List<BusinessPayment> payments, 
                     Person entity, BusinessTypes businessType) {
        this.businessDate = businessDate;
        this.notes = notes;
        this.businessProducts = businessProducts;
        this.businessPayments = payments;
        this.entity = entity;
        this.businessType = businessType;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getBusinessTotal() {
        return getBusinessProducts().stream()
        .map(BusinessProduct::getTotalPrice)
        .mapToDouble(Double::valueOf)
        .sum();
    }

    public LocalDate getBusinessDate() {
        return this.businessDate;
    }

    public void setBusinessDate(LocalDate businessDate) {
        this.businessDate = businessDate;
    }

    public String getNotes() {
        return this.notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @JsonManagedReference(value = "business-product")
    public List<BusinessProduct> getBusinessProducts() {
        return this.businessProducts;
    }

    public void setBusinessProducts(List<BusinessProduct> businessProducts) {
        this.businessProducts = businessProducts;
    }

    @JsonManagedReference(value = "business-payment")
    public List<BusinessPayment> getBusinessPayments() {
        return this.businessPayments;
    }

    public void setBusinessPayments(List<BusinessPayment> payments) {
        this.businessPayments = payments;
    }

    public Map<String, Long> getCountIntallments() {
        return getBusinessPayments().stream()
        .collect(Collectors
        .groupingBy(BusinessPayment::getPaymentType, Collectors.counting()));
    }

    public Set<Double> getIntallmentsValues() {
        return getBusinessPayments().stream()
        .map(BusinessPayment::getAmount)
        .collect(Collectors.toSet());
    }

    public Person getEntity() {
        return this.entity;
    }

    public void setEntity(Person entity) {
        this.entity = entity;
    }

    public BusinessTypes getBusinessType() {
        return this.businessType;
    }

    public void setBusinessType(BusinessTypes businessType) {
        this.businessType = businessType;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Business)) {
            return false;
        }
        Business business = (Business) o;
        return Objects.equals(id, business.id) 
        && Objects.equals(businessDate, business.businessDate) 
        && Objects.equals(notes, business.notes) 
        && Objects.equals(businessProducts, business.businessProducts) 
        && Objects.equals(businessPayments, business.businessPayments) 
        && Objects.equals(entity, business.entity)
        && Objects.equals(businessType, business.businessType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, businessDate, notes, businessProducts, businessPayments, entity, businessType);
    }

    @Override
    public String toString() {
        return "{" +
            " businessId='" + getId() + "'" +
            ", businessTotal='" + getBusinessTotal() + "'" +
            ", businessDate='" + getBusinessDate() + "'" +
            ", notes='" + getNotes() + "'" +
            ", products='" + getBusinessProducts() + "'" +
            ", payment='" + getBusinessPayments() + "'" +
            ", entity='" + getEntity() + "'" +
            ", businessType='" + getBusinessType() + "'" +
            ", getCountIntallments='" + getCountIntallments() + "'" +
            "}";
    }
}
