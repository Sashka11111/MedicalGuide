package com.kudelych.medicalguide.service.operations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kudelych.medicalguide.domain.model.User;
import com.kudelych.medicalguide.domain.validation.UserInputHandler;
import com.kudelych.medicalguide.presentation.Application;
import com.kudelych.medicalguide.presentation.Menu;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class AuthorizationService {

  public static void authorization() {
    UserInputHandler inputHandler = new UserInputHandler();

    String role;
    while (true) {
      System.out.println("Оберіть роль:");
      System.out.println("1) Admin");
      System.out.println("2) User");

      int roleChoice = inputHandler.promptUserForInteger("Ваш вибір: ");

      if (roleChoice == 1) {
        role = "Admin";
        break;
      } else if (roleChoice == 2) {
        role = "User";
        break;
      } else {
        System.out.println("Невірний вибір ролі. Спробуйте ще раз.");
      }
    }

    Scanner scanner = new Scanner(System.in);
    System.out.println("Введіть логін:");
    String username = scanner.nextLine();

    System.out.println("Введіть пароль:");
    String password = scanner.nextLine();

    List<User> users = readUsersFromJson("Data/users.json");

    User user = findUserByUsernameAndRole(users, username, role);

    if (user != null && user.getPassword().equals(password)) {
      Application.currentUser = user;
      System.out.println("Авторизація пройшла успішно.");
      try {
        Menu.show();
      } catch (IllegalAccessException e) {
        throw new RuntimeException(e);
      }
    }
    if (user == null) {
      System.out.println("Це не Ваша роль, авторизуйтесь знову.");
    } else {
      System.out.println("Помилка авторизації. Перевірте логін та пароль.");
    }
  }

  private static User findUserByUsernameAndRole(List<User> users, String username, String role) {
    for (User user : users) {
      if (user.getUsername().equals(username) && user.getRole().equals(role)) {
        return user;
      }
    }
    return null;
  }

  private static List<User> readUsersFromJson(String filePath) {
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      return Arrays.asList(objectMapper.readValue(new File(filePath), User[].class));
    } catch (IOException e) {
      e.printStackTrace();
      // Обробка помилок при читанні з файлу
      return new ArrayList<>();
    }
  }
}
