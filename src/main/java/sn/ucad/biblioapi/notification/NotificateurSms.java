package sn.ucad.biblioapi.notification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "biblio.notification.canal", havingValue = "sms")
public class NotificateurSms implements Notificateur {
    private static final Logger log = LoggerFactory.getLogger(NotificateurSms.class);

    @Override
    public String envoyer(String destinataire, String message) {
        log.info("SMS à {} : {}", destinataire, message);
        return "SMS";
    }
}
