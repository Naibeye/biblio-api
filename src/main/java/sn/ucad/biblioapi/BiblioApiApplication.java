package sn.ucad.biblioapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// TODO ch05 (TP 2 bis, étape 1) : activer la lecture des records @ConfigurationProperties
public class BiblioApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(BiblioApiApplication.class, args);
    }
}
