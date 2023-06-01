package co.edu.javeriana.ms.LoadBalancer.services;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Collection;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTService {

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
    }

    public String generateToken(String username, Collection<? extends GrantedAuthority> roles) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", roles);
        return createToken(claims, username);
    }

    private boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    public boolean validateToken(String token, UserDetails ud){
        final String username = extractUsername(token);
        return (username.equals(ud.getUsername()) && !isTokenExpired(token));
    }

    private String createToken(Map<String, Object> claims, String username) {
        return Jwts.builder().setClaims(claims).setSubject(username).setIssuedAt(new Date(System.currentTimeMillis())).setExpiration(new Date(System.currentTimeMillis()+1000*60*30)).signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
    }

    private Key getSignKey() {
        // The string should be a secret
        byte[] keyBytes=Decoders.BASE64.decode("763979244226452948404D6351665468576D5A7134743777217A25432A462D4A614E645267556B586E3272357538782F413F4428472B4B6250655368566D5971");
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
