package com.gswf.financecontrol.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "purchase_product")
public class PurchaseProduct {

    @EmbeddedId
    @JsonIgnore
    private PurchaseProductPk pk;

    @Column(length = 5, nullable = false)
    private Integer quantity;

    public PurchaseProduct(Purchases purchase, Product product, Integer quantity) {
        pk = new PurchaseProductPk();
        pk.setPurchase(purchase);
        pk.setProduct(product);
        this.quantity = quantity;
    }

    @Transient
    public Product getProduct() {
        return this.pk.getProduct();
    }

    @Transient
    public Purchases getPurchase() {
        return this.pk.getPurchase();
    }

    @Transient
    public Double getTotalPrice() {
        return getProduct().getPrice() * getQuantity();
    }

    public PurchaseProductPk getPk() {
        return this.pk;
    }

    public void setPk(PurchaseProductPk pk) {
        this.pk = pk;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((pk == null) ? 0 : pk.hashCode());

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
        PurchaseProduct other = (PurchaseProduct) obj;
        if (pk == null) {
            if (other.pk != null) {
                return false;
            }
        } else if (!pk.equals(other.pk)) {
            return false;
        }

        return true;
    }
}
