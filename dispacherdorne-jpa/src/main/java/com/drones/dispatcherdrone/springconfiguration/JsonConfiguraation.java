package com.drones.dispatcherdrone.springconfiguration;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.datatype.hibernate5.jakarta.Hibernate5JakartaModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JsonConfiguraation {

  @Bean
  public Module hibernateModule() {
    return new Hibernate5JakartaModule();
  }
}
