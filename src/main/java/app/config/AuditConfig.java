package app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class AuditConfig {

//@Bean
//    public AuditorAware<String> auditorProvider() {
//    return () -> Optional.ofNullable(SecurityContextHolder.getContext())
//            .map(SecurityContext::getAuthentication)
//            .map(Principal::getName);
//}
}
