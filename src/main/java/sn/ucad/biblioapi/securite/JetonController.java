package sn.ucad.biblioapi.securite;

import java.time.Clock;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JetonController {
    private final JwtEncoder encodeur;
    private final Clock horloge;

    public JetonController(JwtEncoder encodeur, Clock horloge) {
        this.encodeur = encodeur;
        this.horloge = horloge;
    }

    @PostMapping("/api/auth/jeton")
    public Map<String, String> jeton(Authentication utilisateur) {
        if (utilisateur instanceof JwtAuthenticationToken) {
            throw new AccessDeniedException("Mot de passe requis pour obtenir un jeton");
        }
        Instant maintenant = Instant.now(horloge);
        List<String> roles = utilisateur.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .map(r -> r.replaceFirst("^ROLE_", ""))
                .toList();
        JwtClaimsSet contenu = JwtClaimsSet.builder()
                .issuer(JwtConfig.EMETTEUR)
                .subject(utilisateur.getName())
                .issuedAt(maintenant)
                .expiresAt(maintenant.plus(1, ChronoUnit.HOURS))
                .claim("roles", roles)
                .build();
        JwsHeader entete = JwsHeader.with(MacAlgorithm.HS256).build();
        String jeton = encodeur.encode(JwtEncoderParameters.from(entete, contenu)).getTokenValue();
        return Map.of("jeton", jeton);
    }
}
