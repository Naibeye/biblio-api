package sn.ucad.biblioapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InspectionConteneur {
    private static final Logger log = LoggerFactory.getLogger(InspectionConteneur.class);

    @Bean
    public ApplicationRunner listerNosBeans(ApplicationContext contexte) {
        return args -> {                  // exécuté une fois, après le démarrage
            log.info("Le conteneur gère {} beans au total", contexte.getBeanDefinitionCount());
            for (String nom : contexte.getBeanDefinitionNames()) {
                Class<?> type = contexte.getType(nom);
                if (type != null && type.getPackageName().startsWith("sn.ucad")) {
                    log.info("  bean « {} » : {}", nom, type.getSimpleName());
                }
            }
        };
    }
}
