package sn.ucad.biblioapi.securite;

import org.springframework.boot.security.autoconfigure.web.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Profile("dev")
public class ConsoleH2Config {

    @Bean
    @Order(1)
    public SecurityFilterChain consoleH2(HttpSecurity http) throws Exception {
        http.securityMatcher(PathRequest.toH2Console())
            .authorizeHttpRequests(a -> a.anyRequest().permitAll())
            .csrf(c -> c.disable())
            .headers(h -> h.frameOptions(f -> f.sameOrigin()));
        return http.build();
    }
}
