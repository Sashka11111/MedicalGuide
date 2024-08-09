package com.sashka11111.bookkeeping.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sashka11111.bookkeeping.domain.validation.ValidationInput;
import com.sashka11111.bookkeeping.presentation.Application;
import com.sashka11111.bookkeeping.domain.model.User;
import com.sashka11111.bookkeeping.presentation.Menu;

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
    String phoneNumber;
    Scanner scanner = new Scanner(System.in);

    List<User> users = JsonDataReader.modelDataJsonReader(USERS_FILE_PATH, User[].class);

    do {
      System.out.println("Введіть логін");
      username = scanner.nextLine();
      if (!isLoginUnique(users, username)) {
        System.out.println("Цей логін вже використовується. Виберіть інший.");
      }
    } while (!isLoginUnique(users, username));

    System.out.println("Введіть пароль");
    password = scanner.nextLine();
    System.out.println("Введіть email");
    email = scanner.nextLine();
    System.out.println("Введіть номер телефону (починайте з +):");
    phoneNumber = ValidationInput.getValidPhoneInput(scanner);

    String role = "Читач"; // Встановлюємо роль "Читач"

    // Знайти максимальний ID користувача
    int maxUserId = users.stream()
        .mapToInt(User::getUserId)
        .max()
        .orElse(0);

    // Новий ID користувача буде на одиницю більше за максимальний
    int newUserId = maxUserId + 1;

    User newUser = new User(newUserId, username, password, email, phoneNumber, role);
    Application.currentUser = newUser;

    users.add(newUser);

    saveUsersToJson(users, USERS_FILE_PATH);

    System.out.println("Реєстрація пройшла успішно.");
    try {
      Menu.show();
    } catch (IllegalAccessException e) {
      throw new RuntimeException(e);
    }
  }

  private static boolean isLoginUnique(List<User> users, String login) {
    for (User existingUser : users) {
      if (existingUser.getUsername().equals(login)) {
        return false; // Логін не унікальний
      }
    }
    return true; // Логін унікальний
  }

  private static void saveUsersToJson(List<User> users, String filePath) {
    ObjectMapper objectMapper = new ObjectMapper();

    try {
      File file = new File(filePath);
      // Записуємо весь список користувачів у файл
      objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, users);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
