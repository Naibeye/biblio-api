package sn.ucad.biblioapi.notification;

// TODO ch04 (TP 2, étape 7) : bean « service » qui reçoit UN Notificateur par son constructeur.
// Deux beans conviennent : lire l'erreur au démarrage, puis la corriger avec @Primary
// (sur NotificateurEmail), puis avec @Qualifier("notificateurSms") au point d'injection.
public class RelanceService {

    public String relancer(String destinataire) {
        throw new UnsupportedOperationException(
                "TODO ch04 : envoyer « Votre livre est à rendre demain. » et renvoyer le canal");
    }
}
