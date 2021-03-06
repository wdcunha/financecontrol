package com.gswf.financecontrol.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Embeddable
public class BusinessProductPk implements Serializable {
    private static final long serialVersionUID = 1L;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "business")
    private Business business;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "product")
    private Product product;

    @JsonBackReference(value = "business-product")
    public Business getBusiness() {
        return this.business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }

    @JsonBackReference
    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "{" +
            " business='" + getBusiness() + "'" +
            ", product='" + getProduct() + "'" +
            "}";
    }

    @Override
    public int hashCode() {
        int result;
        result = (business != null ? business.hashCode() : 0);
        result = 31 * result + (product != null ? product.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        BusinessProductPk that = (BusinessProductPk) obj;

        if (business != null ? !business.equals(that.business) : that.business != null) 
            return false;
        if (product != null ? !product.equals(that.product) : that.product != null)
            return false;

        return true;
    }
}
