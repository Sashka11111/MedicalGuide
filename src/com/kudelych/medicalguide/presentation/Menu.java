package com.sashka11111.bookkeeping.presentation;

import com.sashka11111.bookkeeping.service.AuthorizationService;
import com.sashka11111.bookkeeping.service.BookService;
import com.sashka11111.bookkeeping.service.CategoryService;
import com.sashka11111.bookkeeping.service.DeleteService;
import com.sashka11111.bookkeeping.service.EditService;
import com.sashka11111.bookkeeping.service.RegistrationService;
import com.sashka11111.bookkeeping.service.ReviewService;
import com.sashka11111.bookkeeping.service.SearchService;
import com.sashka11111.bookkeeping.domain.validation.UserInputHandler;

public class Menu {

  public Menu() {
  }

  public static void show() throws IllegalAccessException {
    UserInputHandler userInputHandler = new UserInputHandler();
    while (true) {
      if (Application.currentUser == null) {
        System.out.println("1) Реєстрація");
        System.out.println("2) Авторизація");
        System.out.println("0) Вихід");

        int choice = userInputHandler.promptUserForInteger("Ваш вибір");

        switch (choice) {
          case 1:
            RegistrationService.registration();
            break;
          case 2:
            AuthorizationService.authorization();
            break;
          case 0:
            System.out.println("Дякую за використання.");
            System.exit(0);
            break;
          default:
            System.out.println("Невірний вибір. Спробуйте ще раз.");
            break;
        }
        continue; // Повернення на початок циклу
      }

      String userRole = Application.currentUser.getRole();

      if ("".equals(userRole)) {
        System.out.println("1) Реєстрація");
        System.out.println("2) Авторизація");
      } else {
        String art = "      ______ ______\n" +
            "    _/      Y      \\_\n" +
            "   // ~~ ~~ | ~~ ~  \\\\\n" +
            "  // ~ ~ ~~ | ~~~ ~~ \\\\\n" +
            " //________.|.________\\\\\\\n" +
            "`----------`-'----------'";

        System.out.println(art);
        System.out.println("1) Перегляд даних");
        System.out.println("2) Пошук книг");
        System.out.println("3) Залишити відгук на книгу");

        if ("Бібліотекар".equals(userRole)) {
          System.out.println("4) Додавання даних");
          System.out.println("5) Редагування даних");
          System.out.println("6) Видалення даних");
        }
      }

      System.out.println("0) Вихід з головного меню");

      int choice = userInputHandler.promptUserForInteger("Ваш вибір");

      switch (choice) {
        case 1:
          if ("".equals(userRole)) {
            RegistrationService.registration();
          } else {
            showViewMenu();
          }
          break;
        case 2:
          if ("".equals(userRole)) {
            AuthorizationService.authorization();
          } else {
            SearchService.searchService(); // Пошук книг
          }
          break;
        case 3:
          if (!"".equals(userRole)) {
            ReviewService.addReview();
          }
          break;
        case 4:
          if ("Бібліотекар".equals(userRole)) {
            showAddMenu();
          }
          break;
        case 5:
          if ("Бібліотекар".equals(userRole)) {
            showEditMenu();
          }
          break;
        case 6:
          if ("Бібліотекар".equals(userRole)) {
            showDeleteMenu();
          }
          break;
        case 0:
          Application.currentUser = null; // Вихід з головного меню
          break;
        default:
          System.out.println("Невірний вибір. Спробуйте ще раз.");
          break;
      }
    }
  }

  private static void showViewMenu() throws IllegalAccessException {
    System.out.println("1) Переглянути книги");
    System.out.println("2) Переглянути категорії");
    System.out.println("3) Переглянути відгуки");
    System.out.println("4) Переглянути мої дані");
    System.out.println("5) Назад");

    int choice = new UserInputHandler().promptUserForInteger("Ваш вибір");

    switch (choice) {
      case 1:
        BookService.main(new String[]{});
        break;
      case 2:
        CategoryService.main(new String[]{});
        break;
      case 3:
        ReviewService.main(new String[]{});
        break;
      case 4:
        UserConsoleUI.displayUserInfo(Application.currentUser);
        break;
      case 5:
        return;
      default:
        System.out.println("Невірний вибір. Спробуйте ще раз.");
        break;
    }
  }

  private static void showAddMenu() throws IllegalAccessException {
    System.out.println("1) Додати книгу");
    System.out.println("2) Додати категорію");
    System.out.println("3) Назад");

    int choice = new UserInputHandler().promptUserForInteger("Ваш вибір");

    switch (choice) {
      case 1:
        BookService.addBook();
        break;
      case 2:
        CategoryService.addCategory();
        break;
      case 3:
        return;
      default:
        System.out.println("Невірний вибір. Спробуйте ще раз.");
        break;
    }
  }

  private static void showEditMenu() throws IllegalAccessException {
    System.out.println("1) Редагувати книгу");
    System.out.println("2) Редагувати категорію");
    System.out.println("3) Назад");

    int choice = new UserInputHandler().promptUserForInteger("Ваш вибір");

    switch (choice) {
      case 1:
        EditService.editBook();
        break;
      case 2:
        EditService.editCategory();
        break;
      case 3:
        return;
      default:
        System.out.println("Невірний вибір. Спробуйте ще раз.");
        break;
    }
  }
  private static void showDeleteMenu() throws IllegalAccessException {
    System.out.println("1) Видалити книгу");
    System.out.println("2) Видалити категорію");
    System.out.println("3) Видалити відгук");
    System.out.println("4) Видалити користувача");
    System.out.println("5) Назад");

    int choice = new UserInputHandler().promptUserForInteger("Ваш вибір");

    switch (choice) {
      case 1:
        DeleteService.deleteBook();
        break;
      case 2:
        DeleteService.deleteCategory();
        break;
      case 3:
        DeleteService.deleteReview();
        break;
      case 4:
        DeleteService.deleteUser();
        break;
      case 5:
        return;
      default:
        System.out.println("Невірний вибір. Спробуйте ще раз.");
        break;
    }
  }

}
