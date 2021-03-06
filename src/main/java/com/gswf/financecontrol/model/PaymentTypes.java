package com.gswf.financecontrol.model;

import java.io.Serializable;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.hibernate.annotations.GenericGenerator;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "payment_types")
public class PaymentTypes implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "pay_type_seq")
    @GenericGenerator(name = "pay_type_seq", strategy = "native")
    private Long id;

    @Column(length = 20, nullable = false)
    private String description;
        
    @OneToMany(cascade=CascadeType.MERGE, fetch=FetchType.EAGER, mappedBy = "pk.payment")
    private List<BusinessPayment> businessed = new ArrayList<>();


    public PaymentTypes() {
    }

    public PaymentTypes(Long id, String description, List<BusinessPayment> business) {
        this.id = id;
        this.description = description;
        this.businessed = business;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JsonIgnore
    public List<BusinessPayment> getBusiness() {
        return this.businessed;
    }

    public void setBusiness(List<BusinessPayment> business) {
        this.businessed = business;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof PaymentTypes)) {
            return false;
        }
        PaymentTypes paymentTypes = (PaymentTypes) o;
        return Objects.equals(id, paymentTypes.id) 
        && Objects.equals(description, paymentTypes.description) 
        && Objects.equals(businessed, paymentTypes.businessed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, businessed);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", description='" + getDescription() + "'" +
            ", business='" + getBusiness() + "'" +
            "}";
    }
}
