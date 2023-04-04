package com.cancodevery.ecom.auth;

import com.cancodevery.ecom.role.RoleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest
{
    private String email;
    private String password;
    private String username;
    @Enumerated(EnumType.STRING)
    private RoleType roleType;





}
