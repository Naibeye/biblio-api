package sn.ucad.biblioapi.notification;

// TODO ch04 (TP 2, étape 7) : faire de cette implémentation un bean.
public class NotificateurEmail implements Notificateur {

    @Override
    public String envoyer(String destinataire, String message) {
        throw new UnsupportedOperationException("TODO ch04 : journaliser l'envoi et renvoyer \"EMAIL\"");
    }
}
