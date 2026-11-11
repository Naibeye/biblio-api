package sn.ucad.biblioapi.commun;

import java.time.Clock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HorlogeConfig {
    @Bean
    public Clock horloge() {
        return Clock.systemDefaultZone();
    }
}
