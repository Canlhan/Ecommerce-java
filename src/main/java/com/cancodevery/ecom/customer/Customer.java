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
    int id;

    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    @Column(name = "dob")
    Date dob;

    @Column(name = "email")
    String email;

    @Column(name = "password")
    String password;

    @Column(name = "contact")
    String contact;




}
