package com.gswf.financecontrol.model;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "purchase_product")
@AssociationOverrides({
    @AssociationOverride(name = "pk.purchase", joinColumns = @JoinColumn(name = "purchase_id")),
    @AssociationOverride(name = "pk.product", joinColumns = @JoinColumn(name = "product_id")) })
public class PurchaseProduct {

    @EmbeddedId
    private PurchaseProductPk pk = new PurchaseProductPk();

    @Column(length = 5, nullable = false)
    private Integer quantity;

    @Column(length = 5, nullable = false)
    private Double price;

    public PurchaseProduct() {}

    public PurchaseProductPk getPk() {
        return this.pk;
    }

    public void setPk(PurchaseProductPk pk) {
        this.pk = pk;
    }

    @Transient
    public Product getProduct() {
        return this.getPk().getProduct();
    }

	public void setProduct(Product product) {
		getPk().setProduct(product);
	}

    @Transient
    @JsonBackReference
    public Purchases getPurchase() {
        return this.getPk().getPurchase();
    }

	public void setPurchase(Purchases purchase) {
		getPk().setPurchase(purchase);
	}

    @Transient
    public Double getTotalPrice() {
        return getProduct().getPrice() * getQuantity();
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public int hashCode() {
		return (getPk() != null ? getPk().hashCode() : 0);
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
