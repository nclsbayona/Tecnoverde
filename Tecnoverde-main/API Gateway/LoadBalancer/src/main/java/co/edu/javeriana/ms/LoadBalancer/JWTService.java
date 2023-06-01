package co.edu.javeriana.ms.LoadBalancer;

import java.security.Key;
import java.util.List;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTService {

    public void validateToken(final String token) {
        Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
    }

    private Key getSignKey() {
        // The string should be a secret
        byte[] keyBytes = Decoders.BASE64.decode(
                "763979244226452948404D6351665468576D5A7134743777217A25432A462D4A614E645267556B586E3272357538782F413F4428472B4B6250655368566D5971");
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
    }

    public boolean hasRole(String headers, String role) {
        boolean ok = false;
        final Claims claims = extractAllClaims(headers);
        for (Object o : claims.get("roles", List.class)) {
            if (!ok) {
                System.out.println(o.toString());
                System.out.println(o.toString().contains(role));
                ok = o.toString().contains(role);
            }
        }
        return ok;
    }

}