package sn.ucad.biblioapi.notification;

/** Envoie un message à un destinataire et renvoie le canal utilisé (« EMAIL », « SMS »). */
public interface Notificateur {
    String envoyer(String destinataire, String message);
}
