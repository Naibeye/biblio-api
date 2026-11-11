package sn.ucad.biblioapi;

// TODO ch13 : code de démonstration des chapitres 3 à 5, à retirer du projet livré.
import java.time.LocalDateTime;

// Renvoyé par GET /info : Jackson le convertit en JSON.
public record Info(String application, String version, LocalDateTime heureServeur) { }
