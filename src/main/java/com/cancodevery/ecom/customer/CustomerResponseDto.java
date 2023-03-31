package com.cancodevery.ecom.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerResponseDto
{
    private int id;


    private String firstName;


    private String lastName;


    private Date dob;


    private String email;


    private String password;


    private String contact;
}
