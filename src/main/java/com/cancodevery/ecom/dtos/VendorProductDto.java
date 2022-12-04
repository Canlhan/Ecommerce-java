package com.cancodevery.ecom.dtos;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor

@Builder
public class VendorProductDto
{


    private int unitInStock;

    private  boolean state;
    private int unitPrice;
    private float price;
    private int quantity;
    private int vendorId;
    private String description;
    private int categoryId;
    private String productName;
    private String productPhoto;

    public VendorProductDto(int unitInStock, boolean state, int unitPrice, float price, int quantity, int vendorId, String description,
                            int categoryId, String productName, String productPhoto) {
        this.unitInStock = unitInStock;
        this.state = state;
        this.unitPrice = unitPrice;
        this.price = price;
        this.quantity = quantity;
        this.vendorId = vendorId;
        this.description = description;
        this.categoryId = categoryId;
        this.productName = productName;
        this.productPhoto = productPhoto;
    }
}
