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
@Table(name = "sales_product")
@AssociationOverrides({
    @AssociationOverride(name = "pk.sale", joinColumns = @JoinColumn(name = "sale_id")),
    @AssociationOverride(name = "pk.product", joinColumns = @JoinColumn(name = "product_id")) })
public class SalesProduct {

    @EmbeddedId
    private SalesProductPk pk = new SalesProductPk();

    @Column(length = 5, nullable = false)
    private Integer quantity;

    @Column(length = 5, nullable = false)
    private Double price;

    public SalesProduct() {}

    public SalesProduct(SalesProductPk pk, Integer quantity, Double price) {
        this.pk = pk;
        this.quantity = quantity;
        this.price = price;
    }

    public SalesProductPk getPk() {
        return this.pk;
    }

    public void setPk(SalesProductPk pk) {
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
    public Sales getSale() {
        return this.getPk().getSale();
    }

	public void setSale(Sales sale) {
		getPk().setSale(sale);
	}

    @Transient
    public Double getTotalPrice() {
        return getPrice() * getQuantity();
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
        SalesProduct other = (SalesProduct) obj;
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
