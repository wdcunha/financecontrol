package com.gswf.financecontrol.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "purchase_payment")
public class PurchasePayment {

    @EmbeddedId
    private PurchasePaymentPk pk;
        
    @ManyToOne(fetch=FetchType.LAZY)
    @MapsId("id")
    private Purchases purchase;
        
    @ManyToOne(fetch=FetchType.LAZY)
    @MapsId("id")
    private PaymentTypes payment;

    @Column(length = 5)
    private Integer quantity;

    @Column(length = 10)
    private Double amount;

    public PurchasePayment(Purchases purchase, PaymentTypes payment, Integer quantity, Double amount) {
        pk = new PurchasePaymentPk(purchase.getId(), payment.getId());

        this.purchase = purchase;
        this.payment = payment;
        this.quantity = quantity;
        this.amount = amount;
    }
}
