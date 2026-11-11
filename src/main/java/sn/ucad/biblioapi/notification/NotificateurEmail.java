package sn.ucad.biblioapi.notification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "biblio.notification.canal", havingValue = "email",
                       matchIfMissing = true)          // choix par défaut si la clé est absente
public class NotificateurEmail implements Notificateur {
    private static final Logger log = LoggerFactory.getLogger(NotificateurEmail.class);

    @Override
    public String envoyer(String destinataire, String message) {
        log.info("EMAIL à {} : {}", destinataire, message);
        return "EMAIL";
    }
}
