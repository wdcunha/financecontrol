package com.gswf.financecontrol.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class PurchasePaymentPk implements Serializable {
    
    private static final long serialVersionUID = 476151177562655457L;

    @Column(name = "purchase_id")
    private Long purchaseId;

    @Column(name = "payment_id")
    private Long paymentId;
}
