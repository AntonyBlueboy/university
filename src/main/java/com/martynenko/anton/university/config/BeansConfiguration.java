package com.martynenko.anton.university.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * Beans configuration class.
 *
 * @author Martynenko Anton
 * @since 1.1
 */

@Configuration
public class BeansConfiguration {

  /**
   * Configure {@link MessageSource} bean.
   *
   * @return {@link MessageSource} bean
   */

  @Bean
  public MessageSource messageSource() {
    ReloadableResourceBundleMessageSource messageSource
        = new ReloadableResourceBundleMessageSource();

    messageSource.setBasename("classpath:messages");
    messageSource.setDefaultEncoding("UTF-8");
    return messageSource;
  }
}
