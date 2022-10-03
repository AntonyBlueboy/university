package com.martynenko.anton.university.i18n;

import com.martynenko.anton.university.UniversityApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.context.MessageSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Locale;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = UniversityApplication.class, initializers = ConfigDataApplicationContextInitializer.class)
class LocalizationHelperTest {

  @InjectMocks
  private LocalizationHelper localizationHelper;

  @Mock
  private MessageSource messageSource;

  @Test
  void contextLoad(){
    assertThat(localizationHelper).isNotNull();
  }

  @Test
  void getMessage() {
    MessageCode messageCode = MessageCode.ANSWER_COMMON_BYE;//anything is ok
    String someCode = messageCode.getCode();
    String someString = "Some string";

    when(messageSource.getMessage(someCode, null, Locale.ENGLISH)).thenReturn(someString);

    assertThat(localizationHelper.getMessage(messageCode)).isEqualTo(someString);
  }

  @Test
  void extract() {
    MessageCode messageCode = MessageCode.ANSWER_COMMON_BYE;//anything is ok

    String value = "VALUE";
    String stringContainsValue = String.format("Some string with value %s here", value);
    String stringWithoutValue = "Just string";
    String patternString = "^Some string with value ([a-zA-Z ]+) here$";
    String code = messageCode.getCode();

    when(messageSource.getMessage(code, null, Locale.ENGLISH)).thenReturn(patternString);

    assertThat(localizationHelper.extract(stringContainsValue, messageCode)).isEqualTo(Optional.of(value));

    assertThat(localizationHelper.extract(stringWithoutValue, messageCode)).isNotPresent();
  }
}