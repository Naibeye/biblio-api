package sn.ucad.biblioapi.notification;

// TODO ch13 : code de démonstration des chapitres 3 à 5, à retirer du projet livré.
import org.springframework.stereotype.Service;

@Service
public class RelanceService {
    private final Notificateur notificateur;

    // Un seul notificateur existe : celui choisi par biblio.notification.canal (email par défaut).
    public RelanceService(Notificateur notificateur) {
        this.notificateur = notificateur;
    }

    public String relancer(String destinataire) {
        return notificateur.envoyer(destinataire, "Votre livre est à rendre demain.");
    }
}
