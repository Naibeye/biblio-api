package sn.ucad.biblioapi.notification;

// TODO ch13 : code de démonstration des chapitres 3 à 5, à retirer du projet livré.
/** Envoie un message à un destinataire et renvoie le canal utilisé (« EMAIL », « SMS »). */
public interface Notificateur {
    String envoyer(String destinataire, String message);
}
