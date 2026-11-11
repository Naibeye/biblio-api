package sn.ucad.biblioapi.emprunt;

// TODO ch09 : corps de POST /api/emprunts — livreId et adherentId, tous deux obligatoires.
public record EmpruntRequete(Long livreId, Long adherentId) { }
