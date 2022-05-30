package com.gswf.financecontrol.model;

import java.time.LocalDate;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

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

    @JsonFormat(pattern = "dd/MM/yyyy")
    @JsonSerialize(using = LocalDateSerializer.class)
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

    @JsonIgnore
    public Business getBusiness() {
        return this.getPk().getBusiness();
    }

	public void setBusiness(Business business) {
		getPk().setBusiness(business);
	}

    @Transient
    @JsonBackReference
    public Integer getInstallment() {
        return this.getPk().getInstallment();
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
