package com.sashka11111.bookkeeping.presentation;


import com.sashka11111.bookkeeping.domain.model.User;
import java.lang.reflect.Field;

public class UserConsoleUI {

  // Метод для виведення інформації про користувача в консоль
  public static void displayUserInfo(User user) throws IllegalAccessException {

    for (Field field : User.class.getDeclaredFields()) {
      String fieldName = field.getName();
      field.setAccessible(true);
      Object value = field.get(user);
      printFieldName(fieldName);

      if (value instanceof String) {
        printField((String) value);
      } else if (value instanceof Long) {
        printField(String.valueOf(value));
      } else {
        printField(value.toString());
      }
    }
  }

  // Метод для виведення назви поля
  private static void printFieldName(String fieldName) {
    System.out.println(fieldName + ":");
  }

  // Метод для виведення значення поля
  private static void printField(String value) {
    System.out.println(value);
  }
}
