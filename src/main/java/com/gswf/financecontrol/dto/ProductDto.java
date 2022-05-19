package com.gswf.financecontrol.dto;

import com.gswf.financecontrol.model.Business;
import com.gswf.financecontrol.model.Sales;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductDto {
      
    private long id;
    
    private String description;
    private String size;
    private Double price;
    private int quantity;
    private String notes;

    private Business purchases;
    private Sales sales;
}
