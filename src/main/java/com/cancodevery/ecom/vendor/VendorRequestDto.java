package com.cancodevery.ecom.vendor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VendorRequestDto
{
    private String name;


    private String adress;


    private String email;


    private String password;


    private String contact;



}
