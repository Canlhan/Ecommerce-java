package com.cancodevery.ecom.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/auth/customer")
@RestController
@RequiredArgsConstructor
@CrossOrigin
public class CustomerAuthController
{

    private final AuthService authService;
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody AuthenticationRequest request)
    {
        return ResponseEntity.ok().body(authService.authenticate(request));
    }
}
