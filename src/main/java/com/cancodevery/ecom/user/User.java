package com.cancodevery.ecom.user;


import com.cancodevery.ecom.Role.Role;
import com.cancodevery.ecom.Role.Roles;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
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
    @Column(name = "user_name")
    private String username;

    @Column(name = "user_password")
    private String password;


    @Enumerated(EnumType.STRING)
    private Roles roles;
//    @ManyToMany(fetch = FetchType.EAGER)
//            @JoinTable(
//                    joinColumns = {@JoinColumn(name = "user_id",referencedColumnName = "user_id")},
//                    inverseJoinColumns = {@JoinColumn(name = "role_id",referencedColumnName = "role_id")}
//            )
//    List<Role> roles=new ArrayList<>();
}
