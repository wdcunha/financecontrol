package com.gswf.financecontrol.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Embeddable
public class BusinessPaymentPk implements Serializable {
    
    private static final long serialVersionUID = 476151177562655457L;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "business")
    private Business business;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "payment")
    private PaymentTypes payment;
            
    private Integer installment;

    @JsonBackReference
    public Business getBusiness() {
        return this.business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }

    @JsonBackReference
    public PaymentTypes getPayment() {
        return this.payment;
    }

    public void setPayment(PaymentTypes payment) {
        this.payment = payment;
    }

    public Integer getInstallment() {
        return this.installment;
    }

    public void setInstallment(Integer installment) {
        this.installment = installment;
    }

    @Override
    public String toString() {
        return "{" +
            " business='" + getBusiness() + "'" +
            ", payment='" + getPayment() + "'" +
            ", installment='" + getInstallment() + "'" +
            "}";
    }

    @Override
    public int hashCode() {
        int result;
        result = (business != null ? business.hashCode() : 0);
        result = 31 * result + (payment != null ? payment.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        BusinessPaymentPk businessPaymentPk = (BusinessPaymentPk) obj;

        if (business != null ? !business.equals(businessPaymentPk.business) : businessPaymentPk.business != null) 
            return false;
        if (payment != null ? !payment.equals(businessPaymentPk.payment) : businessPaymentPk.payment != null)
            return false;

        return true;
    }

}
