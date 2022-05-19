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
@Table(name = "business_payment")
@AssociationOverrides({
    @AssociationOverride(name = "pk.business", joinColumns = @JoinColumn(name = "business_id")),
    @AssociationOverride(name = "pk.payment", joinColumns = @JoinColumn(name = "payment_id")) })
public class BusinessPayment {

    @EmbeddedId
    private BusinessPaymentPk pk = new BusinessPaymentPk();
        
    @Column(length = 5)
    private Integer quantity;

    @Column(length = 10)
    private Double amount;

    public BusinessPayment() {
    }

    public BusinessPayment(BusinessPaymentPk pk, Integer quantity, Double amount) {
        this.pk = pk;
        this.quantity = quantity;
        this.amount = amount;
    }

    public BusinessPaymentPk getPk() {
        return this.pk;
    }

    public void setPk(BusinessPaymentPk pk) {
        this.pk = pk;
    }

    @Transient
    public PaymentTypes getPayment() {
        return this.getPk().getPayment();
    }

	public void setPayment(PaymentTypes payment) {
		getPk().setPayment(payment);
	}

    @Transient
    @JsonBackReference
    public Business getBusiness() {
        return this.getPk().getBusiness();
    }

	public void setBusiness(Business business) {
		getPk().setBusiness(business);
	}

    @Transient
    public Double getInstallments() {
        return getAmount() / getQuantity();
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getAmount() {
        return this.amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
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
        BusinessPayment other = (BusinessPayment) obj;
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
