package com.vettrack.appointment.infrastructure.config;

import com.vettrack.appointment.domain.service.CitaDomainService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    /**
     * CitaDomainService no tiene @Component porque es parte del dominio puro.
     * Lo registramos como Bean aquí en la capa de infraestructura.
     */
    @Bean
    public CitaDomainService citaDomainService() {
        return new CitaDomainService();
    }
}