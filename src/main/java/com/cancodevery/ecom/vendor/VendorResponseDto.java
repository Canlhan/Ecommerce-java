package com.cancodevery.ecom.vendor;

import com.cancodevery.ecom.vendorproduct.VendorProduct;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VendorResponseDto
{


    private int id;
    private String name;

    private String adress;

    private String email;

    private String contact;


     private List<VendorProduct> vendorProducts=new ArrayList<>();
}
