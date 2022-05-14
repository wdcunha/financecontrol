package com.gswf.financecontrol.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Embeddable
public class PurchaseProductPk implements Serializable {
    private static final long serialVersionUID = 1L;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "purchase")
    private Purchases purchase;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "product")
    private Product product;

    @JsonBackReference
    public Purchases getPurchase() {
        return this.purchase;
    }

    public void setPurchase(Purchases purchase) {
        this.purchase = purchase;
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
            " purchase='" + getPurchase() + "'" +
            ", product='" + getProduct() + "'" +
            "}";
    }

    @Override
    public int hashCode() {
        int result;
        result = (purchase != null ? purchase.hashCode() : 0);
        result = 31 * result + (product != null ? product.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        PurchaseProductPk that = (PurchaseProductPk) obj;

        if (purchase != null ? !purchase.equals(that.purchase) : that.purchase != null) 
            return false;
        if (product != null ? !product.equals(that.product) : that.product != null)
            return false;

        return true;
    }
}
