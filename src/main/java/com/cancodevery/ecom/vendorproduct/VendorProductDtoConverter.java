package com.cancodevery.ecom.vendorproduct;

import com.cancodevery.ecom.dtos.VendorProductDto;
import org.springframework.stereotype.Component;

@Component
public class VendorProductDtoConverter
{


    public  VendorProductDto convert(VendorProduct from){

        VendorProductDto vendorProductDto=new VendorProductDto();

        vendorProductDto.setProductName(from.getProduct().getProductName());
        vendorProductDto.setState(from.getProduct().isState());
        vendorProductDto.setUnitPrice(from.getProduct().getUnitPrice());
        vendorProductDto.setUnitInStock(from.getProduct().getUnitInStock());

        vendorProductDto.setVendorId(from.getVendor().getId());
        vendorProductDto.setDescription(from.getDescription());
        vendorProductDto.setCategoryId(from.getProduct().getCategory().getId());
        vendorProductDto.setPrice(from.getPrice());
        vendorProductDto.setProductPhoto(from.getProduct().getProductPhoto());
        vendorProductDto.setQuantity(from.getQuantity());

        return vendorProductDto;

    }


}
