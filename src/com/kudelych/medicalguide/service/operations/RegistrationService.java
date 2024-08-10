package com.kudelych.medicalguide.service.operations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kudelych.medicalguide.domain.model.User;
import com.kudelych.medicalguide.domain.validation.ValidationInput;
import com.kudelych.medicalguide.presentation.Application;
import com.kudelych.medicalguide.presentation.Menu;
import com.kudelych.medicalguide.service.util.JsonDataReader;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class RegistrationService {

  private static final String USERS_FILE_PATH = "Data/users.json";

  public static void registration() {
    String username;
    String password;
    String email;
    Scanner scanner = new Scanner(System.in);

    // Зчитання списку користувачів із JSON файлу
    List<User> users = JsonDataReader.modelDataJsonReader(USERS_FILE_PATH, User[].class);

  // Введення та перевірка логіну
    do {
      System.out.println("Введiть логiн:");
      username = scanner.nextLine();

      if (ValidationInput.isEmpty(username)) {
        System.out.println("Логiн не може бути порожнiм.");
        continue;
      }
      if (!ValidationInput.isValidName(username)) {  // Важливо перевіряти, чи містить логін букву
        System.out.println("Логiн повинен мiстити буквенний символ.");
        continue;
      }
      if (!ValidationInput.isLoginUnique(users, username)) {
        System.out.println("Цей логiн вже використовується. Виберiть iнший.");
        continue;
      }

      break;  // Якщо всі умови виконані, виходимо з циклу
    } while (true);

    // Введення та перевірка паролю
    do {
      System.out.println("Введiть пароль:");
      password = scanner.nextLine();
      if (!ValidationInput.isValidPassword(password)) {
        System.out.println("Пароль має мiстити мiнiмум 6 символiв.");
      }
    } while (!ValidationInput.isValidPassword(password));

    // Введення та перевірка електронної пошти
    do {
      System.out.println("Введiть email:");
      email = scanner.nextLine();
      if (!ValidationInput.isValidEmail(email)) {
        System.out.println("Невiрний формат електронної пошти.");
      }
    } while (!ValidationInput.isValidEmail(email));

    String role = "User"; // роль "User" за замовчуванням

    // максимальний ID користувача
    int maxUserId = users.stream()
        .mapToInt(User::getUserId)
        .max()
        .orElse(0);

    // Новий ID користувача буде на одиницю більше за максимальний
    int newUserId = maxUserId + 1;

    // Створюємо нового користувача
    User newUser = new User(newUserId, username, password, email, role);
    Application.currentUser = newUser;

    // Додає нового користувача до списку та зберігає у файл
    users.add(newUser);
    saveUsersToJson(users, USERS_FILE_PATH);

    System.out.println("Реєстрацiя пройшла успiшно.");
    try {
      Menu.show();
    } catch (IllegalAccessException e) {
      throw new RuntimeException(e);
    }
  }

  private static void saveUsersToJson(List<User> users, String filePath) {
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      File file = new File(filePath);
      objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, users);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
