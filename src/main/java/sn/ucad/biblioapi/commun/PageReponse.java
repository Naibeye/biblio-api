package sn.ucad.biblioapi.commun;

import java.util.List;

public record PageReponse<T>(List<T> elements, int page, int taille, long total, int nombrePages) { }
