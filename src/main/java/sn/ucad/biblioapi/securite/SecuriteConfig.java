package sn.ucad.biblioapi.securite;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecuriteConfig {

    @Bean
    public SecurityFilterChain filtres(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(regles -> regles
                // 1. Ouvert à tous
                .requestMatchers("/error").permitAll()
                .requestMatchers("/actuator/health", "/actuator/health/**").permitAll()
                .requestMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/livres/**", "/api/auteurs/**").permitAll()
                // 2. Être connecté
                .requestMatchers(HttpMethod.POST, "/api/auth/jeton").authenticated()
                .requestMatchers(HttpMethod.GET, "/api/moi/**").authenticated()
                // 3. Réservé aux bibliothécaires (toute méthode, PATCH compris)
                .requestMatchers("/api/emprunts/retards").hasRole("BIBLIOTHECAIRE")
                .requestMatchers("/api/livres/**", "/api/auteurs/**", "/api/adherents/**",
                                 "/actuator/**").hasRole("BIBLIOTHECAIRE")
                // 4. Les emprunts : être connecté (le service vérifie à qui ils appartiennent)
                .requestMatchers("/api/emprunts/**").authenticated()
                // 5. Tout le reste est refusé
                .anyRequest().denyAll())
            // TODO ch11 (TP 8b, étape 2) : accepter aussi les jetons JWT — oauth2ResourceServer avec le
            // JwtAuthenticationConverter de JwtConfig, reçu en paramètre de cette méthode.
            .httpBasic(Customizer.withDefaults());   // identifiant + mot de passe à chaque requête
        return http.build();
    }

    @Bean
    public PasswordEncoder encodeurMotDePasse() {
        return new BCryptPasswordEncoder();
    }
}
