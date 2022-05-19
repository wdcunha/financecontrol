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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.hibernate.annotations.GenericGenerator;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "product")
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "product_seq")
    @GenericGenerator(name = "product_seq", strategy = "native")
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
    
    @OneToMany(cascade=CascadeType.MERGE, fetch=FetchType.EAGER, mappedBy = "pk.product")
    private List<BusinessProduct> business = new ArrayList<>();

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "saled")
    @JsonIgnore
    private Sales saled;

    public Product() {
    }

    public Product(String description, String size, Double price, int quantity, String notes, List<BusinessProduct> business, Sales sale) {
        this.description = description;
        this.size = size;
        this.price = price;
        this.quantity = quantity;
        this.notes = notes;
        this.business = business;
        this.saled = sale;
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

    public String getSize() {
        return this.size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Double getPrice() {
        return this.price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getNotes() {
        return this.notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @JsonIgnore
    public List<BusinessProduct> getBusinessProduct() {
        return this.business;
    }

    public void setBusinessProduct(List<BusinessProduct> business) {
        this.business = business;
    }

    public Sales getSaled() {
        return this.saled;
    }

    public void setSaled(Sales saled) {
        this.saled = saled;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Product)) {
            return false;
        }
        Product product = (Product) o;
        return Objects.equals(id, product.id) 
        && Objects.equals(description, product.description) 
        && Objects.equals(size, product.size) 
        && Objects.equals(price, product.price) 
        && quantity == product.quantity 
        && Objects.equals(notes, product.notes) 
        && Objects.equals(business, product.business) 
        && Objects.equals(saled, product.saled);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, size, price, quantity, notes, business, saled);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", description='" + getDescription() + "'" +
            ", size='" + getSize() + "'" +
            ", price='" + getPrice() + "'" +
            ", quantity='" + getQuantity() + "'" +
            ", notes='" + getNotes() + "'" +
            ", business='" + getBusinessProduct() + "'" +
            ", sales='" + getSaled() + "'" +
            "}";
    }
}
