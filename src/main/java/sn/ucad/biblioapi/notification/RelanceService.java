package sn.ucad.biblioapi.notification;

import org.springframework.stereotype.Service;

@Service
public class RelanceService {
    private final Notificateur notificateur;

    // Deux beans Notificateur existent : @Primary sur NotificateurEmail désigne celui-ci.
    // Autre solution : public RelanceService(@Qualifier("notificateurSms") Notificateur notificateur)
    public RelanceService(Notificateur notificateur) {
        this.notificateur = notificateur;
    }

    public String relancer(String destinataire) {
        return notificateur.envoyer(destinataire, "Votre livre est à rendre demain.");
    }
}
