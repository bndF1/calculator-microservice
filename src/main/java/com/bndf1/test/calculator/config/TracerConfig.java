package com.bndf1.test.calculator.config;

import io.corp.calculator.TracerImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TracerConfig {

  @Bean
  public TracerImpl tracer() {
    return new TracerImpl();
  }
}
