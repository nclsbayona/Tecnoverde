package co.edu.javeriana.ms.LoadBalancer;

import java.util.List;
import java.util.Arrays;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
public class Filter extends AbstractGatewayFilterFactory<Filter.Config> {

    @Autowired
    private RouteValidator routeValidator;

    @Autowired
    private JWTService jwtService;

    public Filter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            if (routeValidator.isSecured.test(exchange.getRequest())) {
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION))
                    throw new RuntimeException("Authorization not present");
                String headers = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if (headers != null && headers.startsWith("Bearer "))
                    headers=headers.substring(7);
                try{
                    System.out.println("Token: "+headers);
                    jwtService.validateToken(headers);
                }catch(Exception e){
                    throw new RuntimeException("Authorization not valid");
                }
                if (!jwtService.hasRole(headers, config.getRole()))
                    throw new RuntimeException("Unauthorized");
            }
            return chain.filter(exchange);
        });
    };

    @Getter
    @Setter
    public static class Config {
        private String role;
    }

    @Override
    public List<String> shortcutFieldOrder() {
        // we need this to use shortcuts in the application.yml
        return Arrays.asList("role");
    }
}