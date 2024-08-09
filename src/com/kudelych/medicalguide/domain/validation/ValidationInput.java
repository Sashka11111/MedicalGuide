package com.sashka11111.bookkeeping.domain.validation;

import java.util.Scanner;

/**
 * Утилітний клас для валідації та обробки вводу користувача.
 */
public class ValidationInput {

  /**
   * Запитує у користувача правильне числове значення типу int.
   *
   * @param scanner Об'єкт Scanner для зчитування вводу користувача.
   * @return Правильне числове значення типу int, введене користувачем.
   */
  public static int getValidIntInput(Scanner scanner) {
    int input;
    while (true) {
      try {
        input = Integer.parseInt(scanner.nextLine().trim());
        break; // Правильний ввід, виходимо з циклу
      } catch (NumberFormatException e) {
        System.out.println("Неправильний формат. Будь ласка, введіть ціле числове значення:");
      }
    }
    return input;
  }
  public static String getValidPhoneInput(Scanner scanner) {
    String input;
    while (true) {
      input = scanner.nextLine().trim();
      if (input.matches("^\\+[0-9]+$")) {
        break; // Правильний ввід, виходимо з циклу
      } else {
        System.out.println("Неправильний формат номеру телефону. Почніть номер з + та введіть цифри без пробілів.");
      }
    }
    return input;
  }
}
