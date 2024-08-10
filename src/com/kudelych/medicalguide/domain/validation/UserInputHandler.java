package com.kudelych.medicalguide.domain.validation;

import java.util.Scanner;

public class UserInputHandler {

  private final Scanner scanner;

  public UserInputHandler() {
    this.scanner = new Scanner(System.in);
  }

  /**
   * Запитує у користувача ціле число з консолі.
   *
   * @param prompt Повідомлення для користувача.
   * @return Введене число.
   */
  public int promptUserForInteger(String prompt) {
    System.out.print(prompt);
    while (!scanner.hasNextInt()) {
      System.out.println("Будь ласка, введіть дійсне ціле число.");
      scanner.next(); // очищення неправильного вводу
      System.out.print(prompt);
    }
    return scanner.nextInt();
  }

  /**
   * Запитує у користувача рядок з консолі.
   *
   * @param prompt Повідомлення для користувача.
   * @return Введений рядок.
   */
  public String promptUserForString(String prompt) {
    System.out.print(prompt);
    return scanner.nextLine();
  }
}
