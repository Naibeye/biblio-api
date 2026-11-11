-- Données de départ, chargées au démarrage (base H2 en mémoire)
INSERT INTO auteur (nom, nationalite) VALUES ('Robert C. Martin', 'américaine');
INSERT INTO auteur (nom, nationalite) VALUES ('Cheikh Hamidou Kane', 'sénégalaise');
INSERT INTO auteur (nom, nationalite) VALUES ('Mariama Bâ', 'sénégalaise');
INSERT INTO auteur (nom, nationalite) VALUES ('Ousmane Sembène', 'sénégalaise');
INSERT INTO auteur (nom, nationalite) VALUES ('Ahmadou Kourouma', 'ivoirienne');

INSERT INTO livre (isbn, titre, annee, auteur_id) VALUES ('978-2-1234', 'Clean Code', 2008, 1);
INSERT INTO livre (isbn, titre, annee, auteur_id) VALUES ('978-2-5678', 'Spring en action', 2022, NULL);
INSERT INTO livre (isbn, titre, annee, auteur_id) VALUES ('978-2-07-036024-5', 'L''Aventure ambiguë', 1961, 2);
INSERT INTO livre (isbn, titre, annee, auteur_id) VALUES ('978-2-84261-001-0', 'Une si longue lettre', 1979, 3);
INSERT INTO livre (isbn, titre, annee, auteur_id) VALUES ('978-2-266-01234-5', 'Les Bouts de bois de Dieu', 1960, 4);
INSERT INTO livre (isbn, titre, annee, auteur_id) VALUES ('978-2-02-002603-5', 'Les Soleils des indépendances', 1968, 5);
INSERT INTO livre (isbn, titre, annee, auteur_id) VALUES ('978-2-02-040318-0', 'Allah n''est pas obligé', 2000, 5);
INSERT INTO livre (isbn, titre, annee, auteur_id) VALUES ('978-2-84261-002-7', 'Un chant écarlate', 1981, 3);
INSERT INTO livre (isbn, titre, annee, auteur_id) VALUES ('978-0-13-449416-6', 'Clean Architecture', 2017, 1);
INSERT INTO livre (isbn, titre, annee, auteur_id) VALUES ('978-2-07-040321-9', 'Les Gardiens du temple', 1995, 2);

INSERT INTO adherent (nom, email, categorie) VALUES ('Amina', 'amina@ucad.sn', 'ETUDIANT');
INSERT INTO adherent (nom, email, categorie) VALUES ('Hassan', 'hassan@ucad.sn', 'ENSEIGNANT');

-- TODO ch11 (TP 8a, étape 2) : les comptes de démonstration (table utilisateur : identifiant,
-- empreinte, role). Mots de passe amina-2026, moussa-2026, hassan-2026 ; empreintes BCrypt :
--   amina@ucad.sn  ADHERENT        $2a$10$UuNaL5o89IXDlOGS9jYRbuA4vMxN8KX6tB3tLjttEPZX4pLsV/ity
--   moussa@ucad.sn BIBLIOTHECAIRE  $2a$10$v4HDyrMtnpfb97z2koL90eDbDWA54ISgcvhKPmssdScscvOlmiHfW
--   hassan@ucad.sn ADHERENT        $2a$10$.ZIQ7AYmddgTLCCikS8b9eMYWx24igb2pBCfi1uTvpAay8q8YbqGu
