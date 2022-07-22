package com.martynenko.anton.university;

import com.martynenko.anton.university.ui.QuestionDistributor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class used to start spring boot application.
 *
 * @author Martynenko Anton
 * @since 1.0
 */

@SpringBootApplication
public class UniversityApplication implements CommandLineRunner {

  /**
   * {@link QuestionDistributor} bean responsible for user interaction.
   */

  private final QuestionDistributor questionDistributor;

  /**
   * Autowiring constructor.
   * @param questionDistributor {@link QuestionDistributor} bean
   */

  @Autowired
  public UniversityApplication(final QuestionDistributor questionDistributor) {
    this.questionDistributor = questionDistributor;
  }

  /**
   * Start application.
   * @param args arguments
   */
  public static void main(final String[] args) {
    SpringApplication.run(UniversityApplication.class);
  }

  /**
   * Run beans when application is contained .
   * @param args arguments
   */
  @Override
  public void run(final String... args) {
    questionDistributor.run();
  }
}
