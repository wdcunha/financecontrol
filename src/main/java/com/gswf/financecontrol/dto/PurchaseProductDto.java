package com.gswf.financecontrol.dto;

import com.gswf.financecontrol.model.Product;
import com.gswf.financecontrol.model.Purchases;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PurchaseProductDto {
        
    private Purchases purchase;
    private Product product;
    private Integer quantity;

}
