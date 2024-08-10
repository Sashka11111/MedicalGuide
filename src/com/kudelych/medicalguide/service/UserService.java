package com.kudelych.medicalguide.service;

import com.kudelych.medicalguide.domain.model.User;
import java.lang.reflect.Field;

public class UserService {

  // Метод для виведення інформації про користувача в консоль
  public static void displayUserInfo(User user) throws IllegalAccessException {
    for (Field field : User.class.getDeclaredFields()) {
      String fieldName = field.getName();

      // Пропускаємо поле ID
      if ("userId".equals(fieldName)) {
        continue;
      }

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
    switch (fieldName) {
      case "username":
        System.out.print("Логін:");
        break;
      case "password":
        System.out.print("Пароль:");
        break;
      case "email":
        System.out.print("Електронна пошта:");
        break;
      case "role":
        System.out.print("Роль:");
        break;
      default:
        System.out.print(fieldName + ":");
        break;
    }
  }

  // Метод для виведення значення поля
  private static void printField(String value) {
    System.out.println(value);
  }
}