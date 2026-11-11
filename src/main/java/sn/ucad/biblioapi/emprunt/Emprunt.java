package sn.ucad.biblioapi.emprunt;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import sn.ucad.biblioapi.adherent.Adherent;
import sn.ucad.biblioapi.commun.ConflitException;
import sn.ucad.biblioapi.livre.Livre;

@Entity
public class Emprunt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Livre livre;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Adherent adherent;

    private LocalDate dateEmprunt;
    private LocalDate dateRetourPrevue;
    private LocalDate dateRetour;

    protected Emprunt() { }

    public Emprunt(Livre livre, Adherent adherent, LocalDate aujourdhui, int dureeJours) {
        this.livre = livre;
        this.adherent = adherent;
        this.dateEmprunt = aujourdhui;
        this.dateRetourPrevue = aujourdhui.plusDays(dureeJours);
    }

    /** Clôture l'emprunt et renvoie le nombre de jours de retard (0 si à l'heure). */
    public long rendre(LocalDate date) {
        if (dateRetour != null) {
            throw new ConflitException("Emprunt déjà clôturé");
        }
        this.dateRetour = date;
        return Math.max(0, ChronoUnit.DAYS.between(dateRetourPrevue, date));
    }

    public Long getId() { return id; }
    public Livre getLivre() { return livre; }
    public Adherent getAdherent() { return adherent; }
    public LocalDate getDateEmprunt() { return dateEmprunt; }
    public LocalDate getDateRetourPrevue() { return dateRetourPrevue; }
    public LocalDate getDateRetour() { return dateRetour; }
}
