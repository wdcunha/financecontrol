package com.gswf.financecontrol.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Embeddable
public class SalesProductPk implements Serializable {
    private static final long serialVersionUID = 1L;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "sale")
    private Sales sale;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "product")
    private Product product;

    @JsonBackReference
    public Sales getSale() {
        return this.sale;
    }

    public void setSale(Sales sale) {
        this.sale = sale;
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
            " sale='" + getSale() + "'" +
            ", product='" + getProduct() + "'" +
            "}";
    }

    @Override
    public int hashCode() {
        int result;
        result = (sale != null ? sale.hashCode() : 0);
        result = 31 * result + (product != null ? product.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        SalesProductPk that = (SalesProductPk) obj;

        if (sale != null ? !sale.equals(that.sale) : that.sale != null) 
            return false;
        if (product != null ? !product.equals(that.product) : that.product != null)
            return false;

        return true;
    }
}
