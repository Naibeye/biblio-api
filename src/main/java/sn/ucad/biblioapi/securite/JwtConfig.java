package sn.ucad.biblioapi.securite;

import com.nimbusds.jose.jwk.source.ImmutableSecret;
import java.nio.charset.StandardCharsets;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtValidators;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

@Configuration
public class JwtConfig {
    public static final String EMETTEUR = "biblio-api";

    private final SecretKey cle;

    public JwtConfig(@Value("${biblio.jwt.secret}") String secret) {
        byte[] octets = secret.getBytes(StandardCharsets.UTF_8);
        if (octets.length < 32) {
            throw new IllegalStateException("biblio.jwt.secret : au moins 32 octets (256 bits) requis");
        }
        this.cle = new SecretKeySpec(octets, "HmacSHA256");
    }

    @Bean
    public JwtEncoder jwtEncoder() {
        return new NimbusJwtEncoder(new ImmutableSecret<>(cle));
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        NimbusJwtDecoder decodeur = NimbusJwtDecoder.withSecretKey(cle).build();
        decodeur.setJwtValidator(JwtValidators.createDefaultWithIssuer(EMETTEUR));
        return decodeur;
    }

    @Bean
    public JwtAuthenticationConverter convertisseurJwt() {
        JwtGrantedAuthoritiesConverter roles = new JwtGrantedAuthoritiesConverter();
        roles.setAuthoritiesClaimName("roles");
        roles.setAuthorityPrefix("ROLE_");
        JwtAuthenticationConverter convertisseur = new JwtAuthenticationConverter();
        convertisseur.setJwtGrantedAuthoritiesConverter(roles);
        return convertisseur;
    }
}
