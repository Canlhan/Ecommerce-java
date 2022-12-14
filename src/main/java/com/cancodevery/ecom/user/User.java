package com.cancodevery.ecom.user;


import com.cancodevery.ecom.Role.Role;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Users")
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
            @Column(name = "user_id")
    int id;

    @Column(name = "user_email")
    private String email;

    @Column(name = "user_password")
    private String password;


    @ManyToMany(fetch = FetchType.EAGER)
            @JoinTable(
                    joinColumns = {@JoinColumn(name = "user_id",referencedColumnName = "user_id")},
                    inverseJoinColumns = {@JoinColumn(name = "role_id",referencedColumnName = "role_id")}
            )
    List<Role> roles=new ArrayList<>();
}
