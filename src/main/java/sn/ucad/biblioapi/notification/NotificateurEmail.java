package sn.ucad.biblioapi.notification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

// TODO ch05 (TP 2 bis, étape 7) : créer ce bean seulement si biblio.notification.canal=email
// (ou si la clé est absente), et retirer @Primary
@Component
@Primary                     // le choix par défaut quand deux beans conviennent
public class NotificateurEmail implements Notificateur {
    private static final Logger log = LoggerFactory.getLogger(NotificateurEmail.class);

    @Override
    public String envoyer(String destinataire, String message) {
        log.info("EMAIL à {} : {}", destinataire, message);
        return "EMAIL";
    }
}
