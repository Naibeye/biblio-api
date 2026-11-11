package sn.ucad.biblioapi.emprunt;

// TODO ch09 : JpaRepository<Emprunt, Long> avec
//   existsByLivreIdAndDateRetourIsNull(Long livreId)      — le livre est-il sorti ?
//   countByAdherentIdAndDateRetourIsNull(Long adherentId) — emprunts en cours de l'adhérent
//   retardsAu(LocalDate date) (exercice 9.2) — non rendus, date prévue dépassée, avec livre et adhérent
public interface EmpruntRepository {
}
