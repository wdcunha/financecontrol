package com.gswf.financecontrol.model;

import java.time.LocalDate;
import java.util.Objects;

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
    @AssociationOverride(name = "pk.payment", joinColumns = @JoinColumn(name = "payment_id")),
    @AssociationOverride(name = "pk.installment", joinColumns = @JoinColumn(name = "installment_id")) })
public class BusinessPayment {

    @EmbeddedId
    private BusinessPaymentPk pk = new BusinessPaymentPk();

    @Column(length = 10)
    private Double amount;

    @Column(length = 8, nullable = false)
    private LocalDate payDate;

    private boolean payed;

    public BusinessPayment() {
    }

    public BusinessPayment(BusinessPaymentPk pk, Double amount,
                           LocalDate payDate, boolean payed) {
        this.pk = pk;
        this.amount = amount;
        this.payDate = payDate;
        this.payed = payed;
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

    public String getPaymentType() {
        return this.getPk().getPayment().getDescription();
    }

	public void setPayment(PaymentTypes payment) {
		getPk().setPayment(payment);
	}

    @Transient
    @JsonBackReference(value = "business-payment")
    public Business getBusiness() {
        return this.getPk().getBusiness();
    }

	public void setBusiness(Business business) {
		getPk().setBusiness(business);
	}

	public void setInstallment(Integer installment) {
		getPk().setInstallment(installment);
	}

    public Double getAmount() {
        return this.amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDate getPayDate() {
        return this.payDate;
    }

    public void setPayDate(LocalDate payDate) {
        this.payDate = payDate;
    }

    public boolean isPayed() {
        return this.payed;
    }

    public boolean getPayed() {
        return this.payed;
    }

    public void setPayed(boolean payed) {
        this.payed = payed;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof BusinessPayment)) {
            return false;
        }
        BusinessPayment businessPayment = (BusinessPayment) o;
        return Objects.equals(pk, businessPayment.pk) 
        && Objects.equals(amount, businessPayment.amount) 
        && Objects.equals(payDate, businessPayment.payDate) 
        && payed == businessPayment.payed;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pk, amount, payDate, payed);
    }

    @Override
    public String toString() {
        return "{" +
            " pk='" + getPk() + "'" +
            ", amount='" + getAmount() + "'" +
            ", payDate='" + getPayDate() + "'" +
            ", payed='" + isPayed() + "'" +
            "}";
    }

}
