package sn.ucad.biblioapi.securite;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UtilisateursEnBase implements UserDetailsService {
    private final UtilisateurRepository utilisateurs;

    public UtilisateursEnBase(UtilisateurRepository utilisateurs) {
        this.utilisateurs = utilisateurs;
    }

    @Override
    public UserDetails loadUserByUsername(String identifiant) {
        Utilisateur u = utilisateurs.findByIdentifiant(identifiant)
                .orElseThrow(() -> new UsernameNotFoundException("Identifiant inconnu"));
        return User.withUsername(u.getIdentifiant())
                .password(u.getEmpreinte())
                .roles(u.getRole().name())
                .build();
    }
}
