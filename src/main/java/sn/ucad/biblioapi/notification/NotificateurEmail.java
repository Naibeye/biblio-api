package sn.ucad.biblioapi.notification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

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
