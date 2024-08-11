package com.kudelych.medicalguide.domain.validation;

import com.kudelych.medicalguide.domain.model.Category;
import com.kudelych.medicalguide.domain.model.User;
import java.util.List;
import java.util.Scanner;

public class ValidationInput {

  // Метод для перевірки, чи є рядок порожнім або містить лише пробіли
  public static boolean isEmpty(String input) {
    return input == null || input.trim().isEmpty();
  }
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

  // Метод для перевірки правильності введення ціни (позитивне число)
  public static boolean isValidPrice(double price) {
    return price > 0;
  }

  // Метод для перевірки правильності оцінки (від 1 до 5)
  public static boolean isValidMark(int mark) {
    return mark >= 1 && mark <= 5;
  }

  // Метод для перевірки правильності електронної пошти
  public static boolean isValidEmail(String email) {
    return email != null && email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
  }

  // Метод для перевірки довжини паролю (мінімум 6 символів)
  public static boolean isValidPassword(String password) {
    return password != null && password.length() >= 6;
  }

  // Метод для перевірки правильності введення імені (не порожній рядок та містить хоча б одну букву)
  public static boolean isValidName(String name) {
    return !isEmpty(name) && name.matches(".*[a-zA-Zа-яА-Я].*");
  }

  // Метод для перевірки унікальності логіна
  public static boolean isLoginUnique(List<User> users, String login) {
    return users.stream().noneMatch(user -> user.getUsername().equals(login));
  }

  public static boolean isCategoryNameUnique(List<Category> categories, String name) {
    return categories.stream()
        .noneMatch(category -> category.getName().equalsIgnoreCase(name));
  }
}
