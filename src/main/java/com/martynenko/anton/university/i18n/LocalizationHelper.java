package com.martynenko.anton.university.i18n;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Component to work with string resources .
 * Current realization use only one source file but can be extended with Locale options and new files with messages-{locale}.properties files.
 *
 * @author Martynenko Anton
 * @since 1.1
 */

@RequiredArgsConstructor
@Component
public class LocalizationHelper {

  /**
   * Configured {@link MessageSource} bean which provides an interface for working with string resources.
   */

  private final MessageSource messageSource;


  /**
   * Get string resource by provided code.
   * @param messageCode {@link MessageCode} enumeration
   * @return String resource
   * @throws NoSuchMessageException if no resource with provided code
   */
  @NotNull
  public  String getMessage(@NotNull final MessageCode messageCode) throws NoSuchMessageException {
    return messageSource.getMessage(messageCode.getCode(), null, Locale.ENGLISH);
  }

  /**
   * Get regex pattern string as resource by provided code, then extract value from provided text fragment .
   * @param extractFrom string fragment, containing value which could be extracted using pattern
   * @param messageCode {@link MessageCode} enumeration
   * @return String value or null
   */
  @NotNull
  public Optional<String> extract(@NotNull final String extractFrom, @NotNull final MessageCode messageCode)
      throws NoSuchElementException {
    Pattern pattern = Pattern.compile(getMessage(messageCode));
    Matcher matcher = pattern.matcher(extractFrom);
    if (matcher.find()) {
      return Optional.of(matcher.group(1));
    }
    return Optional.empty();
  }
}
