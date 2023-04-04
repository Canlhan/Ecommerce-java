package com.cancodevery.ecom.user;


import com.cancodevery.ecom.role.RoleType;
import lombok.*;

import javax.persistence.*;

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
    private RoleType roleType;
//    @ManyToMany(fetch = FetchType.EAGER)
//            @JoinTable(
//                    joinColumns = {@JoinColumn(name = "user_id",referencedColumnName = "user_id")},
//                    inverseJoinColumns = {@JoinColumn(name = "role_id",referencedColumnName = "role_id")}
//            )
//    List<Role> roles=new ArrayList<>();
}
