package sn.ucad.biblioapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class BiblioApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(BiblioApiApplication.class, args);
    }
}
