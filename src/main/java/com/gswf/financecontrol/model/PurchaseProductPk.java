package com.gswf.financecontrol.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Embeddable
public class PurchaseProductPk implements Serializable {
    
    private static final long serialVersionUID = 476151177562655457L;
        
    @ManyToOne(optional = false, fetch=FetchType.LAZY)
    @JoinColumn(name = "purchase")
    @JsonManagedReference
    private Purchases purchase;
        
    @ManyToOne(optional = false, fetch=FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    public Purchases getPurchase() {
        return this.purchase;
    }

    public void setPurchase(Purchases purchase) {
        this.purchase = purchase;
    }

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
        final int prime = 31;
        int result = 1;

        result = prime * result + ((purchase.getId() == null)
          ? 0
          : purchase
            .getId()
            .hashCode());
        result = prime * result + ((product.getId() == null)
          ? 0
          : product
            .getId()
            .hashCode());

        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        PurchaseProductPk other = (PurchaseProductPk) obj;
        if (purchase == null) {
            if (other.purchase != null) {
                return false;
            }
        } else if (!purchase.equals(other.purchase)) {
            return false;
        }

        if (product == null) {
            if (other.product != null) {
                return false;
            }
        } else if (!product.equals(other.product)) {
            return false;
        }

        return true;
    }
}
