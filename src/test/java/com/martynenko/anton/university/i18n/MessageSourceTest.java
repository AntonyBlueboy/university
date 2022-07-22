package com.martynenko.anton.university.i18n;

import com.martynenko.anton.university.UniversityApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.context.MessageSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = UniversityApplication.class, initializers = ConfigDataApplicationContextInitializer.class)
class MessageSourceTest {

  @Autowired
  private MessageSource messageSource;

  @Test
  void contextLoads(){
    assertThat(messageSource).isNotNull();
  }

  @Test
  void getMessage(){
    for(MessageCode messageCode : MessageCode.values()){
      assertThat(messageSource.getMessage(messageCode.getCode(), null, null)).isNotNull();
    }
  }
}
