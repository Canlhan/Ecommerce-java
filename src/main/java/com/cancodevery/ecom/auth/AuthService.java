package com.cancodevery.ecom.auth;

import com.cancodevery.ecom.Role.Role;
import com.cancodevery.ecom.Role.Roles;
import com.cancodevery.ecom.config.JWTService;
import com.cancodevery.ecom.user.User;
import com.cancodevery.ecom.user.UserDetail;
import com.cancodevery.ecom.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService
{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;

    private final AuthenticationManager  authenticationManager;
    public AuthenticationResponse register(RegisterRequest request)
    {

        var user= User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .username(request.getUsername())
                .roles(Roles.CUSTOMER)
                .build();
        UserDetail userDetail=new UserDetail(user);
        userRepository.save(user);
        var jwt=jwtService.generateToken(userDetail);
        return AuthenticationResponse.builder().token(jwt).build();

    }

    public AuthenticationResponse authenticate(AuthenticationRequest request)
    {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword())
        );
        var user=userRepository.findByEmail(request.getEmail());
        var userDetail=new UserDetail(user);
        var jwt=jwtService.generateToken(userDetail);
        return AuthenticationResponse.builder().token(jwt).build();
    }
}
