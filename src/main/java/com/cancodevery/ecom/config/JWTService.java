package com.cancodevery.ecom.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTService {

    private static  final String SECRET_KEY="586E3272357538782F413F4428472B4B6250655368566D597133733676397924";

    public String extractUserEmail(String jwtToken) {

    return extractClaim(jwtToken, Claims::getSubject);

    }
    public boolean isTokenValid(String jwtToken, UserDetails userDetails) {

        final String userEmail=extractUserEmail(jwtToken);
        return (userEmail.equals(userDetails.getUsername()) && !isTokenExpired(jwtToken));
    }

    private boolean isTokenExpired(String jwtToken) {
        return extractExpiration(jwtToken).before(new Date());
    }

    private Date extractExpiration(String jwtToken) {
        return extractClaim(jwtToken,Claims::getExpiration);
    }

    public String generateToken(UserDetails userDetails) {

        return  generateToken(new HashMap<>(),userDetails);
    }
    public String generateToken(Map<String,Object> extraClaims,
                                UserDetails userDetails) {

        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername()).
                setIssuedAt(new Date(System.currentTimeMillis())).
                setExpiration(new Date(System.currentTimeMillis()+1000*60*60*10))
               .signWith(getSigningKey(), SignatureAlgorithm.HS256)

                .compact();
    }
    public <T> T extractClaim(String jwtToken, Function<Claims,T> claimResolver) {

        final Claims claims=extractAllClaims(jwtToken);
        return claimResolver.apply(claims);

    }

    private Claims extractAllClaims(String jwtToken) {

        return Jwts.
                parserBuilder().
                setSigningKey(getSigningKey()).build()
                .parseClaimsJws(jwtToken).getBody();
    }

    private Key getSigningKey() {

        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
