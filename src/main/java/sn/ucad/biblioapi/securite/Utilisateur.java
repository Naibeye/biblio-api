package sn.ucad.biblioapi.securite;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String identifiant;          // l'email de connexion

    @Column(nullable = false, length = 72)
    private String empreinte;            // jamais le mot de passe : son empreinte BCrypt

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    protected Utilisateur() { }

    public Utilisateur(String identifiant, String empreinte, Role role) {
        this.identifiant = identifiant;
        this.empreinte = empreinte;
        this.role = role;
    }

    public String getIdentifiant() { return identifiant; }
    public String getEmpreinte() { return empreinte; }
    public Role getRole() { return role; }
}
