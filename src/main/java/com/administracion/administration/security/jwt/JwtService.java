package com.administracion.administration.security.jwt;

import java.security.Key;
import java.util.Date;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.administracion.administration.entities.Auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {    
    private static final String SECRET_KEY = "ESTA_ES_UNA_CLAVE_SECRETA_MUY_LARGA_CAMBIALA";

    private static final long EXPIRATION = 1000*60*24;

    private Key getSigningKey(){
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    //--Genera el topken al usuario
    public String generarToken(Auth auth) {

        return Jwts.builder()
            .setSubject(auth.getEmail()) // identificador principal
            .claim("id", auth.getId())
            .claim("rol", auth.getUsuario().getRol().name())
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
            .signWith(getSigningKey(), SignatureAlgorithm.HS256)
            .compact();
    }

    //---Extraer claims
    private Claims extraerClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    //---Extraer Email (Subject)
    public String extraerEmail(String token){
        return extraerClaims(token).getSubject();
    }

    //---Validar Expiracion
    private boolean tokenExpirado(String token){
        return extraerClaims(token)
                .getExpiration()
                .before(new Date());
    }

    //---Validar token completo
    public boolean tokenValido(String token, UserDetails userDetails){
        String emailToken = extraerEmail(token);

        return emailToken.equals(userDetails.getUsername()) && !tokenExpirado(token);

    }
}
