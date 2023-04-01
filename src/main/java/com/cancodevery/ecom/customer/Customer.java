package com.cancodevery.ecom.customer;

import com.cancodevery.ecom.product.Product;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Getter
@Setter

public class Customer implements Serializable {



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
     private String lastName;

    @Column(name = "dob")
     private Date dob;

    @Column(name = "email")
     private String email;


    @Column(name = "contact")
     private String contact;




}
