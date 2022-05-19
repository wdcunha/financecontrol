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
@Table(name = "business_types")
public class BusinessTypes implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "business_type_seq")
    @GenericGenerator(name = "business_type_seq", strategy = "native")
    private Long id;

    @Column(length = 10, nullable = false)
    private String description;
        
    @OneToMany(cascade=CascadeType.MERGE, fetch=FetchType.EAGER, mappedBy = "businessType")
    @JsonIgnore
    private List<Business> business = new ArrayList<>();

    public BusinessTypes() {
    }

    public BusinessTypes(Long id, String description, List<Business> business) {
        this.id = id;
        this.description = description;
        this.business = business;
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
    public List<Business> getBusiness() {
        return this.business;
    }

    public void setBusiness(List<Business> business) {
        this.business = business;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof PaymentTypes)) {
            return false;
        }
        BusinessTypes businessTypes = (BusinessTypes) o;
        return Objects.equals(id, businessTypes.id) 
        && Objects.equals(description, businessTypes.description) 
        && Objects.equals(business, businessTypes.business);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, business);
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
