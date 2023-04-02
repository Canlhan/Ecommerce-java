package com.cancodevery.ecom.auth;

import com.cancodevery.ecom.Role.Roles;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest
{
    private String email;
    private String password;
    private String username;
    private Roles roles;





}
