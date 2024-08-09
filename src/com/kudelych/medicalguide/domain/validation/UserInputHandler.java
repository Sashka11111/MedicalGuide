package com.sashka11111.bookkeeping.domain.validation;

import java.util.Scanner;

/**
 * Клас для обробки вводу користувача з консолі.
 */
public class UserInputHandler {

  private final Scanner scanner;

  public UserInputHandler() {
    this.scanner = new Scanner(System.in);
  }

  /**
   * Запитує у користувача введення цілого числа.
   *
   * @param prompt Повідомлення, яке буде показано користувачу.
   * @return Ціле числове значення, введене користувачем.
   */
  public int promptUserForInteger(String prompt) {
    System.out.print(prompt + ": ");

    while (!scanner.hasNextInt()) {
      System.out.println("Будь ласка, введіть ціле число.");
      System.out.print(prompt + ": ");
      scanner.next();
    }
    int result = scanner.nextInt();
    scanner.nextLine(); // Очищуємо буфер сканера
    return result;
  }
}
